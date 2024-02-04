package com.gana.ebenezer.rachael

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.gana.ebenezer.rachael.ui.theme.Purple40
import com.gana.ebenezer.rachael.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RachaelTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Rachael", color = Color.White)
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Purple40
        )
    )

}

@Preview
@Composable
private fun RachaelTopBarPreview() {
    RachaelTopBar()
}


