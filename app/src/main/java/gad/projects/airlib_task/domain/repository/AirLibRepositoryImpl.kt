package gad.projects.airlib_task.domain.repository

import gad.projects.airlib_task.data.datasource.api.AirLibAPIDataSource
import gad.projects.airlib_task.data.datasource.api.entities.Problem

class AirLibRepositoryImpl(private val datasource: AirLibAPIDataSource) : AirLibRepository {


    override suspend fun getProblems(): List<Problem> = datasource.getProblems()
}
