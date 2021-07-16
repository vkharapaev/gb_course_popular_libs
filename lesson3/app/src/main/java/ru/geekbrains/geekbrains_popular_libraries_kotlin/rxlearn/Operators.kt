package ru.geekbrains.geekbrains_popular_libraries_kotlin.rxlearn

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Function4
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.TestScheduler
import java.util.concurrent.TimeUnit
import kotlin.random.Random


class Operators {

    fun exec() {
        Consumer(Producer()).exec()
    }

    class Producer() {

        fun randomResultOperation(): Boolean {
            Thread.sleep(1000)
            return listOf(true, false, true)[Random.nextInt(2)]
        }

        fun just(): Observable<String> {
            return Observable.just("1", "2", "3", "4", "5")
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

        fun create1() = Observable.create<Int> { emitter ->
            for (i in 0..10) {
                Thread.sleep(Random.nextLong(500))
                emitter.onNext(i)
            }
            emitter.onComplete()
        }.map { it.toString() + "a" }

        fun create2() = Observable.create<Int> { emitter ->
            for (i in 0..10) {
                Thread.sleep(Random.nextLong(500))
                emitter.onNext(i)
            }
            emitter.onComplete()
        }.map { it.toString() + "b" }

    }

    class Consumer(val producer: Producer) {

        fun exec() {
            //execMap()
            //execFilter()
            //execDistinct()
            //execTake()
            //execSkip()
            //execMerge()
            //execConcat()
            //execDebounce()
            //execZip()
            execFlatMap()
        }

        fun execMap() {
            producer.just().map {
                return@map it + it
            }.subscribe({
                println("onNext: $it")
            }, {
                it.printStackTrace()
            })
        }

        fun execFilter() {
            producer.just().filter {
                it.toInt() > 1
            }.subscribe({
                println("onNext: $it")
            }, {
                it.printStackTrace()
            })
        }

        fun execDistinct() {
            producer.just().distinct() //distinctUntilChanged()
                .subscribe({
                    println("onNext: $it")
                }, {
                    it.printStackTrace()
                })
        }


        fun execTake() {
            producer.just().take(3)
                .subscribe({
                    println("onNext: $it")
                }, {
                    it.printStackTrace()
                })
        }

        fun execSkip() {
            producer.just().skip(2)
                .subscribe({
                    println("onNext: $it")
                }, {
                    it.printStackTrace()
                })
        }

        fun execMerge() {
            producer.create1()
                .subscribeOn(Schedulers.newThread())
                .mergeWith(producer.create2())
                .subscribe({
                    println("onNext: $it")
                }, {
                    it.printStackTrace()
                })
        }

        fun execConcat() {
            producer.create1()
                .subscribeOn(Schedulers.newThread())
                .concatWith(producer.create2())
                .subscribe({
                    println("onNext: $it")
                }, {
                    it.printStackTrace()
                })
        }

        fun execDebounce() {
            Observable.create<String>() { emitter ->
                Thread.sleep(100)
                emitter.onNext("1")
                Thread.sleep(100)
                emitter.onNext("2")
                Thread.sleep(100)
                emitter.onNext("3")
                Thread.sleep(400)
                emitter.onComplete()
            }.debounce(300, TimeUnit.MILLISECONDS)
                .subscribe({
                    println("onNext: $it")
                }, {
                    it.printStackTrace()
                })
        }

        fun execZip() {
            val observable1 =
                Observable.just("1", "11", "111").delay(1, TimeUnit.SECONDS).doOnNext { println("doOnNext 1") }
            val observable2 =
                Observable.just("2", "22").delay(2, TimeUnit.SECONDS).doOnNext { println("doOnNext 2") }
            val observable3 =
                Observable.just("3","33").delay(3, TimeUnit.SECONDS).doOnNext { println("doOnNext 3") }
            val observable4 =
                Observable.just("4", "44").delay(4, TimeUnit.SECONDS).doOnNext { println("doOnNext 4") }

            Observable.zip(
                observable1,
                observable2,
                observable3,
                observable4,
                Function4 { v1, v2, v3, v4 ->
                    return@Function4 listOf<String>(v1, v2, v3, v4)
                }).subscribe({
                println("onNext: $it")
            }, {
                it.printStackTrace()
            })
        }

        fun execFlatMap() {
            val testScheduler = TestScheduler()
            producer.just()
                .flatMap {
                    val delay = Random.nextInt(10).toLong()
                    return@flatMap Observable.just(it + "x").delay(delay, TimeUnit.SECONDS, testScheduler)
                }
                .toList()
                .subscribe({ s ->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                })

            testScheduler.advanceTimeBy(1, TimeUnit.MINUTES)
        }


    }

}