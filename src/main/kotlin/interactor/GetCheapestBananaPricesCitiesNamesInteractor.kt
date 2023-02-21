package interactor

import model.CityEntity

class GetCheapestBananaPricesCitiesNamesInteractor(
    private val dataSource: CostOfLivingDataSource
) {

    fun execute(
        limit: Int = DEFAULT_LIMIT,
        vararg cityEntities: CityEntity = dataSource.getAllCitiesData().toTypedArray()
    ): List<String> {
        return cityEntities
            .filter { it.fruitAndVegetablesPrices.banana1kg != null }
            .sortedBy { it.fruitAndVegetablesPrices.banana1kg }
            .take(limit)
            .map { it.cityName }
            .takeIf { it.isNotEmpty() && it.size == limit }
            ?: listOf("Couldn't find Cities that meet your requirements !")
    }

    private companion object {
        const val DEFAULT_LIMIT = 10
    }
}
