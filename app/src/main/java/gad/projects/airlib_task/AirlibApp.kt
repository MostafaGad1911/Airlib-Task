package gad.projects.airlib_task

import android.app.Application
import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.createDataStore
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger

const val AIRLIP_DATA_STORE = "airlib_data_store"

class AirlibApp : Application() , ImageLoaderFactory {


    companion object {
        lateinit var appContext: Context
        lateinit var dataStore: DataStore<androidx.datastore.preferences.Preferences>
        lateinit var instance: AirlibApp
            private set // Only SweaterApplication can set the instance value

        fun applicationContext(): Context {
            return appContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this.applicationContext
        dataStore = getSpaceDataStore(appContext)
        instance = this
    }

    private fun getSpaceDataStore(context: Context): DataStore<androidx.datastore.preferences.Preferences> = context.createDataStore(AIRLIP_DATA_STORE)

    override fun newImageLoader(): ImageLoader {
        return ImageLoader(this).newBuilder()
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder(this)
                    .strongReferencesEnabled(true)
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .directory(cacheDir)
                    .build()
            }
            .logger(DebugLogger())
            .build()
    }

}