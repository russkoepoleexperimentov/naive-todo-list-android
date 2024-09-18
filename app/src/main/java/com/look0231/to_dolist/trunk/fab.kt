package com.look0231.to_dolist.trunk

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState


@Composable
fun FloatingButton(formTextState: MutableState<String>,
                   formIndexState: MutableState<Int>,
                   bottomSheetState: MutableState<Boolean>
)
{
    FloatingActionButton(
        onClick = {
            formTextState.value = ""
            formIndexState.value = -1
            bottomSheetState.value = true
        },
        shape = CircleShape
        ) {
        Icon(Icons.Filled.Add, "Add task")
    }
}