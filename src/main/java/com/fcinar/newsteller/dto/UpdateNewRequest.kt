package com.fcinar.newsteller.dto

data class UpdateNewRequest(
    val title: String,
    val description: String,
    val published: Boolean
)
