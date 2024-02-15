package gad.projects.airlib_task.domain.repository

import gad.projects.airlib_task.data.datasource.api.entities.Problem

interface AirLibRepository {
    suspend fun getProblems():List<Problem>
}