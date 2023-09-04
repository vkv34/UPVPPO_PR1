package com.example.demo.model


enum class Currency(val exchangeRate: Double, val currencyName: String) {
    RUB(1.0, "RUB"),
    EUR(60.0, "EUR"),
    USD(100.0, "USD");

    fun convert(value: Double, currency: Currency) = value * ((1 / this.exchangeRate) * currency.exchangeRate)

    companion object{
        @JvmStatic
        fun parse(string: String) = Currency.values().firstOrNull { it.currencyName == string }
    }


}