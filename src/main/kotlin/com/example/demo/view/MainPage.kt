package com.example.demo.view

import kotlinx.html.*
import kotlinx.html.stream.createHTML

fun mainPage() = createHTML().html {
    head {
        getBootstrap()
    }
    body(classes = "container-fluid") {
        form(method = FormMethod.get) {
            div(classes = "placeholder")
            submitInput(classes = "btn btn-primary col-4 input-group mb-3") {
                formAction = "/calculator"
                value = "Калькулятор"
            }
            submitInput(classes = "btn btn-primary col-4 input-group mb-3") {
                formAction = "/converter"
                value = "Конвнретер"
            }
        }
    }


}

fun main() {
    print(mainPage())
}