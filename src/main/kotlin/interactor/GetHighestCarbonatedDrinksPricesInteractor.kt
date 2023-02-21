package interactor

import model.CityEntity

class GetHighestCarbonatedDrinksPricesInteractor(private val dataSource: CostOfLivingDataSource) {

    fun execute(limit: Int=10): List<Pair<String, Float>>? {

        val city: List<CityEntity> = dataSource.getAllCitiesData().filter { nullAndLowQualityDrinks(it) }

        return when {
            city.isEmpty() -> emptyList()
            city.size > limit -> listOfCountries(city, limit)
            city.size < limit -> listOfCountries(city, city.size)

            else -> null
        }
    }

    private fun nullAndLowQualityDrinks(cityEntity: CityEntity): Boolean =
        cityEntity.run {  drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants != null &&
        dataQuality}


    // function to return the average prices for only one city
    private fun avgPriceForACountry(list: List<CityEntity>): Float {
        var avgPrices = 0f
        list.forEach {
            avgPrices += it.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants!!
        }
        return avgPrices / list.size
    }


    //a function to return the average prices for a whole country
    private fun avgPricesForAListOfCountries(list: List<List<CityEntity>>): List<Float> =
         list.map { avgPriceForACountry(it) }


    //  sort the list into a sorted list of pairs of country and average prices
    private fun listOfCountries(list: List<CityEntity>, limit: Int): List<Pair<String, Float>> {
        val listOfPairsCountriesAndPrices: List<Pair<String, Float>>
        val listOFCountries = list.groupBy { it.country }
        val listOfPrices = avgPricesForAListOfCountries(listOFCountries.values.toList())

        listOfPairsCountriesAndPrices = listOFCountries.keys.toList().zip(listOfPrices)

        return listOfPairsCountriesAndPrices.sortedByDescending { it.second }.take(limit)
    }
}
