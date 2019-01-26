package com.earthwax

interface ClickEventDelegate<T> {

    fun onClick(item: T)
    fun onLongClick(item: T)

}