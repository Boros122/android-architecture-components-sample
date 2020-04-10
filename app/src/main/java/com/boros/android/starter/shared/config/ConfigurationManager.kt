package com.boros.android.starter.shared.config

import android.content.res.AssetManager
import com.google.gson.Gson
import java.io.InputStreamReader

object ConfigurationManager {

    private lateinit var assetManager: AssetManager

    private lateinit var generalConfigurationModel: GeneralConfigurationModel

    fun init(assetManager: AssetManager) {
        this.assetManager = assetManager
        this.generalConfigurationModel = Gson().fromJson(InputStreamReader(ConfigurationManager.assetManager.open("general_configuration.json")), GeneralConfigurationModel::class.java)
    }

    fun getMainConfiguration() = generalConfigurationModel.mainConfigurationModel

}