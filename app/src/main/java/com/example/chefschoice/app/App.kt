package com.example.chefschoice.app
import android.app.Application
import com.example.chefschoice.data.network.remote.api.IApiService
import com.example.chefschoice.data.network.remote.service.ServiceBuilder
import com.example.chefschoice.data.repository.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App: Application() {


    lateinit var repository: AppRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {

        val iApi = ServiceBuilder.getInstance().create(IApiService::class.java)
        //  No need to cancel this scope as it'll be torn down with the process
        // No need to cancel this scope as it'll be torn down with the process


//        val applicationScope = CoroutineScope(SupervisorJob())
//
//        // Using by lazy so the database and the repository are only created when they're needed
//        // rather than when the application starts
//        val database by lazy { WordRoomDatabase.getDatabase(this, applicationScope) }
//        repository = AppRepository(iApi,database.wordDao())


        repository = AppRepository(iApi)

    }

}