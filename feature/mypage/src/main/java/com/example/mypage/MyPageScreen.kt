package com.example.mypage

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
 fun MyPageScreen(modifier: Modifier = Modifier, viewModel : MyPageViewModel = hiltViewModel()){

    Greeting(name = "Mypage")
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}