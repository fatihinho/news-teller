package com.fcinar.newsteller.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "news")
data class New(
    @Id
    val id: String?,
    var title: String,
    var description: String,
    var published: Boolean
) {
    constructor(title: String, description: String, published: Boolean) : this(
        id = null,
        title = title,
        description = description,
        published = published
    )

    override fun toString(): String {
        return String.format("Title: %s\nDescription: %s\nPublished: %s", title, description, published)
    }
}
