package com.look0231.to_dolist.trunk

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    var description: String = "",
    var done: Boolean = false
)
