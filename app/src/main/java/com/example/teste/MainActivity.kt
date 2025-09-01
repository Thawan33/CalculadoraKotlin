package com.example.teste

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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
        val btnComma = findViewById<Button>(R.id.buttonCalcComma)
        val btnErase = findViewById<Button>(R.id.buttonCalcBack)

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
        btnComma.setOnClickListener { appendNumber(".") }
        btnErase.setOnClickListener { deleteNum() }
    }
    private fun appendNumber(num: String) {
        if (num !in currentInput || num != "."){
            currentInput += num
            textCalc.text = currentInput
        }
        println(currentInput)
        println(currentInput.toDoubleOrNull())
    }

    private fun deleteNum() {
        currentInput = currentInput.dropLast(1)
        textCalc.text = currentInput
    }
}