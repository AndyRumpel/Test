package com.arsoft.test.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arsoft.test.R
import com.arsoft.test.models.DialogModel
import com.arsoft.test.providers.DialogsProvider
import com.arsoft.test.views.DialogsView

@InjectViewState
class DialogsPresenter: MvpPresenter<DialogsView>() {

    fun loadDialogs() {
        viewState.startLoading()
        DialogsProvider(this).loadDialogs()
    }

    fun dialogsLoaded(dialogsList: ArrayList<DialogModel>) {
        viewState.endLoading()
        if (dialogsList.size == 0) {
            viewState.setupEmptyList()
            viewState.showError(R.string.error_no_items)
        } else {
            viewState.setupDialogs(dialogsList)
            viewState.runAnimation()
        }
    }

    fun showError(textResource: Int) {
        viewState.showError(textResource)
    }
}