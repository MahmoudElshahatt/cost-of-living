package dataSource

import com.appmattus.kotlinfixture.decorator.nullability.AlwaysNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import interactor.CostOfLivingDataSource
import model.CityEntity

class FakeDataSource : CostOfLivingDataSource {

    enum class DataType {

        VALID, NULLABLE, LOWQUALITY, MIXTURE
    }

    private var dataType: DataType = DataType.VALID
    fun setDataType(_dataType: DataType) {
        this.dataType = _dataType
    }

    private val normalFixture by lazy {
        kotlinFixture {
            nullabilityStrategy(NeverNullStrategy)
            property(CityEntity::dataQuality) { true }
            property(CityEntity::country) { "United States" }
            property(CityEntity::cityName) { "New York" }
            repeatCount { 20 }
        }
    }

    /**
     * @return data that have high dataQuality and no null values.
     */
    private fun getNormalData() = normalFixture<List<CityEntity>>()

    private val nullableFixture by lazy {
        kotlinFixture {
            nullabilityStrategy(AlwaysNullStrategy)
            property(CityEntity::country) { "United States" }
            property(CityEntity::cityName) { "New York" }
            property(CityEntity::dataQuality) { true }
            repeatCount { 20 }
        }
    }

    /**
     * @return data that have high dataQuality and null values.
     */
    private fun getDataWithNullValues() = nullableFixture<List<CityEntity>>()

    private val lowQualityFixture by lazy {
        kotlinFixture {
            nullabilityStrategy(NeverNullStrategy)
            property(CityEntity::dataQuality) { false }
            property(CityEntity::country) { "United States" }
            property(CityEntity::cityName) { "New York" }
            repeatCount { 20 }
        }
    }

    /**
     * @return list of CityEntity that have low dataQuality and no null values.
     */
    private fun getDataWithLowQuality() = lowQualityFixture<List<CityEntity>>()

    private val mixedFixture by lazy {
        kotlinFixture {
            repeatCount { 20 }
        }
    }

    /**
     * @return list of CityEntity that have mixture of all values.
     */
    private fun getMixedData() =
        normalFixture<List<CityEntity>>() + nullableFixture<List<CityEntity>>()

    /**
     * retrieve data from FakeDataSource depending on the needed type
     * @return List<CityEntity>
     */
    override fun getAllCitiesData(): List<CityEntity> {
        return when (dataType) {
            DataType.VALID -> {
                getNormalData()
            }
            DataType.NULLABLE -> {
                getDataWithNullValues()
            }
            DataType.LOWQUALITY -> {
                getDataWithLowQuality()
            }
            DataType.MIXTURE -> {
                getMixedData()
            }

        }
    }
}
