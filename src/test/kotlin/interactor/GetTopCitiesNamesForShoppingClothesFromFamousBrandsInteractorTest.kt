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
        //Given
        fakeData.setDataType(FakeDataSource.DataType.VALID)

        //When
        val list = GetTopCitiesNamesForShoppingClothesFromFamousBrandsInteractor.execute(5)

        //Then
        assertTrue(list.size == 5)
    }

    @Test
    fun should_ReturnEmptyList_When_EnterNoData() {
        //Given
        dataSource.setDatatype(HardCodedFakeDataSource.DataType.EMPTY)

        //When
        val list = GetTopCitiesNamesForShoppingClothesFromFamousBrandsInteractor.execute(0)

        //Then
        assertTrue(list.isEmpty())
    }


}