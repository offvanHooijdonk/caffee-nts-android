package by.nts.cafe.app.presentation.presenter.halls;

import android.content.Context;
import android.support.annotation.NonNull;

import by.nts.cafe.app.helper.rx.Transformers;
import by.nts.cafe.app.network.NetworkClientFactory;
import by.nts.cafe.app.presentation.presenter.AbstractDisposePresenter;
import by.nts.cafe.app.presentation.ui.halls.IHallsView;

import by.nts.cafe.app.CafeApp;

public class HallsPresenter extends AbstractDisposePresenter {

    private IHallsView view;
    private Context ctx;

    public HallsPresenter(@NonNull IHallsView view, @NonNull Context ctx) {
        super();
        this.view = view;
        this.ctx = ctx;
    }

    public void loadHalls() {
        addDisposable(
                CafeApp.Companion.getAppDatabase().hallDao().getAll()
                        .compose(Transformers.schedulersIOMaybe())
                        .subscribe(view::onHallsLoaded, view::onError)
        );

    }

    public void updateHalls() {
        view.showUpdateProcess(true);
        addDisposable(
                NetworkClientFactory.getHallClient(ctx).getHalls()
                        .doOnNext(list -> CafeApp.Companion.getAppDatabase().hallDao().saveAll(list))
                        .compose(Transformers.schedulersIO())
                        .doOnNext(list -> view.showUpdateProcess(false))
                        .subscribe(view::onHallsLoaded, view::onError)
        );
    }

    public void detach() {
        view = null;
        disposeAll();
    }
}
