package by.nts.cafe.app.helper.rx

import io.reactivex.FlowableTransformer
import io.reactivex.MaybeTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun <T> schedulersIO(): ObservableTransformer<T, T> = ObservableTransformer { observable ->
    observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun <T> schedulersIOMaybe() = MaybeTransformer<T, T> { maybe ->
    maybe
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun <T> schedulersIOFlowable() = FlowableTransformer<T, T> { flowable ->
    flowable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun <T> schedulersIOSingle(): SingleTransformer<T, T> = SingleTransformer { single ->
    single
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

