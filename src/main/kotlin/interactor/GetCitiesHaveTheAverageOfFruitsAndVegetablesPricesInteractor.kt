package interactor

import model.CityEntity

class GetCitiesHaveTheAverageOfFruitsAndVegetablesPricesInteractor
    (private val dataSource: CostOfLivingDataSource) {

    fun execute(limit: Int): List<String> {
        return dataSource.getAllCitiesData()
            .filter(::excludeNullSalariesAndPrices)
            .sortedByDescending {
                it.averageMonthlyNetSalaryAfterTax!! / (getAverageOfFruitsAndVegetables(it))
            }
            .take(limit)
            .map { it.cityName }
            .takeIf { it.isNotEmpty() && it.size == limit } ?: emptyList()
    }

    private fun excludeNullSalariesAndPrices(city: CityEntity): Boolean {
        return city.averageMonthlyNetSalaryAfterTax != null &&
                with(city.fruitAndVegetablesPrices){
                    apples1kg != null && banana1kg != null &&
                    lettuceOneHead != null && onion1kg != null &&
                    tomato1kg != null && potato1kg != null && oranges1kg != null
                }
    }

    private fun getAverageOfFruitsAndVegetables(city: CityEntity): Float {
        return with(city.fruitAndVegetablesPrices) {
                    apples1kg!! + banana1kg!! + lettuceOneHead!! +
                    onion1kg!! + tomato1kg!! + potato1kg!! + oranges1kg!!
                } / NUMBER_OF_FRUITS_AND_VEGETABLES
    }

    private companion object {
        const val NUMBER_OF_FRUITS_AND_VEGETABLES = 7
    }
}