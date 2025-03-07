import dataSource.CsvDataSource
import dataSource.utils.CsvParser
import interactor.*

fun main() {
    val csvParser = CsvParser()
    val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser)

    val getHighestSalaryAverageCities = GetHighestSalaryAverageCititesNamesInteractor(dataSource)
    println(getHighestSalaryAverageCities.execute(limit = 10))
    printSeparationLine()

    val getCitiesHaveTheAverageOfFruitsAndVegetablesPrices =
        GetCitiesHaveTheAverageOfFruitsAndVegetablesPricesInteractor(dataSource)
    println(getCitiesHaveTheAverageOfFruitsAndVegetablesPrices.execute(10))
    printSeparationLine()

    val getCheapestApartmentsCitiesNamesInteractor = GetCheapestApartmentsCitiesNamesInteractor(dataSource)
    getCheapestApartmentsCitiesNamesInteractor.execute(10, 100).forEach {
        println("Number of years needed to buy a 100m apartment in ${it.first} city is : " + "%.2f".format(it.second))
    }
    printSeparationLine()

    val getCityHasCheapestInternetConnectionInteractor = GetCityHasCheapestInternetConnectionInteractor(dataSource)
    println(getCityHasCheapestInternetConnectionInteractor.execute(1))
    printSeparationLine()

    val getCheapestBananaPricesCitiesNamesInteractor = GetCheapestBananaPricesCitiesNamesInteractor(dataSource)
    getCheapestBananaPricesCitiesNamesInteractor.apply {
        println("Cities with cheapest banana prices are :")
        execute().forEach{ println(it) }
    }
    printSeparationLine()

    val getBestClothesPriceCitiesNameInteractor =
        GetTopCitiesNamesForShoppingClothesFromFamousBrandsInteractor(dataSource)
    println(getBestClothesPriceCitiesNameInteractor.execute(limit = 5))
    printSeparationLine()

    val getCityNameAndSalaryAverageInteractor = GetCityNameAndSalrayAverageInteractor(dataSource)
    println(getCityNameAndSalaryAverageInteractor.execute("Egypt"))
    printSeparationLine()

    val getMostSuitableCityForMoreSavingsPerMonthInteractor =
        GetMostSuitableCityForMoreSavingsPerMonthInteractor(dataSource)
    getMostSuitableCityForMoreSavingsPerMonthInteractor.apply {
        println(execute())
    }
    printSeparationLine()

    val getCityManagerExpectationInteractor = GetCityManagerExpectationInteractor(dataSource)
    println(getCityManagerExpectationInteractor.execute("United States" , "Canada","Mexico"))
    printSeparationLine()

    val getCityHasHighestRentingBetweenApartmentsInteractor = GetCityHasHighestRentingBetweenApartmentsInteractor(dataSource)
    println("The City has highest renting between apartements inside city center and outside is: "
            +getCityHasHighestRentingBetweenApartmentsInteractor.execute().cityName)
    printSeparationLine()

    val getHighestCarbonatedDrinksPricesInteractor = GetHighestCarbonatedDrinksPricesInteractor(dataSource)
    getHighestCarbonatedDrinksPricesInteractor.execute(10)!!.forEach{
        println("country : ${it.first} " +"  average carbonated Drinks prices : ${it.second}")
    }
    printSeparationLine()
}

private fun printSeparationLine() {
    print("\n_______________________________\n")
}
