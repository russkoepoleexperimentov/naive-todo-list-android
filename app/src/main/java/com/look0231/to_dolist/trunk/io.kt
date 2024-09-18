package com.look0231.to_dolist.trunk

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter


fun saveFile(context: Context, uri: Uri, content: String)
{
    context.contentResolver.openOutputStream(uri)?.use { outputStream ->
        OutputStreamWriter(outputStream).use { writer ->
            writer.write(content)
        }
    }
}

fun readFile(context: Context, uri: Uri): String {
    var txt = ""
    context.contentResolver.openInputStream(uri)?.use { inputStream ->
        inputStream.bufferedReader().use { txt = it.readText() }
    }
    return txt
}