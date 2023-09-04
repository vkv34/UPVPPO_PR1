package com.example.demo.controller

import com.example.demo.model.Currency
import com.example.demo.model.MathAction
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class HomeController {

    @GetMapping("/")
    fun getHome(
        model: Model,
        @RequestParam(value = "name", required = false) name: String?
    ) = "main_view"

    @GetMapping("/calculator")
    fun getCalculatorPage() = "calculator_view"

    @PostMapping("/calculator/results")
    fun postCalculationResults(
        @RequestParam(value = "expression", required = true) expression: String,
        model: Model
    ): String {
        val mathExpression = MathAction.Expression(expression)
        val result = mathExpression.getResult()
        model.addAttribute(
            "result",
            if (result.isNaN() || result.isInfinite()) "Не возможно рассчитать" else result
        )
        return "calculator_result_view"
    }

    @GetMapping("/converter")
    fun getConverterView(model: Model): String {

        model.addAttribute("currencies", Currency.values())
        return "converter_view"
    }


    @PostMapping("/converter/result")
    fun postConverterResults(
        @RequestParam(value = "value", required = true) value: String,
        @RequestParam(value = "fromCurrency", required = true) fromCurrency: String,
        @RequestParam(value = "toCurrency", required = true) toCurrency: String,
        model: Model
    ): String {
        val from = Currency.parse(fromCurrency)
        val to = Currency.parse(toCurrency)
        requireNotNull(from)
        requireNotNull(to)
        val convertingValue = try {
            value.toDouble()
        } catch (e: Exception) {
            Double.NaN
        }
        model.addAttribute(
            "result",
            if (convertingValue.isNaN()) "Не возможно рассчитать" else from.convert(convertingValue, to)
        )
        return "calculator_result_view"
    }

}