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
        //given
        fakeData.setDataType(FakeDataSource.DataType.VALID)
        //When
        val result = getCitiesHaveTheAverageOfFruitsAndVegetablesPricesInteractor.execute(10)
        //Then
        assertTrue(result.size == 10)
    }

    @Test
    fun should_ReturnTheTenCitiesName_When_TheDataIsMix() {
        //given
        fakeData.setDataType(FakeDataSource.DataType.MIXTURE)
        //When
        val result = getCitiesHaveTheAverageOfFruitsAndVegetablesPricesInteractor.execute(10)
        //Then
        assertEquals(result.size == 10 , result.size == 6)
    }

    @Test
    fun should_ReturnTheTenCitiesName_When_TheDataIsNull() {
        //given
        fakeData.setDataType(FakeDataSource.DataType.NULLABLE)
        //When
        val result = getCitiesHaveTheAverageOfFruitsAndVegetablesPricesInteractor.execute(10)
        //Then
        assertTrue(result.isEmpty())
    }

}