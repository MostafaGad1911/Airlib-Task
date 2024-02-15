package gad.projects.airlib_task.core.manager.networkconnectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NetworkConnectivityManagerImpl(private val appContext: Context) : NetworkConnectivityManager {

    companion object {
        private const val LOG = "NetworkConnectivity"
    }

    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    private var networkCallback: ConnectivityManager.NetworkCallback? = null

    private var availabelNetworks = mutableSetOf<Network>()

    private var _isNetworkOnlineFlow = MutableStateFlow(checkIfNetworkActive(context = appContext))

    /**
     * Get the current network status. True if online; otherwise false
     * [startMonitoringNetworkStatus] should be called before using this property
     */
    override val isNetworkOnline get() = _isNetworkOnlineFlow.value

    /**
     * Get flow of network status.
     * Notes:
     * - [startMonitoringNetworkStatus] should be called before using this property
     * - Internally this is a StateFlow so it gives the current network status whenever a collector added.
     */
    override val isNetworkOnlineFlow: Flow<Boolean> = _isNetworkOnlineFlow.asStateFlow()


    //private var _isNetworkOnline = MutableStateFlow(checkIfNetworkActive())
    // private var currentNetworkStatusFlow: Flow<Boolean>? = null
    //private var _networkStatus = checkIfNetworkActive()


    /**
     * Start receiving the latest network status updates.
     * This should be called before using [isNetworkOnline] or [isNetworkOnlineFlow] in order to
     */
    @Synchronized
    override fun startMonitoringNetworkStatus() {
        Log.d("NetworkConnectivity", "startMonitoringNetworkStatus()")

        if (networkCallback != null) {
            return
        }

        networkCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                Log.d(LOG, "onAvailabel(): network $network")
                availabelNetworks.add(network)
                updateConnectivity()
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                Log.d(LOG, "onLost(): network $network")
                availabelNetworks.remove(network)
                updateConnectivity()
            }

            private fun updateConnectivity() {
                Log.d(LOG, "Availabel Networks count: ${availabelNetworks.size}")
                _isNetworkOnlineFlow.value = availabelNetworks.isNotEmpty()
            }
        }
        getConnectivityManager(context = appContext).registerNetworkCallback(
            networkRequest,
            networkCallback!!
        )
    }

    override fun stopMonitoringNetworkStatus() {
        Log.d(LOG, "StopMonitoringNetworkStatus()")
        networkCallback?.let {
            getConnectivityManager(context = appContext).unregisterNetworkCallback(it)
            networkCallback = null
            availabelNetworks.clear()
        }
    }

    //region Private methods
    private fun checkIfNetworkActive(context: Context): Boolean {
        return getConnectivityManager(context = context).activeNetwork != null
    }

    private fun getConnectivityManager(context: Context): ConnectivityManager {
        return context.getSystemService(ConnectivityManager::class.java)
    }
}
