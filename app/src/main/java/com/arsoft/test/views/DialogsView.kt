package com.arsoft.test.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.arsoft.test.models.DialogModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface DialogsView: MvpView {
    fun showError(textResource: Int)
    fun setupEmptyList()
    fun setupDialogs(dialogsList: ArrayList<DialogModel>)
    fun startLoading()
    fun endLoading()
    fun runAnimation()
}