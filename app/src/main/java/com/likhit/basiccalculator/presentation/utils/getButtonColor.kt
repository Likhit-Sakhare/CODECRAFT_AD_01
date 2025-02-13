package com.likhit.basiccalculator.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.likhit.basiccalculator.ui.theme.EqualsColor
import com.likhit.basiccalculator.ui.theme.MediumGray
import com.likhit.basiccalculator.ui.theme.Orange

@Composable
fun getButtonColor(button: String): Color {
    if(button == "AC" || button == "C"){
        return Orange
    }
    if(button == "(" || button == ")" || button == "+" || button == "-"
        || button == "x" || button == "/"){
        return MediumGray
    }
    if(button == "="){
        return EqualsColor
    }
    return Color.Gray
}