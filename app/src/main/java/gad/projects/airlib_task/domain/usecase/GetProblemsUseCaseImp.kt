package gad.projects.airlib_task.domain.usecase

import gad.projects.airlib_task.data.datasource.api.entities.Problem
import gad.projects.airlib_task.domain.repository.AirLibRepository

class GetProblemsUseCaseImp(
    private val repository: AirLibRepository
) : GetProblemsUseCase {

    override suspend fun invoke(): List<Problem> = repository.getProblems()
}