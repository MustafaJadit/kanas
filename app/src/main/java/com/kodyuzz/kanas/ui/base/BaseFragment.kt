package com.kodyuzz.kanas.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kodyuzz.kanas.InstagramApplication
import com.kodyuzz.kanas.di.component.DaggerFragmentComponent
import com.kodyuzz.kanas.di.component.FragmentComponent
import com.kodyuzz.kanas.di.module.FragmentModule
import com.kodyuzz.kanas.utils.display.Toaster
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    @Inject
    lateinit var viewMode: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies(buildFragmentComponent())
        setupObservers()
        viewMode.onCreate()
    }

    open fun setupObservers() {
        viewMode.messageString.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

        viewMode.messageStringId.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })
    }

    fun showMessage(s: String) {
        context?.let { Toaster.show(it, s) }
    }

    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    fun goBack() {
        if (activity is BaseActivity<*>) (activity as BaseActivity<*>).goBack()
    }

    protected abstract fun injectDependencies(buildFragmentComponent: FragmentComponent)

    private fun buildFragmentComponent(): FragmentComponent {
        return DaggerFragmentComponent.builder()
            .applicationComponent((context?.applicationContext as InstagramApplication).applicationComponent)
            .fragmentModule(FragmentModule(this))
            .build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupVIew(view)
    }

    abstract fun setupVIew(view: View)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(provideLayoutId(), container, false)
    }

    @LayoutRes
    protected abstract fun provideLayoutId(): Int
}