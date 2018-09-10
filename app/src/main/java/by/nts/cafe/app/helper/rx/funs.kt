package by.nts.cafe.app.helper.rx

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Yahor_Fralou on 9/10/2018 3:12 PM.
 */

fun Disposable.attachTo(cd: CompositeDisposable) {
    cd.add(this);
}