package com.kodyuzz.kanas.ui.dummies

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.kodyuzz.kanas.data.model.Dummy
import com.kodyuzz.kanas.ui.base.BaseAdapter

class DummiesAdapter(
    parentLifecycle: Lifecycle,
    private val dummies: ArrayList<Dummy>
) : BaseAdapter<Dummy, DummyItemViewHolder>(parentLifecycle, dummies) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DummyItemViewHolder(parent)
}