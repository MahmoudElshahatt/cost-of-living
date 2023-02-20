package interactor

import dataSource.FakeDataSource
import dataSource.HardCodedFakeDataSource
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertTrue
import java.lang.reflect.Executable
import kotlin.test.assertNull

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GetTopCitiesNamesForShoppingClothesFromFamousBrandsInteractorTest {
    private lateinit var dataSource: HardCodedFakeDataSource
    private lateinit var fakeData: FakeDataSource
    private lateinit var GetTopCitiesNamesForShoppingClothesFromFamousBrandsInteractor:
            GetTopCitiesNamesForShoppingClothesFromFamousBrandsInteractor

    @BeforeAll
    fun init() {
        dataSource = HardCodedFakeDataSource()
        fakeData = FakeDataSource()
        GetTopCitiesNamesForShoppingClothesFromFamousBrandsInteractor =
            GetTopCitiesNamesForShoppingClothesFromFamousBrandsInteractor(fakeData)
    }

    @Test
    fun should_ReturnCorrectList_When_EnterValidData() {
        //Given data in fake data class
        fakeData.setDataType(FakeDataSource.DataType.VALID)

        //When input is 5
        val list = GetTopCitiesNamesForShoppingClothesFromFamousBrandsInteractor.execute(5)

        //Then if the list size is equal to 5 then it is valid
        assertTrue(list.size == 5)
    }

    @Test
    fun should_ReturnEmptyList_When_EnterNoData() {
        //Given data in fake data class
        dataSource.setDatatype(HardCodedFakeDataSource.DataType.EMPTY)

        //When input is empty
        val list = GetTopCitiesNamesForShoppingClothesFromFamousBrandsInteractor.execute(0)

        //Then  if the list size is empty then it is invalid
        assertTrue(list.isEmpty())
    }


}