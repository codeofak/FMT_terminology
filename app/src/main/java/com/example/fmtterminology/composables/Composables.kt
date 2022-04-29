package com.example.fmtterminology.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TerminologyRow(
    modifier: Modifier = Modifier,
    textEnglish: String,
    textUrdu: String,
    textPronounce: String,
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    var ShowPopUpMenu by remember { mutableStateOf(false) }
    val menuItems: List<String> = listOf<String>("Item1", "Item2", "Item3")
    Card(
        modifier = Modifier
            .padding(5.dp)
            .animateContentSize()
            .clickable { isExpanded = !isExpanded },
        elevation = 3.dp,
        backgroundColor = Color(0xFCD3D0D6),
        border = BorderStroke(color = Color.Unspecified, width = 1.dp),
        shape = RoundedCornerShape(20.dp),
    ) {
        Column() {
            Row(
            ) {
                SelectionContainer() {      //It makes the text selectable
                    Text(modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                        text = textEnglish,
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center
                    )
                }
            }
            if (isExpanded) {
                Box(modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp, bottom = 1.dp)) {
                    Column() {
                        RowItem(text = textUrdu,
                            cardColor = Color.DarkGray,
                            padding = 2,
                            fontStyle = FontStyle.Normal,
                            textColor = Color.White
                        )
                        RowItem(text = textPronounce,
                            cardColor = Color.DarkGray,
                            padding = 2,
                            fontStyle = FontStyle.Italic,
                            textColor = Color.White
                        )
                    }
                }
            }
        }
    }

}


@Composable
fun RowItem(text: String, cardColor: Color, padding: Int, fontStyle: FontStyle, textColor: Color) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(padding.dp),
        backgroundColor = cardColor,
        shape = RoundedCornerShape(20.dp)
    ) {
        SelectionContainer() {
            Text(modifier = Modifier.padding(2.dp),
                textAlign = TextAlign.Center,
                text = text,
                fontStyle = fontStyle,
                color = textColor)
        }
    }
}

@Composable
fun SplashScreen() {
    Card(modifier = Modifier.fillMaxSize(), backgroundColor = Color.DarkGray) {

    }
}








