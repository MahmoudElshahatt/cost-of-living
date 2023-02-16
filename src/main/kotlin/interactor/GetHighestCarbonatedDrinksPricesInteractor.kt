package interactor

import model.CityEntity


class GetHighestCarbonatedDrinksPricesInteractor(private val dataSource: CostOfLivingDataSource) {
    fun execute(limit : Int): Set<Pair<String, Float>> {

        val city: List<CityEntity> = dataSource.getAllCitiesData()
            .filter { nullAndLowQualityDrinks(it) }

        return if (city.isEmpty()) {
            emptySet()
        } else if (limit>city.size) {
            listOfCountries(city,city.size)
        }
        else
            listOfCountries(city,limit)
    }

    private fun nullAndLowQualityDrinks(cityEntity: CityEntity): Boolean =

        cityEntity.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants != null &&
                cityEntity.dataQuality



    // function to return the average prices for only one city
    private fun avgPriceForASingleCity(cityEntity: CityEntity) =
        cityEntity.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants!!

    //a function to return the average prices for a whole country
    private fun avgpriceforacountry(list: List<CityEntity>): Float {
        val listofprices = mutableListOf<Float>()
        var finalvalue = 0f
        list.forEach {
            listofprices.add(avgPriceForASingleCity(it))
        }
        listofprices.forEach {
            finalvalue += it
        }
        return  finalvalue / listofprices.size
    }

    // function to sort the list into a sorted set
    private fun listOfCountries(list: List<CityEntity>, limit: Int) :Set<Pair<String,Float>> {
        val finallist = mutableListOf<Pair<String, Float>>()
        list.forEach { cityEntity ->
            finallist .add(Pair(cityEntity.country,avgpriceforacountry(list.filter{cityEntity.country ==it.country })))

        }

        return finallist.toSet().sortedByDescending { it.second }.take(limit).toSet()
    }
}
