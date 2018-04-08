package au.com.sentia.test.utils.events

import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable

object RxBus {
    private val eventBus: Relay<Any> = PublishRelay.create()
    fun send(obj: Any) {
        eventBus.accept(obj)
    }

    fun toObservable(): Observable<Any> {
        return eventBus;
    }
}
