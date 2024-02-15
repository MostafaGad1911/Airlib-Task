package gad.projects.airlib_task.data.repository

import gad.projects.airlib_task.data.datasource.api.entities.Problem
import gad.projects.airlib_task.data.datasource.api.entities.ProblemsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface AirLibApi {
    @GET("93b3fe2b-642a-44fb-bff3-4ddd79f59c22")
    suspend fun getProblems(): ProblemsResponse

    companion object {
        var airLibApi: AirLibApi? = null
        fun getInstance(): AirLibApi {
            if (airLibApi == null) {
                airLibApi = Retrofit.Builder()
                    .baseUrl("https://run.mocky.io/v3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(AirLibApi::class.java)
            }
            return airLibApi!!
        }
    }
}