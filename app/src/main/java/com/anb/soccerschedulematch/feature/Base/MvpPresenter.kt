package com.anb.soccerschedulematch.feature.Base

interface MvpPresenter<V : MvpView> {

    fun onAttach(mvpView: V)
    fun getView(): V?

}