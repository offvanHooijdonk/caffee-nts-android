package by.nts.cafe.app.presentation.presenter.order;

import by.nts.cafe.app.presentation.ui.order.IOrderView;
import io.reactivex.disposables.CompositeDisposable;

public class OrderPresenter {
    private IOrderView view;
    private CompositeDisposable compDisposable= new CompositeDisposable();

    public void onViewCreated() {

    }

    // region attach/detach
    public void attachView(IOrderView orderView) {
        this.view = orderView;
    }

    public void detach() {
        compDisposable.dispose();
        view = null;
    }
    // endregion
}
