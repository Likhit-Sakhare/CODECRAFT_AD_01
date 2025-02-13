package com.likhit.basiccalculator.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.likhit.basiccalculator.presentation.components.CalculatorButton
import com.likhit.basiccalculator.presentation.utils.buttonList
import com.likhit.basiccalculator.ui.theme.CursorColor
import kotlinx.coroutines.delay

@Composable
fun CalculatorRoot(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel
) {
    val equation = viewModel.equationText.collectAsState()
    val result = viewModel.resultText.collectAsState()

    Calculator(
        modifier = modifier,
        equation = equation.value,
        result = result.value,
        onButtonClick = viewModel::onButtonClick
    )
}

@Composable
fun Calculator(
    modifier: Modifier = Modifier,
    equation: String,
    result: String,
    onButtonClick: (String) -> Unit
) {
    var showCursor by remember {
        mutableStateOf(true)
    }
    val scrollState = rememberScrollState()

    val defaultSize = 100.sp
    var textSize by remember {
        mutableStateOf(defaultSize)
    }
    val minTextSize = 60.sp
    val textMeasurer = rememberTextMeasurer()
    val textWidth = remember {
        mutableStateOf(0f)
    }

    var isUserScrolling by remember {
        mutableStateOf(false)
    }
    var shouldAutoScroll by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {
        while (true){
            delay(500)
            showCursor = !showCursor
        }
    }
    
    LaunchedEffect(equation) {
        if(equation.isEmpty()){
            textSize = defaultSize
        }else{
            textWidth.value = textMeasurer.measure(
                text = equation,
                style = TextStyle(fontSize = textSize)
            ).size.width.toFloat()

            val maxAllowedWidth = 1000f

            if(textWidth.value > maxAllowedWidth && textSize > minTextSize){
                textSize *= 0.9f
            }
        }
    }

    LaunchedEffect(scrollState.isScrollInProgress) {
        if(scrollState.isScrollInProgress){
            isUserScrolling = true
            shouldAutoScroll = false
        }else{
            delay(500)
            isUserScrolling = false
            shouldAutoScroll = true
        }
    }

    LaunchedEffect(equation) {
        if(shouldAutoScroll && !isUserScrolling){
            scrollState.scrollTo(
                scrollState.maxValue
            )
        }
    }

    Box(
        modifier = modifier.padding(12.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom
            ) {
                BasicTextField(
                    value = equation,
                    onValueChange = {},
                    readOnly = true,
                    textStyle = TextStyle(
                        fontSize = textSize,
                        textAlign = TextAlign.End,
                        color = Color.White,
                        lineHeight = 65.sp
                    ),
                    cursorBrush = SolidColor(Color.Transparent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(
                            state = scrollState
                        ),
                    decorationBox = { innerTextField ->
                        Box{
                            if(equation.isEmpty()){
                                if(showCursor){
                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.CenterEnd)
                                            .width(1.5.dp)
                                            .height(textSize.value.dp)
                                            .background(CursorColor)
                                    )
                                }
                            }else{
                                innerTextField()
                                if(showCursor){
                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.CenterEnd)
                                            .width(1.5.dp)
                                            .height(textSize.value.dp)
                                            .background(CursorColor)
                                    )
                                }
                            }
                        }
                    }
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = result,
                    fontSize = 50.sp,
                    textAlign = TextAlign.End,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.LightGray,
                    lineHeight = 45.sp
                )
                Spacer(Modifier.height(16.dp))
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(buttonList){ button ->
                    CalculatorButton(
                        button = button,
                        onClick = {
                            onButtonClick(button)
                        }
                    )
                }
            }
        }
    }
}

