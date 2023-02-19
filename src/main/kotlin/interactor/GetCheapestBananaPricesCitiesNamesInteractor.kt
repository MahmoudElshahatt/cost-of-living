package interactor

import interactor.GetCheapestBananaPricesCitiesNamesInteractor.Const.DEFAULT_LIMIT
import model.CityEntity

class GetCheapestBananaPricesCitiesNamesInteractor(
    private val dataSource: CostOfLivingDataSource
) {
    fun getCitiesVarArgs(): List<CityEntity> {
        return dataSource.getAllCitiesData()
    }

    fun execute(
        limit: Int = DEFAULT_LIMIT,
        vararg cityEntities: CityEntity
    ): List<String> {
        return cityEntities
            .filter { it.fruitAndVegetablesPrices.banana1kg != null }
            .sortedBy { it.fruitAndVegetablesPrices.banana1kg }
            .take(limit)
            .map { it.cityName }
            .takeIf { it.isNotEmpty() } ?: listOf("No Valid Data is Entered !")
    }

    object Const {
        const val DEFAULT_LIMIT = 10
    }
}
