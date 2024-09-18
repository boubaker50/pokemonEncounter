package com.bforbank.pokemon.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun ModalLoading() {
    Dialog(
        onDismissRequest = {},
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
    ) {
        Card(colors = CardDefaults.cardColors(containerColor = Color.Transparent)) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(Modifier.padding(32.dp))
            }
        }
    }
}