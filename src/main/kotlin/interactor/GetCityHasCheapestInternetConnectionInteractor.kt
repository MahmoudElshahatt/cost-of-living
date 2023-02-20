package interactor

import model.CityEntity

class GetCityHasCheapestInternetConnectionInteractor(
    private val dataSource: CostOfLivingDataSource,
) {

    fun execute(limit: Int): String {
        return dataSource
            .getAllCitiesData()
            .filter(::excludeNullAverageSalaryAndInterentPrices)
            .sortedByDescending {
                (it.averageMonthlyNetSalaryAfterTax?.div(
                    it.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl!!
                ))
            }
            .take(limit)
            .map { it.cityName }
            .takeIf { it.isNotEmpty() }
            ?.first() ?: ""
    }

    private fun excludeNullAverageSalaryAndInterentPrices(city: CityEntity): Boolean {
        return city.averageMonthlyNetSalaryAfterTax != null
                && city.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl != null
    }


}

