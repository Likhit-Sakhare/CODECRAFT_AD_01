package com.likhit.basiccalculator.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.likhit.basiccalculator.presentation.utils.operators
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.mozilla.javascript.Context

class CalculatorViewModel: ViewModel() {

    private val _equationText = MutableStateFlow("")
    val equationText = _equationText.asStateFlow()

    private val _resultText = MutableStateFlow("")
    val resultText = _resultText.asStateFlow()

    fun onButtonClick(button: String){
        _equationText.value.let {

            /*
                If the button is AC, then make both the initial and final text "" and
                return from there
            */
            if(button == "AC"){
                _equationText.value = ""
                _resultText.value = ""
                return
            }

            /*
                If the button is C, if the initial text is empty then simply return
                and if the initial text is not empty then remove last character from it and return
            */
            if (button == "C"){
                if(it.isEmpty()){
                    return
                }
                if(it.isNotEmpty()){
                    _equationText.value = it.substring(0, it.length - 1)
                    return
                }
            }

            /*
                If the button is =, if the initial text is empty then simply return and then
                update the final text to the calculated result
            */
            if(button == "="){
                try {
                    if(it.isEmpty()){
                        _resultText.value = ""
                        return
                    }
                    _resultText.value = calculateResult(_equationText.value)
                }catch (e: Exception){
                    Log.e("CalculatorViewModel", e.message?: "Unknown error")
                }
                return
            }

            /*
                If the initial text is empty and the button we click is in the operators except "-"
                then simply return.

                This handles the cases like "+5", "x5", "89/+4", only allowed case is "-98+6"
            */
            if(it.isEmpty() && button in operators && button != "-"){
                return
            }

            /*
                If the initial text is not empty, then first find the last character which we will
                use to check if the last character is an operator.

                Then check if the last character and the pressed button are other operators,
                this condition prevents two consecutive operators like "++", "-*", etc.

                Then for last condition, if the button is "-" and the last character is not "-",
                then allow that equation otherwise block that equation or return from it.

                This condition prevents cases like "5--8" and only allow "98x-74", 78/-5", etc
            */
            if(it.isNotEmpty()){
                val lastCharacter = it.last().toString()
                if(lastCharacter in operators && button in operators){
                    if(!(button == "-" && lastCharacter != "-")){
                        return
                    }
                }
            }

            /*
                If the button is "(" and the initial text is not empty and last character is digit,
                then return.
            */
            if(button == "(" && it.isNotEmpty()
                && it.last().isDigit()){
                return
            }

            /*
                If the button is ")", then first count the numbers of opening and closing brackets
                present in the equation.

                Then ensure that we don't have more closing brackets than the opening brackets,
                then also check is our initial text is empty so that we can't put closing bracket
                as the first character, then also check that closing bracket should not directly
                comes after an operator.

                This prevents cases like "((589)-98)))", ")58", (48+)
            */
            if(button == ")"){
                val openBrackets = _equationText.value.count { it == '(' }
                val closeBrackets = _equationText.value.count { it == ')' }

                if(openBrackets <= closeBrackets
                    || _equationText.value.isEmpty() || it.lastOrNull()!! in "+-x/("){
                    return
                }
            }

            /*
                Store the value in the initial text after every button press
            */
            _equationText.value = it + button
        }
    }

    private fun calculateResult(equation: String): String{
        val context = Context.enter()
        context.optimizationLevel = -1
        val scriptable = context.initStandardObjects()

        val fixedEquation = equation.replace("x", "*")

        var result = context.evaluateString(
            scriptable,
            fixedEquation,
            "Javascript",
            1,
            null
        ).toString()
        if(result.endsWith(".0")){
            result = result.replace(".0", "")
        }
        return result
    }
}