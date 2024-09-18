package com.look0231.to_dolist.trunk

import kotlinx.serialization.Serializable

@Serializable
data class TaskList (
    val tasks: List<Task>
)