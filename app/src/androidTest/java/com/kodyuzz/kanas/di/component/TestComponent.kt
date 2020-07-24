package com.kodyuzz.kanas.di.component

import com.kodyuzz.kanas.di.module.ApplicationTestModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationTestModule::class])
interface TestComponent : ApplicationComponent {
}