package by.nts.cafe.app.presentation.ui.tables;

import java.util.List;

import by.nts.cafe.app.model.db.TableModel;

public interface ITablesView {
    void handleError(Throwable th);

    void onTablesLoaded(List<TableModel> list);
}
