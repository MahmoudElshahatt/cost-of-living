package interactor

import dataSource.FakeDataSource
import dataSource.HardCodedFakeDataSource
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.function.Executable

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GetCityNameAndSalrayAverageInteractorTest {

    private  lateinit var fakeData: HardCodedFakeDataSource
    private lateinit var getcityandsalary: GetCityNameAndSalrayAverageInteractor

    @BeforeAll
    fun init() {
        fakeData = HardCodedFakeDataSource()
        getcityandsalary = GetCityNameAndSalrayAverageInteractor(fakeData)
    }
    @Test
    fun should_ReturnlistOfPairs_When_DataValid()
    {
        //given Valid data
        fakeData.setDatatype(HardCodedFakeDataSource.DataType.VALID)
        //when list not equal null
        val listOfCityAndSalary = getcityandsalary.execute("eGypt")
        //then Return ListOfPair
        assertTrue(listOfCityAndSalary.isNotEmpty())
    }

    @Test
    fun should_returnlistEmpty_When_EnterDataWrong(){
        //Given  UnValid data
        fakeData.setDatatype(HardCodedFakeDataSource.DataType.MIXED)
        //when list  equal null
        val listOfCityAndSalary = Executable{ getcityandsalary.run{
            execute("ex")
        }}
        //then Exception because of Return  Empty_list
        assertThrows(Exception::class.java,listOfCityAndSalary)
    }

    fun should_returnlistEmpty_When_EnterDataNull(){
        //given Null data
        fakeData.setDatatype(HardCodedFakeDataSource.DataType.NULLABLE)
        //when list  equal null
        val listOfCityAndSalary = Executable{ getcityandsalary.run{
            execute("")
        }}
        //then return Exception because of Return  Empty_list
        assertThrows(Exception::class.java,listOfCityAndSalary)
    }






}