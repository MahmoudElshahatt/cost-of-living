package interactor



import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance
import dataSource.FakeDataSource
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetHighestCarbonatedDrinksPricesInteractorTest {

    private lateinit var fakeData: FakeDataSource
    private lateinit var getHighestCarbonatedDrinksPricesInteractor: GetHighestCarbonatedDrinksPricesInteractor

    @BeforeAll
    fun first() {
        fakeData = FakeDataSource()
        getHighestCarbonatedDrinksPricesInteractor = GetHighestCarbonatedDrinksPricesInteractor(fakeData)
    }

    @Test
    fun should_return_listOfPairs_When_Data_Valid() {
        //given a valid list of Data
        fakeData.setDataType(FakeDataSource.DataType.VALID)
        //when getting a list of (limit) of the Highest countries Prices
        val listOfCountries = getHighestCarbonatedDrinksPricesInteractor.execute(1)!!
        //then return a list of the limit sorted by descending
        assertTrue(listOfCountries.size==1)
    }

    @Test
    fun should_return_empty_When_Data_LowQuality() {
        //given a list of low quality data
        fakeData.setDataType(FakeDataSource.DataType.LOWQUALITY)
        //when
        val listOfCountries = getHighestCarbonatedDrinksPricesInteractor.execute()!!
        //then return an empty list
        assertTrue(listOfCountries.isEmpty())
    }

    @Test
    fun should_return_empty_List_When_Data_NotFound() {
        //given a list of null values
        fakeData.setDataType(FakeDataSource.DataType.NULLABLE)
        //when
        val listOfCountries = getHighestCarbonatedDrinksPricesInteractor.execute()!!
        //then return an empty list
        assertTrue(listOfCountries.isEmpty())
    }

    @Test
    fun should_return_list_of_available_countries_when_limit_is_greater_than_number_of_countries_available() {
        //given a list of valid Data
        fakeData.setDataType(FakeDataSource.DataType.VALID)
        //when the limit asked is larger than the whole list's size
        val limit=99999
        val listOfCountries = getHighestCarbonatedDrinksPricesInteractor.execute(limit)!!
        //then return a list of the whole size not the limit sorted by descending
        assertTrue( listOfCountries.size<limit)
    }
}