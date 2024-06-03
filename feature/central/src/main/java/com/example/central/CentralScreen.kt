package com.example.central

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
internal fun MainScreen( modifier: Modifier = Modifier,viewModel : CentralViewModel = hiltViewModel()){

    Greeting(name = "Central")
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier,) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}