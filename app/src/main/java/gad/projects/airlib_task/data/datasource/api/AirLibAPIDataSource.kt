package gad.projects.airlib_task.data.datasource.api

import gad.projects.airlib_task.data.datasource.api.entities.Problem

interface AirLibAPIDataSource {
    suspend fun getProblems(): List<Problem>
}