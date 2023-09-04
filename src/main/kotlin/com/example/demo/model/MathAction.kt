package com.example.demo.model

enum class MathAction(val sign: String) : SolveMathAction {
    PLUS("+") {
        override operator fun invoke(a: Double, b: Double) = a + b
    },

    MINUS("-") {
        override operator fun invoke(a: Double, b: Double) = a - b
    },
    MULTIPLY("*") {
        override operator fun invoke(a: Double, b: Double) = a * b
    },
    DIVIDE("/") {
        override operator fun invoke(a: Double, b: Double) = a / b
    };

    companion object {
        @JvmStatic
        fun parseActon(action: String) = MathAction.values().firstOrNull { it.sign == action }
    }

    class Expression(private val expression: String) {


        private fun nextNumber(index: Int): String {
            val sb = StringBuilder()
            for (i in index until expression.length) {
                if (expression[i].isDigit() || expression[i] == '.' || expression[i] == ',')
                    sb.append(if (expression[i] == ',') '.' else expression[i])
                else return sb.toString()
            }
            print(sb.toString())
            return sb.toString()
        }

        private fun nextAction(index: Int): MathAction? = try {
            MathAction.parseActon(expression[index].toString())
        } catch (_: Exception) {
            null
        }

        fun getResult(): Double {

            var currentIndex: Int = 0

            var result: Double = Double.NaN

            while (currentIndex < expression.length) {
                if (result.isNaN()) {
                    val a = nextNumber(currentIndex)
                    currentIndex += a.length
                    val action = nextAction(currentIndex) ?: return if (a.isNotBlank()) a.toDouble() else result
                    currentIndex += action.sign.length
                    val b = nextNumber(currentIndex)
                    currentIndex += b.length
                    result = try {
                        action(a.toDouble(), b.toDouble())
                    } catch (_: Exception) {
                        Double.NaN
                    }
                } else {
                    val action = nextAction(currentIndex) ?: return result
                    currentIndex += action.sign.length
                    val a = nextNumber(currentIndex)
                    currentIndex += a.length

                    result = action(result, a.toDouble())
                }
            }
            return result
        }


    }
}
