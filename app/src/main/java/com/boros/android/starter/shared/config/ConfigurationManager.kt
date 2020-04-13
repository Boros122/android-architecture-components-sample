package com.boros.android.starter.shared.config

import android.content.Context
import android.content.res.AssetManager
import com.google.gson.Gson
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConfigurationManager @Inject constructor(context: Context) {

    private val generalConfigurationModel: GeneralConfigurationModel = Gson().fromJson(InputStreamReader(context.assets.open("general_configuration.json")), GeneralConfigurationModel::class.java)

    fun getMainConfiguration() = generalConfigurationModel.mainConfigurationModel

}