package interactor

import model.CityEntity

class GetTopCitiesNamesForShoppingClothesFromFamousBrandsInteractor(
    private val dataSource: CostOfLivingDataSource,
) {

    fun execute(limit: Int): List<String> {
        return dataSource
            .getAllCitiesData()
            .filter(::excludeNullClothesPricesAndBrandClothesData)
            .sortByClothesPrices()
            .take( limit )
            .map { it.cityName }
    }


    private fun excludeNullClothesPricesAndBrandClothesData(city: CityEntity): Boolean {
        return city.clothesPrices.onePairOfJeansLevis50oneOrSimilar != null &&
                city.clothesPrices.onePairOfMenLeatherBusinessShoes != null &&
                city.clothesPrices.onePairOfNikeRunningShoesMidRange != null &&
                city.clothesPrices.oneSummerDressInAChainStoreZaraHAndM != null
    }


    private fun List<CityEntity> .sortByClothesPrices(): List<CityEntity> {
        return this.sortedByDescending { it.clothesPrices.onePairOfJeansLevis50oneOrSimilar!! +
                it.clothesPrices.onePairOfNikeRunningShoesMidRange!! +
                it.clothesPrices.oneSummerDressInAChainStoreZaraHAndM!! +
                it.clothesPrices.onePairOfMenLeatherBusinessShoes!! }
    }


}
