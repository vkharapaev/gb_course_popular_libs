package ru.geekbrains.geekbrains_popular_libraries_kotlin.rxlearn

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit
import kotlin.random.Random


class Creation {

    fun exec() {
        Consumer(Producer()).exec()
    }

    class Producer() {

        fun randomResultOperation(): Boolean {
            Thread.sleep(1000)
            return listOf(true, false, true)[Random.nextInt(2)]
        }

        fun just(): Observable<String> {
            return Observable.just("1", "2", "3")
        }

        fun fromIterable(): Observable<String> {
            return Observable.fromIterable(listOf("1", "2", "3"))
        }

        fun interval() = Observable.interval(1, TimeUnit.SECONDS);

        fun timer() = Observable.timer(5, TimeUnit.SECONDS);

        fun range() = Observable.range(1, 10)

        fun fromCallable() = Observable.fromCallable {
            return@fromCallable randomResultOperation().toString()
        }

        fun create() = Observable.create<String> { emitter ->
            for (i in 0..10){
                randomResultOperation().let {
                    if(it){
                        println("Emitting on ${Thread.currentThread().name}" )
                        emitter.onNext("Success")
                    } else {
                        emitter.onError(RuntimeException("Error"))
                        return@create
                    }
                }
            }
            emitter.onComplete()
        }

    }

    class Consumer(val producer: Producer) {

        val stringObserver = object : Observer<String> {

            override fun onSubscribe(d: Disposable) {
                println("onSubscribe")
            }

            override fun onNext(str: String) {
                println("onNext: $str")
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

            override fun onComplete() {
                println("onComplete")
            }
        }


        fun exec() {
            //execJust()
            //execFromCallable()
            //execRange()
            //execInterval()
            execCreate()
        }

        fun execJust() {
            val observable = producer.just()
            observable.subscribe(stringObserver)
        }

        fun execFromCallable() {
            val observable = producer.fromCallable()
            observable.subscribe(stringObserver)
        }

        fun execRange() {
            val observable = producer.range()
            observable.subscribe({
                println("onNext: $it")
            }, {
                it.printStackTrace()
            }, {
                println("onComplete")
            })
        }

        fun execInterval() {
            val observable = producer.interval()
            val disposable = observable.subscribe({
                println("onNext: $it")
            }, {
                it.printStackTrace()
            }, {
                println("onComplete")
            })

            producer.timer().subscribe({
                disposable.dispose()
            }, {
                it.printStackTrace()
            })


        }

        fun execCreate() {
            val observable = producer.create()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread() нельзя использовать в презентере, его нужно отдавать в конструктор в качестве Scheduler!

            observable.subscribe({
                println("onNext on ${Thread.currentThread().name}: $it")
            }, {
                it.printStackTrace()
            }, {
                println("onComplete")
            })

        }
    }

}