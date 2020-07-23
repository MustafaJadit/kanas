package com.kodyuzz.kanas.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.kodyuzz.kanas.InstagramApplication
import com.kodyuzz.kanas.di.component.ActivityComponent
import com.kodyuzz.kanas.di.component.DaggerActivityComponent
import com.kodyuzz.kanas.di.module.ActivityModule
import com.kodyuzz.kanas.utils.display.Toaster
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {


    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies(buildActivityComponent())
        setContentView(provideLayoutId())
        setupObservers()
        setupView(savedInstanceState)
        viewModel.onCreate()
    }

    private fun buildActivityComponent() = DaggerActivityComponent
        .builder()
        .applicationComponent((application as InstagramApplication).applicationComponent)
        .activityModule(ActivityModule(this))
        .build()


    protected abstract fun injectDependencies(activityComponent: ActivityComponent)

    protected open fun setupObservers() {
        viewModel.messageString.observe(
            this,
            Observer {
                it.data?.run { showMessage(this) }
            }
        )
    }

    fun showMessage(message: String) = Toaster.show(applicationContext, message)

    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    open fun goBack() = onBackPressed()

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStackImmediate()
        else super.onBackPressed()
    }

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun setupView(savedInstanceState: Bundle?)

}