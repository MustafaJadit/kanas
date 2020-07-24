package com.kodyuzz.kanas

import android.content.Context
import com.kodyuzz.kanas.di.component.TestComponent
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestComponentRule(private val context: Context) : TestRule {


    var testComponent: TestComponent? = null

    fun getContext() = context

    private fun setupDaggerTestComponentInApplication(){
        val application=context.applicationContext as InstagramApplication

    }

    override fun apply(base: Statement?, description: Description?): Statement {
        TODO("Not yet implemented")
    }

}