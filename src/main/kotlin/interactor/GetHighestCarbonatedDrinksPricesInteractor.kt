package interactor

import model.CityEntity


class GetHighestCarbonatedDrinksPricesInteractor(private val dataSource: CostOfLivingDataSource) {
    fun execute(limit : Int): List<Pair<String, Float>> {

        val city: List<CityEntity> = dataSource.getAllCitiesData()
            .filter { nullAndLowQualityDrinks(it) }

        return if (city.isEmpty()) {
            emptyList()
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
    private fun avgPriceForAListOfCities(list: List<CityEntity>) :Float {
    var avgPrices =0f
        list.forEach {
            avgPrices += it.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants!!
        }
        return avgPrices/list.size
    }


    //a function to return the average prices for a whole country
    private fun avgpricesforcountries(list: List<List<CityEntity>>):List<Float> {
        val listofprices = mutableListOf<Float>()
        var finalvalue = 0f
        list.forEach {
            listofprices.add(avgPriceForAListOfCities(it))
        }

        return  listofprices
    }

    //  sort the list into a sorted set
    private fun listOfCountries(list: List<CityEntity>, limit: Int) : List<Pair<String, Float>>  {
        var finallist : List<Pair<String, Float>>
        val listOFPrices = list.groupBy { it.country }
        val PricesList = avgpricesforcountries(listOFPrices.values.toList())
        finallist =listOFPrices.keys.toList().zip(PricesList)
//        list.forEach { cityEntity ->
//            finallist .add(Pair(cityEntity.country,avgpricesforcountries(list.filter{cityEntity.country ==it.country })))
//
//        }
return finallist.sortedByDescending { it.second }.take(limit)
    }
}
