package gad.projects.airlib_task.domain.usecase

import gad.projects.airlib_task.data.datasource.api.entities.Problem
import gad.projects.airlib_task.domain.repository.AirLibRepository

interface GetProblemsUseCase {
    suspend operator fun invoke(): List<Problem>
}
