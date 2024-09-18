package com.look0231.to_dolist.trunk

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// https://habr.com/ru/companies/joydev/articles/777412/
@Composable
fun TasksList(tasks: MutableList<Task>, formTextState: MutableState<String>,
              formIndexState: MutableState<Int>, bottomSheetState: MutableState<Boolean>,
              innerPadding: PaddingValues
)
{
    Column {
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
        ) {
            items(tasks.size) { i ->
                TaskView(
                    tasks = tasks,
                    i = i,
                    formText = formTextState,
                    formIndex = formIndexState,
                    bottomSheetState = bottomSheetState
                )
            }
        }
    }

}

@Composable
fun TaskView(tasks: MutableList<Task>, i: Int, formText: MutableState<String>,
             formIndex: MutableState<Int>, bottomSheetState: MutableState<Boolean>
)
{
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = tasks[i].description,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
                    .clickable {
                        formIndex.value = i
                        formText.value = tasks[i].description
                        bottomSheetState.value = true
                    }
            )
            Switch(
                checked = tasks[i].done,
                onCheckedChange = {
                    tasks[i] = tasks[i].copy(done = it)
                }
            )
        }
    }
}