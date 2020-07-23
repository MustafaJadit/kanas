package com.kodyuzz.kanas.ui.profile

import android.os.Bundle
import android.view.View
import com.kodyuzz.kanas.R
import com.kodyuzz.kanas.di.component.FragmentComponent
import com.kodyuzz.kanas.ui.base.BaseFragment

class ProfileFragment : BaseFragment<ProfileVIewModel>() {
    override fun injectDependencies(buildFragmentComponent: FragmentComponent) {
        buildFragmentComponent.inject(this)
    }

    override fun setupVIew(view: View) {
     }

    override fun provideLayoutId(): Int {
        return R.layout.fragment_profile
    }

    companion object{
        const val TAG="ProfileFragment"

        fun getInstance(): ProfileFragment{
            val args=Bundle()
            val fragment=ProfileFragment()
            fragment.arguments=args
            return fragment

        }
    }

}