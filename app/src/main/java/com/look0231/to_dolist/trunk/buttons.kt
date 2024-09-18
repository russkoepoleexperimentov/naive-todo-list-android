package com.look0231.to_dolist.trunk

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun RoundIconButton(icon: ImageVector, contentDescription: String, onClick: () -> Unit)
{
    Button(
        onClick = {
            onClick()
        },
        shape = CircleShape,
        modifier = Modifier.size(50.dp),
        contentPadding = PaddingValues(0.dp),
    ) {
        Icon(icon, contentDescription)
    }
}

@Composable
fun TextButtonWithIcon(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
)
{
    Button(
        onClick = {
            onClick()
        },
    ) {
        Text(
            text = text,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Icon(icon, text)
    }
}