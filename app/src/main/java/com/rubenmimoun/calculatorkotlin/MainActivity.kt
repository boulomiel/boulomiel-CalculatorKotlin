package com.rubenmimoun.calculatorkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var input : TextView
    private lateinit var btn1 : Button
    private lateinit var btn2 : Button
    private lateinit var btn3 : Button
    private lateinit var btn4 : Button
    private lateinit var btn5 : Button
    private lateinit var btn6 : Button
    private lateinit var btn7 : Button
    private lateinit var btn8 : Button
    private lateinit var btn9 : Button
    private lateinit var btn0 : Button
    private lateinit var btnPlus : Button
    private lateinit var btnMinus: Button
    private lateinit var btnMulti : Button
    private lateinit var btnDivide : Button
    private lateinit var btnClean : Button
    private lateinit var btnPercent : Button
    private lateinit var btnPsik : Button
    private lateinit var btnResult : Button
    private var inputScreen : String = ""
    private var operations = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBtns()


    }

    override fun onClick(v: View?) {


        when(v?.id){
            R.id.zero_btn -> addNumber("0")
            R.id.one_btn -> addNumber("1")
            R.id.two_btn -> addNumber("2")
            R.id.three_btn -> addNumber("3")
            R.id.four_btn -> addNumber("4")
            R.id.five_btn -> addNumber("5")
            R.id.six_btn -> addNumber("6")
            R.id.seven_btn -> addNumber("7")
            R.id.eight_btn -> addNumber("8")
            R.id.nine_btn -> addNumber("9")
            R.id.divide_btn -> operation("/")
            R.id.multi_btn -> operation("*")
            R.id.percent_btn -> operation("%")
            R.id.minus_btn -> operation("-")
            R.id.plus_btn -> operation("+")
            R.id.clean_btn -> operation("ac")
            R.id.psik_btn -> input.text = "."
            R.id.result_btn -> operation("=")

        }



    }
    private  fun addNumber(number : String) {
        input.text  = inputScreen + number
        inputScreen = input.text.toString()
        operations.add(inputScreen)

    }

    private fun operation( sign : String){

        if(sign != "ac") {
            if(sign != "="){
                operations.add(sign)
            }else{
                print(operations.toString())
                getResult(operations)
            }

        }else{
            operations.clear()
            input.text= ""
        }

        inputScreen = ""
    }

    private fun getResult(list: ArrayList<String>) {
        val result =  calculate(list)
        val resultString = result.toString()
        input.text = resultString


    }

    private fun calculate(calculationList: List<String>): Int {
        var currentOp = ""
        var currentNumber = 0

        calculationList.forEach { token ->
            when {
                token == "+"
                        || token == "/"
                        || token == "*"
                        || token == "%"
                        || token == "-" -> currentOp = token

                currentOp == "-" -> currentNumber -= token.toInt()
                currentOp == "/" -> currentNumber /= token.toInt()
                currentOp == "*" -> currentNumber *= token.toInt()
                currentOp == "%" -> currentNumber -=  currentNumber * token.toInt() / 100
                currentOp == "+" -> currentNumber += token.toInt()
                else -> currentNumber = token.toInt()

            }
        }

        return currentNumber
    }




    private fun initBtns(){

        input = findViewById(R.id.screen)
        btn0 = findViewById(R.id.zero_btn)
        btn1 = findViewById(R.id.one_btn)
        btn2 = findViewById(R.id.two_btn)
        btn3 = findViewById(R.id.three_btn)
        btn4 = findViewById(R.id.four_btn)
        btn5 = findViewById(R.id.five_btn)
        btn6 = findViewById(R.id.six_btn)
        btn7 = findViewById(R.id.seven_btn)
        btn8 = findViewById(R.id.eight_btn)
        btn9 = findViewById(R.id.nine_btn)
        btnClean = findViewById(R.id.clean_btn)
        btnDivide = findViewById(R.id.divide_btn)
        btnMinus = findViewById(R.id.minus_btn)
        btnMulti = findViewById(R.id.multi_btn)
        btnPercent = findViewById(R.id.percent_btn)
        btnPsik = findViewById(R.id.psik_btn)
        btnPlus = findViewById(R.id.plus_btn)
        btnResult = findViewById(R.id.result_btn)

        val list = arrayListOf(btn9,btn8,btn7,btn6,btn5,btn4,btn3,
            btn2,btn1,btn0,btnPsik,btnPercent,btnMulti,btnMinus,btnDivide,btnClean,btnPlus,btnResult)

        for ( button in list){
            button.setOnClickListener(this)
        }
    }
}
