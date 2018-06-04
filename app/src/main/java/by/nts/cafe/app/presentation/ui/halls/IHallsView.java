package by.nts.cafe.app.presentation.ui.halls;

import java.util.List;

import by.nts.cafe.app.model.db.HallModel;

public interface IHallsView {
    void onHallsLoaded(List<HallModel> hallsList);
    void onError(Throwable th);
    void showUpdateProcess(boolean isShow);
}
