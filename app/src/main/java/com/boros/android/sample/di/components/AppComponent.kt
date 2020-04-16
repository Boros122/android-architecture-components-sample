package com.boros.android.sample.di.components

import android.content.Context
import com.boros.android.sample.BaseApplication
import com.boros.android.sample.di.ViewModelBuilder
import com.boros.android.sample.di.modules.*
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