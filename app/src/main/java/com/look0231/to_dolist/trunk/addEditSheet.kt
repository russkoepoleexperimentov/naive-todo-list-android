package com.look0231.to_dolist.trunk

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetForm(state: MutableState<Boolean>, sheetState: SheetState,
                    formTextState: MutableState<String>, onSubmit: () -> Unit, onDismiss: () -> Unit,
                    showDismiss: Boolean
)
{
    if (state.value) {
        ModalBottomSheet(
            onDismissRequest = {
                state.value = false
            },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
            ) {

                TextField(
                    value = formTextState.value,
                    onValueChange = { formTextState.value = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(15.dp))

                Row()
                {
                    
                    Spacer(modifier = Modifier.weight(1f))

                    if(showDismiss)
                    {
                        TextButtonWithIcon(icon = Icons.Filled.Delete, text = "Delete")
                        {
                            onDismiss()
                        }
                    }

                    TextButtonWithIcon(icon = Icons.Filled.Done, text = "Save") {
                        onSubmit()
                    }
                }
            }
        }
    }
}