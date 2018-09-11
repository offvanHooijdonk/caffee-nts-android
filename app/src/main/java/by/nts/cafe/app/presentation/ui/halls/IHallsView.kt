package by.nts.cafe.app.presentation.ui.halls

import by.nts.cafe.app.model.db.HallModel

interface IHallsView {
    fun onHallsLoaded(hallsList: List<HallModel>)
    fun onError(th: Throwable)
    fun showUpdateProcess(isShow: Boolean)
}
