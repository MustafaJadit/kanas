package com.kodyuzz.kanas.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.kodyuzz.kanas.InstagramApplication
import com.kodyuzz.kanas.di.component.DaggerViewHolderComponent
import com.kodyuzz.kanas.di.component.ViewHolderComponent
import com.kodyuzz.kanas.di.module.ViewHolderModule
import com.kodyuzz.kanas.utils.display.Toaster
import javax.inject.Inject

abstract class BaseItemViewHolder<T : Any, VM : BaseItemViewModel<T>>(
    @LayoutRes layoutId: Int, parent: ViewGroup
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)),
    LifecycleOwner {

    init {
        onCreate()
    }

    @Inject
    lateinit var viewModel: VM

    @Inject
    lateinit var lifeCycleRegistry: LifecycleRegistry

    override fun getLifecycle(): Lifecycle = lifeCycleRegistry


    open fun bind(data: T) {
        viewModel.updateData(data)
    }

    protected fun onCreate() {
        injectDependencies(buildViewHolderComponent())
        lifeCycleRegistry.markState(Lifecycle.State.INITIALIZED)
        lifeCycleRegistry.markState(Lifecycle.State.CREATED)
        setupObservers()
        setupVIew(itemView)
    }

    fun onstart() {
        lifeCycleRegistry.markState(Lifecycle.State.STARTED)
        lifeCycleRegistry.markState(Lifecycle.State.RESUMED)
    }

    fun onStop() {
        lifeCycleRegistry.markState(Lifecycle.State.STARTED)
        lifeCycleRegistry.markState(Lifecycle.State.CREATED)
    }

    fun onDestroy() {
        lifeCycleRegistry.markState(Lifecycle.State.DESTROYED)
    }

    abstract fun setupVIew(itemView: View)

    protected open fun setupObservers() {
        viewModel.messageString.observe(
            this, Observer {
                it.data?.run {
                    showMessage(this)
                }
            }
        )

        viewModel.messageStringId.observe(
            this, Observer {
                it.data?.run {
                    showMessage(this)
                }
            }
        )
    }

    fun showMessage(@StringRes s: Int) = showMessage(itemView.context.getString(s))

    fun showMessage(s: String) {
        Toaster.show(itemView.context, s)
    }

    private fun buildViewHolderComponent() =
        DaggerViewHolderComponent.builder()
            .applicationComponent((itemView.context.applicationContext as InstagramApplication).applicationComponent)
            .viewHolderModule(ViewHolderModule(this))
            .build()

    protected abstract fun injectDependencies(viewHolderComponent: ViewHolderComponent)

}