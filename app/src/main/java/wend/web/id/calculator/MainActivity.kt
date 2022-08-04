package wend.web.id.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    // numbered button
    private var btnZero: Button? = null
    private var btnOne: Button? = null
    private var btnTwo: Button? = null
    private var btnThree: Button? = null
    private var btnFour: Button? = null
    private var btnFive: Button? = null
    private var btnSix: Button? = null
    private var btnSeven: Button? = null
    private var btnEight: Button? = null
    private var btnNine: Button? = null

    // operator button
    private var btnDivide: Button? = null
    private var btnMultiply: Button? = null
    private var btnAdd: Button? = null
    private var btnSubtract: Button? = null
    private var btnEqual: Button? = null
    private var btnCls: Button? = null
    private var btnDot: Button? = null

    // variable
    private var operand1 = 0.0
    private var operand2 = 0.0
    private var operator = ""

    // display
    private var tvCalc: TextView? = null
    private var tvOperator: TextView? = null
    private var tvOperand1: TextView? = null
    private var tvOperand2: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCalc = findViewById(R.id.tvCalc)
        tvOperator = findViewById(R.id.tvOperator)
        tvOperand1 = findViewById(R.id.tvOperand1)
        tvOperand2 = findViewById(R.id.tvOperand2)

        tvCalc?.text = "0"
        tvOperator?.text = ""
        tvOperand1?.text = ""
        tvOperand2?.text = ""

        btnZero = findViewById(R.id.btnZero)
        btnOne = findViewById(R.id.btnOne)
        btnTwo = findViewById(R.id.btnTwo)
        btnThree = findViewById(R.id.btnThree)
        btnFour = findViewById(R.id.btnFour)
        btnFive = findViewById(R.id.btnFive)
        btnSix = findViewById(R.id.btnSix)
        btnSeven = findViewById(R.id.btnSeven)
        btnEight = findViewById(R.id.btnEight)
        btnNine = findViewById(R.id.btnNine)

        btnDivide = findViewById(R.id.btnDivide)
        btnMultiply = findViewById(R.id.btnMultiply)
        btnAdd = findViewById(R.id.btnAdd)
        btnSubtract = findViewById(R.id.btnSubtract)
        btnEqual = findViewById(R.id.btnEqual)
        btnCls = findViewById(R.id.btnCls)
        btnDot = findViewById(R.id.btnDot)

        btnZero?.setOnClickListener{
            setNumber(0)
        }
        btnOne?.setOnClickListener{
            setNumber(1)
        }
        btnTwo?.setOnClickListener{
            setNumber(2)
        }
        btnThree?.setOnClickListener{
            setNumber(3)
        }
        btnFour?.setOnClickListener{
            setNumber(4)
        }
        btnFive?.setOnClickListener{
            setNumber(5)
        }
        btnSix?.setOnClickListener{
            setNumber(6)
        }
        btnSeven?.setOnClickListener{
            setNumber(7)
        }
        btnEight?.setOnClickListener{
            setNumber(8)
        }
        btnNine?.setOnClickListener{
            setNumber(9)
        }

        btnCls?.setOnClickListener{
            clear()
        }

        btnAdd?.setOnClickListener{
            setOperator("+")
        }

        btnSubtract?.setOnClickListener {
            setOperator("-")
        }

        btnMultiply?.setOnClickListener{
            setOperator("*")
        }

        btnDivide?.setOnClickListener {
            setOperator("/")
        }

        btnDot?.setOnClickListener {
            if (!tvCalc?.text.toString().contains(".")){
                val temp = tvCalc?.text.toString() + "."
                tvCalc?.text = temp
            }
        }

        btnEqual?.setOnClickListener{
            if (operand1 != 0.0 || operator != "") {
                operand2 = tvCalc?.text.toString().toDouble()
                tvOperand2?.text = normalizeNumber(operand2.toString())
                try {
                    when (operator) {
                        "+" -> {
                            tvCalc?.text = normalizeNumber((operand1 + operand2).toString())
                        }
                        "-" -> {
                            tvCalc?.text = normalizeNumber((operand1 - operand2).toString())
                        }
                        "*" -> {
                            tvCalc?.text = normalizeNumber((operand1 * operand2).toString())
                        }
                        "/" -> {
                            tvCalc?.text = normalizeNumber((operand1 / operand2).toString())
                        }
                        else -> {
                            tvCalc?.text = "0"
                        }
                    }
                } catch (e: ArithmeticException) {
                    e.printStackTrace()
                }
            }
            resetOp()
        }
    }

    // clear
    private fun clear() {
        resetOp()
        resetDisplay()
    }

    // reset operator and operand
    private fun resetOp() {
        operand1 = 0.0
        operand2 = 0.0
        operator = ""
    }

    // reset display
    private fun resetDisplay() {
        tvOperator?.text = ""
        tvOperand1?.text = ""
        tvOperand2?.text = ""
        tvCalc?.text = normalizeNumber("0")
    }

    // set operator
    private fun setOperator(op: String){
        if (tvCalc?.text.toString() == "0" && op == "-"){
            tvCalc?.text = "-"
        }else {
            operand1 = tvCalc?.text.toString().toDouble()
            tvOperand1?.text = normalizeNumber(operand1.toString())
            tvCalc?.text = normalizeNumber("0")
            operator = op
            tvOperator?.text = op
            tvOperand2?.text = ""
        }
    }

    // set number
    private fun setNumber(num: Int) {
        if (tvCalc?.text.toString() == "-"){
            tvCalc?.append(num.toString())
        }else{
            tvCalc?.text = normalizeNumber(concatNumber(tvCalc?.text.toString(), num))
        }
    }

    // concat number after button clicked
    private fun concatNumber(number1: String, number2: Int):String  {
        var temp: String
        if (number1 == "0"){
            temp = ""
            temp += number2.toString()
        } else {
            temp = number1
            temp += number2.toString()
        }
        return normalizeNumber(temp)
    }

    private fun normalizeNumber(number: String): String {
        var temp = String.format("%.6f", number.toDouble())
//        var temp = DecimalFormat("# ###.##").format(number.toDouble())
        if (temp.contains(".")) { // remove trailing zero 0.010000 -> 0.01
            val trailing = "[(\\.d+)\\.]*0*\$".toRegex()
            temp = temp.replace(trailing,"")
        }
//        if (temp.contains(" ")) { // remove extra space
//            temp = temp.replace(" ","")
//        }
        return temp
    }
}