package interactor

import dataSource.FakeDataSource
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GetCitiesHaveTheAverageOfFruitsAndVegetablesPricesInteractorTest{

   private lateinit var getCitiesHaveTheAverageOfFruitsAndVegetablesPricesInteractor:
            GetCitiesHaveTheAverageOfFruitsAndVegetablesPricesInteractor
   private lateinit var fakeData: FakeDataSource
    @BeforeAll
    fun setup() {
        fakeData = FakeDataSource()
        getCitiesHaveTheAverageOfFruitsAndVegetablesPricesInteractor =
            GetCitiesHaveTheAverageOfFruitsAndVegetablesPricesInteractor(fakeData)
    }

    @Test
    fun should_ReturnTheTenCitiesName_When_TheDataIsValid() {
        //given the valid data and limit the result by 10
        val limit = 10
        fakeData.setDataType(FakeDataSource.DataType.VALID)
        //When the limit equal to size of existing data
        val result = getCitiesHaveTheAverageOfFruitsAndVegetablesPricesInteractor.execute(limit)
        //Then return true if the result is list of ten cities names
        assertTrue(result.size == limit)
    }

    @Test
    fun should_ReturnEmptyList_When_TheDataIsNull() {
        //given null data
        fakeData.setDataType(FakeDataSource.DataType.NULLABLE)
        //When the result is empty
        val result = getCitiesHaveTheAverageOfFruitsAndVegetablesPricesInteractor.execute(10)
        //Then return true if the result is empty
        assertTrue(result.isEmpty())
    }

    @Test
    fun should_ReturnTheTenCitiesName_When_TheDataIsMix() {
        //given mixed data and limit the result by 10
        val limit = 10
        fakeData.setDataType(FakeDataSource.DataType.MIXTURE)
        //When the limit equal to size of existing data
        val result = getCitiesHaveTheAverageOfFruitsAndVegetablesPricesInteractor.execute(limit)
        //Then return true if the result is list of ten cities names
        assertTrue(result.size == limit)
    }

    @Test
    fun should_ReturnEmptyList_When_EnterNotEnoughData() {
        //given the valid Data and limit the result by 30
        val limit = 30
        fakeData.setDataType(FakeDataSource.DataType.VALID)
        //When limit is more than the size of existing data
        val result = getCitiesHaveTheAverageOfFruitsAndVegetablesPricesInteractor.execute(limit)
        //Then return emptyList
        assertEquals(emptyList<String>(), result)
    }



}