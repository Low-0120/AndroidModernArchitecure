package util

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor{
    val isOnline :Flow<Boolean>
}