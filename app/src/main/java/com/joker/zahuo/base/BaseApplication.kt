package com.joker.zahuo.base

import android.app.Application
import com.joker.zahuo.utils.SophixUtils

/**
 * Created by PengXJ on 2018/8/28.
 */
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SophixUtils.initSophix(this)
    }
}