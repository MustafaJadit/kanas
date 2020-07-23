package com.kodyuzz.kanas.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.kodyuzz.kanas.R
import com.kodyuzz.kanas.di.component.ActivityComponent
import com.kodyuzz.kanas.ui.base.BaseActivity
import com.kodyuzz.kanas.ui.login.LoginActivity
import com.kodyuzz.kanas.ui.main.MainActivity
import com.kodyuzz.kanas.utils.common.Event
import java.util.*

class SplashActivity : BaseActivity<SplashViewModel>() {

    companion object {
        const val TAG = "SplashActivity"
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun provideLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun setupView(savedInstanceState: Bundle?) {
    }


    override fun setupObservers() {
        super.setupObservers()
        viewModel.launchLogin.observe(this, Observer<Event<Map<String, String>>> {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext, LoginActivity::class.java))
            }
        })

        viewModel.launchMain.observe(this, Observer<Event<Map<String, String>>> {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
        })
    }
}