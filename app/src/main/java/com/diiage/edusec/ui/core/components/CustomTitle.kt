package com.diiage.edusec.ui.core.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun CustomTitle(
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.displayLarge,
        fontSize = 52.sp,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true, name = "Custom Title")
@Composable
fun CustomTitlePreview() {
    CustomTitle("Custom Title")
}
