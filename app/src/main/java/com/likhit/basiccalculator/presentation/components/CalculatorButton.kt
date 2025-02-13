package com.likhit.basiccalculator.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.likhit.basiccalculator.presentation.utils.getButtonColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CalculatorButton(
    modifier: Modifier = Modifier,
    button: String,
    onClick: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var isPressed by remember {
        mutableStateOf(false)
    }

    val buttonShape by animateDpAsState(
        targetValue = if(isPressed) 20.dp else 50.dp,
        label = "buttonShape"
    )

    Box(
        modifier = modifier.padding(4.dp)
    ){
        FloatingActionButton(
            onClick = {
                isPressed = true
                coroutineScope.launch {
                    delay(200)
                    isPressed = false
                }
                onClick()
            },
            modifier = Modifier.size(100.dp),
            shape = RoundedCornerShape(buttonShape),
            containerColor = getButtonColor(button),
            contentColor = Color.White
        ) {
            Text(
                text = button,
                fontSize = 40.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}