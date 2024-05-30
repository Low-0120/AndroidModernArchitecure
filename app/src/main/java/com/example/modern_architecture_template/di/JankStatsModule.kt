package com.example.modern_architecture_template.di

import android.util.Log
import android.view.Window
import androidx.metrics.performance.JankStats
import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object JankStatsModule {
    @Provides
    fun providesOnFrameListener():JankStats.OnFrameListener = JankStats.OnFrameListener { frameData ->
        if (frameData.isJank){
            Log.v("MAT",frameData.toString())
        }
    }

    @Provides
    fun providesWindows(activity: Activity):Window = activity.window

    @Provides
    fun providesJankStats(
        window: Window,
        frameListener: JankStats.OnFrameListener,
    ):JankStats = JankStats.createAndTrack(window,frameListener)
}