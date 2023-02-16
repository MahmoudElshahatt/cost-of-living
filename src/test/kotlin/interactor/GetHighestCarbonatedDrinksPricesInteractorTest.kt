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
        //given
        fakeData.setDataType(FakeDataSource.DataType.VALID)
        //when
        val listOfCountries = getHighestCarbonatedDrinksPricesInteractor.execute(10)
        //then
        assertTrue(listOfCountries.toSet().size == 10)
    }

    @Test
    fun should_return_empty_When_Data_LowQuality() {
        //given
        fakeData.setDataType(FakeDataSource.DataType.LOWQUALITY)
        //when
        val listOfCountries = getHighestCarbonatedDrinksPricesInteractor.execute(10)
        //then
        assertTrue(listOfCountries.isEmpty())
    }

    @Test
    fun should_return_empty_List_When_Data_NotFound() {
        //given
        fakeData.setDataType(FakeDataSource.DataType.NULLABLE)

        //when
        val listOfCountries = getHighestCarbonatedDrinksPricesInteractor.execute(10)
        //then return empty list
        assertTrue(listOfCountries.isEmpty())
    }

    @Test
    fun should_return_list_of_available_countries_when_limit_is_greater_than_number_of_countries_available() {
        //given
        fakeData.setDataType(FakeDataSource.DataType.VALID)
        //when
        val limit=99999
        val listOfCountries = getHighestCarbonatedDrinksPricesInteractor.execute(limit)
        //then
        assertTrue(listOfCountries.size<limit)
    }
}