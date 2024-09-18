package com.look0231.to_dolist

import android.R.attr
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.look0231.to_dolist.trunk.BottomSheetForm
import com.look0231.to_dolist.trunk.FloatingButton
import com.look0231.to_dolist.trunk.NavSheet
import com.look0231.to_dolist.trunk.Task
import com.look0231.to_dolist.trunk.TasksList
import com.look0231.to_dolist.trunk.TopBar
import com.look0231.to_dolist.ui.theme.AppTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val tasks = remember { mutableStateListOf<Task>() }

            val bottomSheetState = remember { mutableStateOf(false) }
            var bottomSheet by bottomSheetState

            val formTextState = remember { mutableStateOf("") }
            var formText by formTextState

            val formIndexState = remember { mutableStateOf(-1) }
            var formIndex by formIndexState

            val sheetState = rememberModalBottomSheetState()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()

            AppTheme {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        NavSheet(tasks)
                    },
                ) {
                    Scaffold(
                        floatingActionButton = { FloatingButton(
                            formTextState = formTextState,
                            formIndexState = formIndexState,
                            bottomSheetState = bottomSheetState
                        )},
                        topBar = {
                            TopBar(
                                text = "Tasks",
                            )
                        },
                        containerColor = MaterialTheme.colorScheme.background
                    ) { innerPadding ->

                        TasksList(
                            tasks = tasks,
                            formTextState = formTextState,
                            formIndexState = formIndexState,
                            bottomSheetState = bottomSheetState,
                            innerPadding = innerPadding
                        )


                        BottomSheetForm(
                            state = bottomSheetState,
                            sheetState = sheetState,
                            formTextState = formTextState,
                            onDismiss = {
                                scope.launch {
                                    tasks.removeAt(formIndex)
                                    sheetState.hide()
                                }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        bottomSheet = false
                                    }
                                }
                            },
                            showDismiss = formIndex >= 0,
                            onSubmit = {
                                scope.launch {
                                    if(formText.isNotBlank())
                                    {
                                        if(formIndex >= 0)
                                        {
                                            tasks[formIndex] = tasks[formIndex]
                                                .copy(description = formText)
                                        }
                                        else
                                        {
                                            tasks.add(Task(formText, false))
                                        }
                                    }
                                    sheetState.hide()
                                }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        bottomSheet = false
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}


