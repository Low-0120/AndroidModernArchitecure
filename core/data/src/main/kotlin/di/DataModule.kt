package di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import util.ConnectivityManagerNetworkMonitor
import util.NetworkMonitor
import util.TimeZoneBroadcastMonitor
import util.TimeZoneMonitor

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor
    ):NetworkMonitor

    @Binds
    internal abstract fun bind(impl:TimeZoneBroadcastMonitor):TimeZoneMonitor
}