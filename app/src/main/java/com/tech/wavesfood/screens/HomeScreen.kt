package com.tech.wavesfood.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tech.wavesfood.ui.theme.WavesFoodTheme

@Composable
fun HomeScreen(navHostController: NavHostController) {

}

@Composable
@Preview
fun HomeScreenPreview() {
    WavesFoodTheme {
        val navHostController = rememberNavController()
        HomeScreen(navHostController = navHostController)
    }
}