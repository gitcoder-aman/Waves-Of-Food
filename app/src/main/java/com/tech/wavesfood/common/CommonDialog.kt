package com.tech.wavesfood.common

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import com.tech.wavesfood.ui.theme.GreenColor

@Composable
fun CommonDialog() {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        CircularProgressIndicator(color = GreenColor)
    }
}