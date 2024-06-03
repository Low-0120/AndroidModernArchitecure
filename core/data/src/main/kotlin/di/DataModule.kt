package di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import util.ConnectivityManagerNetworkMonitor
import util.NetworkMonitor

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor
    ):NetworkMonitor
}