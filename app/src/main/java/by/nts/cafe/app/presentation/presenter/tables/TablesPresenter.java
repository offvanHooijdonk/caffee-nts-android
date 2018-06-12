package by.nts.cafe.app.presentation.presenter.tables;

import android.support.annotation.NonNull;

import java.util.List;

import by.nts.cafe.app.CafeApp;
import by.nts.cafe.app.helper.rx.Transformsers;
import by.nts.cafe.app.model.db.TableModel;
import by.nts.cafe.app.presentation.ui.tables.ITablesView;
import io.reactivex.disposables.CompositeDisposable;

public class TablesPresenter {
    private ITablesView view;
    private CompositeDisposable cd;

    public TablesPresenter(@NonNull ITablesView view) {
        this.view = view;
    }

    public void loadTableList(String hallId) {
        cd.add(
                CafeApp.getAppDatabase().tableDao()
                        .getAll(hallId)
                        .compose(Transformsers.schedulersIOMaybe())
                        .subscribe(this::tablesLoaded, view::handleError));
    }

    public void detachView() {
        cd.dispose();
        view = null;
    }

    private void tablesLoaded(List<TableModel> list) {
        view.onTablesLoaded(list);
    }
}
