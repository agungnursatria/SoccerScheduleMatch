package com.anb.soccerschedulematch.feature.Base

open class BaseFragmentPresenter<V : MvpView> : MvpPresenter<V> {

    var mvpView: V? = null

    override fun getView(): V = mvpView!!

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
    }

}