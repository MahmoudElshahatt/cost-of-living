package interactor

class GetCityNameAndSalrayAverageInteractor(
    private val dataSource: CostOfLivingDataSource
) {

    fun execute(country: String): List<Pair<String, Float>> {
        return dataSource.getAllCitiesData()
            .filter { (it.country.lowercase() == country.lowercase()) && (it.dataQuality) }
            .map { Pair(it.cityName, it.averageMonthlyNetSalaryAfterTax!!) }
            .takeIf { it.isNotEmpty() } ?: throw Exception("Enter Valid Country")

    }

}