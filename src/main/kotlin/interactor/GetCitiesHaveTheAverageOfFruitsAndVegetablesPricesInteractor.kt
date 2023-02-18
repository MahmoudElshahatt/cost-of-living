package interactor

import interactor.GetCitiesHaveTheAverageOfFruitsAndVegetablesPricesInteractor
.NumberOfItems.NUMBER_OF_FRUITS_AND_VEGETALES
import model.CityEntity

class GetCitiesHaveTheAverageOfFruitsAndVegetablesPricesInteractor
    (private val dataSource: CostOfLivingDataSource) {

    fun execute(limit: Int): List<String> {
        return dataSource.getAllCitiesData()
            .filter(::excludeNullSalariesAndNullPrices)
            .sortedByDescending {
                it.averageMonthlyNetSalaryAfterTax?.div(averageOfFruitsAndVegetables(it))
            }
            .take( limit )
            .map { it.cityName }
            .takeIf { it.isNotEmpty() } ?: listOf()
    }

    private fun excludeNullSalariesAndNullPrices(city: CityEntity): Boolean {
        return city.averageMonthlyNetSalaryAfterTax != null &&
                city.fruitAndVegetablesPrices.apples1kg != null &&
                city.fruitAndVegetablesPrices.banana1kg != null &&
                city.fruitAndVegetablesPrices.lettuceOneHead != null &&
                city.fruitAndVegetablesPrices.onion1kg != null &&
                city.fruitAndVegetablesPrices.tomato1kg != null &&
                city.fruitAndVegetablesPrices.potato1kg != null &&
                city.fruitAndVegetablesPrices.oranges1kg != null
    }

    private fun averageOfFruitsAndVegetables(city: CityEntity): Float {
        var sum: Float
        with(city.fruitAndVegetablesPrices) {
            sum = apples1kg!! + banana1kg!! + lettuceOneHead!! +
                    onion1kg!! + tomato1kg!! + potato1kg!! + oranges1kg!!
        }
        return (sum / NUMBER_OF_FRUITS_AND_VEGETALES)
    }
    object NumberOfItems {
        const val NUMBER_OF_FRUITS_AND_VEGETALES = 7
    }
}