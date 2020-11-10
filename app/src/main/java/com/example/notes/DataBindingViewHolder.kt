package com.example.notes

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class DataBindingViewHolder<T>
    (val dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root) {

    abstract fun onBind(t: T)

}