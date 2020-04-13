package com.boros.android.starter.di.components

import android.content.Context
import com.boros.android.starter.BaseApplication
import com.boros.android.starter.di.ViewModelBuilder
import com.boros.android.starter.di.modules.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            ViewModelBuilder::class,
            RepositoryModule::class,
            StorageModule::class,
            ApiModule::class,
            NetworkModule::class,
            DatabaseModule::class,
            MemoryCacheModule::class
        ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(application: BaseApplication)

    fun mainComponent(): MainComponent.Factory

}