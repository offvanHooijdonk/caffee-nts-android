package by.nts.cafe.app.presentation.presenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class AbstractDisposePresenter {
    private CompositeDisposable compositeDisposable;

    public AbstractDisposePresenter() {
        compositeDisposable = new CompositeDisposable();
    }

    protected void addDisposable(Disposable d) {
        compositeDisposable.add(d);
    }

    protected void disposeAll() {
        compositeDisposable.dispose();
    }
}
