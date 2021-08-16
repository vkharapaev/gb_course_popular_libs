package com.headmostlab.findmovie2.ui.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import com.headmostlab.findmovie2.mvp.model.network.NetworkStatus
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

class AndroidNetworkStatus(private val context: Context) : NetworkStatus {

    private val statusSubject = BehaviorSubject.create<Boolean>()

    init {
        statusSubject.onNext(false)
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(
            request,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    statusSubject.onNext(true)
                }

                override fun onUnavailable() {
                    statusSubject.onNext(false)
                }

                override fun onLost(network: Network) {
                    statusSubject.onNext(false)
                }
            })
    }

    override fun isOnline(): Observable<Boolean> = statusSubject
    override fun isOnlineSingle(): Single<Boolean> = statusSubject.first(false)
}