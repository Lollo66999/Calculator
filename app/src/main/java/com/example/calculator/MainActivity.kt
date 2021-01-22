package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //numeri
        button12.setOnClickListener {appendOnexpresstion("1", true)}
        button11.setOnClickListener {appendOnexpresstion("2", true)}
        button10.setOnClickListener {appendOnexpresstion("3", true)}
        button8.setOnClickListener {appendOnexpresstion("4", true)}
        button7.setOnClickListener {appendOnexpresstion("5", true)}
        button6.setOnClickListener {appendOnexpresstion("6", true)}
        button4.setOnClickListener {appendOnexpresstion("7", true)}
        button3.setOnClickListener {appendOnexpresstion("8", true)}
        button2.setOnClickListener {appendOnexpresstion("9", true)}
        button16.setOnClickListener {appendOnexpresstion("0", true)}

        //operazioni
        button13.setOnClickListener {appendOnexpresstion("+", false)}
        button9.setOnClickListener {appendOnexpresstion("-", false)}
        button5.setOnClickListener {appendOnexpresstion("/", false)}
        button.setOnClickListener {appendOnexpresstion("*", false)}

        button15.setOnClickListener {
            expressionText.text = ""
            resultsText.text = ""
        }

        button17.setOnClickListener {
            val string = expressionText.text.toString()
            if(string.isNotEmpty()) {
                expressionText.text = string.substring(0,string.length-1)
            }
            resultsText.text = ""
        }

        button14.setOnClickListener {
            try {
                val expression = ExpressionBuilder(expressionText.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result == longResult.toDouble())
                    resultsText.text = longResult.toString()
                else resultsText.text =result.toString()
            }catch (e:Exception) {
                Log.d("exception", "messege : " + e.message)
            }
        }
    }

    fun appendOnexpresstion(string: String, canCLear: Boolean) {

        if(resultsText.text.isNotEmpty()) {
            expressionText.text = ""
        }

        if(canCLear){
            resultsText.text = ""
            expressionText.append(string)
        }else{
            expressionText.append(resultsText.text)
            expressionText.append(string)
            resultsText.text = ""
        }
    }

    fun buttonPressed (v:View ) {
        val b = v as Button
        when(b.text) {
            "9" ->  {
                val number = resultsText.text.toString() + b.text
                resultsText.text = number
            }
        }
    }
}