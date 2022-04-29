package com.example.fmtterminology

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.fmtterminology.composables.SearchViewComposable2
import com.example.fmtterminology.composables.TerminologyRow
import com.example.fmtterminology.ui.theme.FMTTerminologyTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val terminologyViewModel = ViewModelProvider(this).get(TerminologyViewModel::class.java)
        setContent {
            FMTTerminologyTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {

                    //MyApp(terminologyViewModel = terminologyViewModel, state = textState)
                    //MyApp2(terminologyViewModel = terminologyViewModel)
                    MyApp3(terminologyViewModel = terminologyViewModel)

                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyApp(terminologyViewModel: TerminologyViewModel, state: MutableState<TextFieldValue>,isSearchViewVisible:Boolean) {
    val terminologyList by terminologyViewModel.fetchAllTerminology()
        .observeAsState(listOf())

    val searchedText = state.value.text

    //Filtering terminologies for search
    val filteredTerminology = if (searchedText.isEmpty()) {
        terminologyList
    } else {
        val resultList = ArrayList<Terminology>()
        for (term in terminologyList) {
            if (term.meaningEnglish.lowercase(Locale.getDefault())
                    .contains(searchedText.lowercase(Locale.getDefault()))
            ) {
                resultList.add(term)
            }
        }
        resultList
    }

    Column(modifier = Modifier.padding(4.dp)) {
        //Checking if Search View is to be shown
        if (isSearchViewVisible) {
            //SearchViewComposable(state = state)
            SearchViewComposable2(state = state)
        }

        LazyColumn(modifier = Modifier.animateContentSize()) {
            items(filteredTerminology) {
                TerminologyRow(
                    textEnglish = it.meaningEnglish,
                    textUrdu = it.meaningUrdu,
                    textPronounce = it.meaning_pronounce)
            }
        }
    }
}

// Main screen with "Overlapping custom side menu"
@Composable
fun MyApp2(terminologyViewModel: TerminologyViewModel) {

    var isSideMenuVisible by remember { mutableStateOf(false) }
    val textState = remember { mutableStateOf(TextFieldValue("")) }


    Box(modifier = Modifier.fillMaxSize()) {
        MyApp(terminologyViewModel = terminologyViewModel, state = textState, isSearchViewVisible = isSideMenuVisible)

        if (isSideMenuVisible) {
            //Making background half transparent
            Card(modifier = Modifier
                .fillMaxSize()
                .alpha(0.5f)
                .clickable { isSideMenuVisible = !isSideMenuVisible },
                backgroundColor = Color.Gray) {
            }
        }

        Column(modifier = Modifier.fillMaxHeight()) {

            // Circular Hamburger menu
            Card(
                modifier = Modifier
                    .size(45.dp)
                    .padding(start = 4.dp, top = 4.dp)
                    .clickable { isSideMenuVisible = !isSideMenuVisible },
                shape = RoundedCornerShape(30.dp),
                backgroundColor = Color.DarkGray,
                elevation = 3.dp
            ) {}
            if (isSideMenuVisible) {
                Card(
                    modifier = Modifier
                        .width(45.dp)
                        .fillMaxHeight()
                        .padding(start = 4.dp, top = 10.dp, bottom = 4.dp),
                    shape = RoundedCornerShape(20.dp),
                    backgroundColor = Color.DarkGray
                ) {}
            }

        }
    }
}

// Main screen with "Overlapping custom side menu"
@Composable
fun MyApp3(terminologyViewModel: TerminologyViewModel) {
    var isSideMenuVisible by remember { mutableStateOf(false) }
    var isSearchViewVisible by remember { mutableStateOf(false) }
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    var buttonClicked by remember { mutableStateOf(false) }


    Box(modifier = Modifier.fillMaxSize()) {


        Row() {

            if (isSideMenuVisible) {
                Column() {

                    // Circular Hamburger menu -- Behind
                    Card(
                        modifier = Modifier
                            .size(45.dp)
                            .padding(start = 4.dp, top = 4.dp),
                        shape = RoundedCornerShape(30.dp),
                        backgroundColor = Color.DarkGray
                    ) {
                        AnimatedVisibility(visible = isSideMenuVisible) {
                            IconButton(modifier = Modifier.padding(4.dp).rotate(90.0f),onClick = {  isSideMenuVisible = !isSideMenuVisible }) {
                                Icon( Icons.Default.Menu,contentDescription = null, tint = Color.White)
                            }
                        }
                    }

                    //Long Side Bar
                    if (isSideMenuVisible) {
                        Card(
                            modifier = Modifier
                                .width(45.dp)
                                .fillMaxHeight()
                                .padding(start = 4.dp,
                                    top = 10.dp,
                                    bottom = 4.dp),
                            shape = RoundedCornerShape(20.dp),
                            backgroundColor = Color.DarkGray
                        ) {
                            Column(Modifier.padding(top = 10.dp),horizontalAlignment = Alignment.CenterHorizontally) {
                                //Search Icon
                                IconButton(onClick = { isSearchViewVisible = !isSearchViewVisible }) {
                                    Icon(Icons.Rounded.Search, contentDescription = null, tint = Color.White)
                                }
                            }
                        }
                    }
                }
            }

            MyApp(terminologyViewModel = terminologyViewModel, state = textState, isSearchViewVisible = isSearchViewVisible)
        }

        //Circular Hamburger menu---Top
        if (!isSideMenuVisible) {
            Card(
                modifier = Modifier
                    .size(45.dp)
                    .padding(start = 4.dp, top = 4.dp)
                    .clickable { isSideMenuVisible = !isSideMenuVisible },
                shape = RoundedCornerShape(40.dp),
                backgroundColor = Color.DarkGray
            ) {
                IconButton(modifier = Modifier.padding(4.dp),onClick = {  isSideMenuVisible = !isSideMenuVisible }) {
                    Icon( Icons.Default.Menu,contentDescription = null, tint = Color.White)
                }
            }
        }

    }
}


















