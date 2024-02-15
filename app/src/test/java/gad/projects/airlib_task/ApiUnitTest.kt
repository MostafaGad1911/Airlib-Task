package gad.projects.airlib_task

import gad.projects.airlib_task.data.datasource.api.entities.AssociatedDrug
import gad.projects.airlib_task.data.repository.AirLibApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ApiUnitTest {

    private  var mockApiService: AirLibApi = AirLibApi.getInstance()

    @Test
    fun testProblemsNotEmpty() {
        runBlocking {
            val response = mockApiService.getProblems()
            assert(response.problems.isNotEmpty())
        }
    }

    @Test
    fun testAssociatedDrugsNotEmpty() {
        runBlocking {
            val associatedDrugs:ArrayList<AssociatedDrug>  = ArrayList()
            val medicationClasses = mockApiService.getProblems().problems.first().Diabetes.first().medications.first().medicationsClasses

            for (medicationClass in medicationClasses) {
                associatedDrugs.addAll(
                    medicationClass.className.first().associatedDrug
                )
                associatedDrugs.addAll(
                    medicationClass.className2.first().associatedDrug
                )
            }
            assert(associatedDrugs.isNotEmpty())
        }

    }
}