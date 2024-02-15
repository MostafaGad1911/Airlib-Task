package gad.projects.airlib_task.data.repository

import gad.projects.airlib_task.data.datasource.api.AirLibAPIDataSource
import gad.projects.airlib_task.data.datasource.api.entities.Problem

class AirLibApiImp : AirLibAPIDataSource {
    override suspend fun getProblems(): List<Problem> {
        return AirLibApi.getInstance().getProblems().problems
    }


}