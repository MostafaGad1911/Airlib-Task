package gad.projects.airlib_task.core.manager.networkconnectivity

import kotlinx.coroutines.flow.Flow

interface NetworkConnectivityManager {

    /**
     * Get the current network status. True if online; otherwise false
     * [startMonitoringNetworkStatus] should be called before using this property
     */
    val isNetworkOnline: Boolean

    /**
     * Get flow of network status.
     * Notes:
     * - [startMonitoringNetworkStatus] should be called before using this property
     * - Internally this is a StateFlow so it gives the current network status whenever a collector added.
     */
    val isNetworkOnlineFlow: Flow<Boolean>

    /**
     * Start receiving the latest network status updates.
     * This should be called before using [isNetworkOnline] or [isNetworkOnlineFlow] in order to
     */
    fun startMonitoringNetworkStatus()

    /**
     * Stop receiving the latest network status updates. This should be called when receiving network
     * status is no longer needed
     */
    fun stopMonitoringNetworkStatus()

}
