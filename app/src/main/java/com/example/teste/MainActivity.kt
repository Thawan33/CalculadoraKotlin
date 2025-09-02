package com.example.teste

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import entities.MathOperations


class MainActivity : AppCompatActivity() {
    private lateinit var textCalc: TextView
    private var currentInput = ""
    val math = MathOperations();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textCalc = findViewById(R.id.textCalc)

        val btn0 = findViewById<Button>(R.id.buttonCalc0)
        val btn1 = findViewById<Button>(R.id.buttonCalc1)
        val btn2 = findViewById<Button>(R.id.buttonCalc2)
        val btn3 = findViewById<Button>(R.id.buttonCalc3)
        val btn4 = findViewById<Button>(R.id.buttonCalc4)
        val btn5 = findViewById<Button>(R.id.buttonCalc5)
        val btn6 = findViewById<Button>(R.id.buttonCalc6)
        val btn7 = findViewById<Button>(R.id.buttonCalc7)
        val btn8 = findViewById<Button>(R.id.buttonCalc8)
        val btn9 = findViewById<Button>(R.id.buttonCalc9)
        val btnadd = findViewById<Button>(R.id.buttonCalcAdd)
        val btnsub = findViewById<Button>(R.id.buttonCalcSub)
        val btnmul = findViewById<Button>(R.id.buttonCalcMul)
        val btndiv = findViewById<Button>(R.id.buttonCalcDiv)
        val btnComma = findViewById<Button>(R.id.buttonCalcComma)
        val btnErase = findViewById<Button>(R.id.buttonCalcBack)
        val btnEqual = findViewById<Button>(R.id.buttonCalcEquals)
        val bntC = findViewById<Button>(R.id.buttonCalcC)


        btn0.setOnClickListener { appendNumber("0") }
        btn1.setOnClickListener { appendNumber("1") }
        btn2.setOnClickListener { appendNumber("2") }
        btn3.setOnClickListener { appendNumber("3") }
        btn4.setOnClickListener { appendNumber("4") }
        btn5.setOnClickListener { appendNumber("5") }
        btn6.setOnClickListener { appendNumber("6") }
        btn7.setOnClickListener { appendNumber("7") }
        btn8.setOnClickListener { appendNumber("8") }
        btn9.setOnClickListener { appendNumber("9") }
        btnadd.setOnClickListener { appendOperator("+") }
        btnsub.setOnClickListener { appendOperator("-") }
        btnmul.setOnClickListener { appendOperator("x") }
        btndiv.setOnClickListener { appendOperator("/") }
        btnComma.setOnClickListener { appendNumber(".") }
        btnErase.setOnClickListener { deleteNum() }
        bntC.setOnClickListener { deleteall() }
        btnEqual.setOnClickListener { calculateExpression() }


    }
    private fun appendNumber(num: String) {
        val lastNumber = currentInput.split(" ", "+", "-", "x", "/").lastOrNull() ?: ""
        if (num == "." && lastNumber.contains(".")) return
        currentInput += num
        textCalc.text = currentInput
    }
    private fun appendOperator(op: String) {
        val trimmedInput = currentInput.trim()
        if (trimmedInput.isNotEmpty()) {
            val lastChar = trimmedInput.last()
            if (lastChar != '+' && lastChar != '-' && lastChar != 'x' && lastChar != '/') {
                currentInput += " $op "
                textCalc.text = currentInput
            }
        }
    }

    private fun deleteNum() {
        currentInput = currentInput.dropLast(1)
        textCalc.text = currentInput
    }
    private fun deleteall() {
        currentInput = ""
        textCalc.text = currentInput
    }
    private fun calculateExpression() {
        try{
            val trimmedInput = currentInput.trim()
            val tokens = currentInput.split(" ")

            if (tokens.isEmpty()) return

            if (trimmedInput.last() in listOf('+', '-', 'x', '/')) {
                Toast.makeText(this, "Último caractere não pode ser operador!", Toast.LENGTH_SHORT).show()
                return
            }


            val values = tokens.filterIndexed { index, _ -> index % 2 == 0 }.map { it.toDouble() }.toMutableList()
            val ops = tokens.filterIndexed { index, _ -> index % 2 != 0 }.toMutableList()

            var i = 0
            while (i < ops.size) {
                when (ops[i]) {
                    "x" -> {
                        values[i] = math.multiply(values[i], values[i + 1])
                        values.removeAt(i + 1)
                        ops.removeAt(i)
                        i--
                    }
                    "/" -> {
                        values[i] = math.divide(values[i], values[i + 1])
                        values.removeAt(i + 1)
                        ops.removeAt(i)
                        i--
                    }
                }
                i++
            }
            i = 0
            while (i < ops.size) {
                when (ops[i]) {
                    "+" -> {
                        values[i] = math.addition(values[i], values[i + 1])
                        values.removeAt(i + 1)
                        ops.removeAt(i)
                        i--
                    }
                    "-" -> {
                        values[i] = math.subtract(values[i], values[i + 1])
                        values.removeAt(i + 1)
                        ops.removeAt(i)
                        i--
                    }
                }
                i++
            }
            val result = values.firstOrNull() ?: 0.0
            textCalc.text = result.toString()
            currentInput = result.toString()
        } catch (e: Exception) {
            textCalc.text = "Error"
            currentInput = ""
        }
    }
}