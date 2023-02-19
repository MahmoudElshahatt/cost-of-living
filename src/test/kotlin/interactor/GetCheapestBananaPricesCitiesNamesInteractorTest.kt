package interactor

import dataSource.FakeDataSource
import model.CityEntity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GetCheapestBananaPricesCitiesNamesInteractorTest {

    private lateinit var fakeData: FakeDataSource
    private lateinit var getCheapestBananaPricesCitiesNamesInteractor: GetCheapestBananaPricesCitiesNamesInteractor

    @BeforeAll
    fun init() {
        fakeData = FakeDataSource()
        getCheapestBananaPricesCitiesNamesInteractor =
            GetCheapestBananaPricesCitiesNamesInteractor(fakeData)
    }

    @Test
    fun should_ReturnCorrectList_When_EnterValidData() {
        //Given data is valid
        fakeData.setDataType(FakeDataSource.DataType.VALID)
        //When valid data is entered
        val list = getCheapestBananaPricesCitiesNamesInteractor.execute(10)
        
        //Then list is sorted and its size = limit
        assertTrue(list.size == 10)
    }

    @Test
    fun should_ReturnNotValidList_When_EnterNullForAllBananaPrices() {
        //Given data is all null
        fakeData.setDataType(FakeDataSource.DataType.NULLABLE)
        //When entering null for all banana prices
        val list = getCheapestBananaPricesCitiesNamesInteractor.execute(10)
        //Then No Valid Data is Entered ! is returned
        assertEquals(listOf("No Valid Data is Entered !"), list)
    }

    @Test
    fun should_ReturnNotValidList_When_EnterNoData() {
        //Given empty array of CityEntities
        val array = emptyArray<CityEntity>()

        //When entering empty array of CityEntities to execute()
        val list = getCheapestBananaPricesCitiesNamesInteractor.execute(10, *array)

        //Then No Valid Data is Entered ! is returned
        assertEquals(listOf("No Valid Data is Entered !"), list)
    }

    @Test
    fun should_ReturnNotValidList_When_EnterNotEnoughData() {
        //Given valid data
        fakeData.setDataType(FakeDataSource.DataType.VALID)

        //When entering empty array of CityEntities to execute()
        val list = getCheapestBananaPricesCitiesNamesInteractor.execute(100)

        //Then No Valid Data is Entered ! is returned
        assertEquals(listOf("No Valid Data is Entered !"), list)
    }


}
