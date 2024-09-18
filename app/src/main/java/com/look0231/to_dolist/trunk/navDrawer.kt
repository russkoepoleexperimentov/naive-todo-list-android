package com.look0231.to_dolist.trunk

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json



@OptIn(ExperimentalSerializationApi::class)
@Composable
fun NavSheet(tasks: MutableList<Task>) {

    val context = LocalContext.current
    val json = Json {
        prettyPrint = true
        prettyPrintIndent = "\t"
    }

    val saveFileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/json"),
        onResult = { uri: Uri? ->
            val list = TaskList(tasks)
            uri?.let { saveFile(context, it, json.encodeToString(TaskList.serializer(), list)) }
        }
    )

    val openFileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                val jsonString = readFile(context, it)

                Log.d("kaka", jsonString)
                val loadedTasks = json.decodeFromString(TaskList.serializer(), jsonString)
                tasks.clear()
                tasks.addAll(loadedTasks.tasks)
            }
        }
    )

    ModalDrawerSheet {
        Text("Actions",
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Bold
        )
        Divider()
        NavigationDrawerItem(
            label = { Text(text = "Import") },
            selected = false,
            onClick = {
                openFileLauncher.launch("*/*")
            }
        )
        NavigationDrawerItem(
            label = { Text(text = "Export") },
            selected = false,
            onClick = {
                saveFileLauncher.launch("tasks.json")
            }
        )
        Divider()
        NavigationDrawerItem(
            label = { Text(text = "Clear all") },
            selected = false,
            onClick = {
                tasks.clear()
            }
        )
    }
}