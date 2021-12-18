package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var textViewInput: TextView? = null
    var lastDot: Boolean = false
    var lastNumeric: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewInput = findViewById(R.id.answerTextField)
        textViewInput?.text = ""
    }

    fun onDigit(view: View) {
        val btn = view as Button
        textViewInput?.append(btn.text)
        val txt = btn.text
        if(txt != "/" && txt != "+" && txt != "/" && txt != "-") {
            this.lastNumeric = true
        }
    }

    fun onClear(view: View) {
        textViewInput?.text = ""
        this.lastDot = false
        this.lastNumeric = false
    }

    fun onEquals(view: View) {
        if(this.lastNumeric) {
            textViewInput?.text.let {
                var stringToCalculate = it.toString()
                    if ((stringToCalculate).contains("-")) {
                        var prefix = ""
                        try {
                            if(stringToCalculate.startsWith("-")) {
                                prefix = "-"
                                stringToCalculate = stringToCalculate.substring(1)
                            }
                            val v = (stringToCalculate).split("-")
                            val firstValue = v[0]
                            val secondValue = v[1]
                            var result = 0.0
                            if(prefix == "") {
                                result = firstValue.toDouble() - secondValue.toDouble()
                            } else {
                                result = firstValue.toDouble() + secondValue.toDouble()
                            }
                            this.textViewInput?.text = prefix + result.toString()
                        } catch (e: Exception) {
                            this.textViewInput?.text = "INVALID"
                        }
                    } else if(stringToCalculate.contains("+")) {
                    try {
                        val v = (stringToCalculate).split("+")
                        val firstValue = v[0]
                        val secondValue = v[1]
                        var result = ""
                        if(stringToCalculate.startsWith("-")) {
                            result = (secondValue.toDouble() - firstValue.toDouble()).toString()
                        } else {
                            result = (secondValue.toDouble() + firstValue.toDouble()).toString()
                        }
                        this.textViewInput?.text = result
                    } catch (e: Exception) {
                        this.textViewInput?.text = "INVALID"
                    }
                } else if(stringToCalculate.contains("*")) {
                        try {
                            val v = (stringToCalculate).split("*")
                            val firstValue = v[0]
                            val secondValue = v[1]
                            var result = (firstValue.toDouble() * secondValue.toDouble()).toString()
                            this.textViewInput?.text = result
                        } catch (e: Exception) {
                            this.textViewInput?.text = "INVALID"
                        }
                    } else if(stringToCalculate.contains("/")) {
                        try {
                            val v = (stringToCalculate).split("/")
                            val firstValue = v[0]
                            val secondValue = v[1]
                            var result = (firstValue.toDouble() / secondValue.toDouble()).toString()
                            this.textViewInput?.text = result
                        } catch (e: Exception) {
                            this.textViewInput?.text = "INVALID"
                        }
                    }
            }
        }
    }

    fun onDecimalPoint(view: View) {
        if(lastNumeric && !lastDot) {
            textViewInput?.append((view as Button).text)
            this.lastNumeric = false
            this.lastDot = true
        }
    }

    fun onOperator(view: View) {
        textViewInput?.text.let {
            if(this.lastNumeric && !this.isOperatorAdded(it.toString())) {
                textViewInput?.append((view as Button).text)
                this.lastNumeric = false
                this.lastDot = false
            }
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }
}