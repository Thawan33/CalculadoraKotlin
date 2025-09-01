package entities

class MathOperations {

    fun addition(a: Double, b: Double): Double{
        return a + b;
    }
    fun subtract(a: Double, b: Double): Double{
        return a - b;
    }
    fun multiply(a: Double, b: Double): Double{
        return a * b;
    }
    fun divide(a: Double, b: Double): Double{
        if (b == 0.0) throw ArithmeticException("Division by zero not allowed")
        return a / b;
    }

}