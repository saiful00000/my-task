package com.shaiful.mynote.utils

import com.shaiful.mynote.presentation.models.IdSlug

val priorityList = listOf(
    IdSlug(id = 1, slug = "Low"),
    IdSlug(id = 2, slug = "Medium"),
    IdSlug(id = 3, slug = "High"),
);

fun getPriorityById(id: Int): IdSlug? {
    return priorityList.find { it.id == id }
}

fun getPriorityBySlug(slug: String): IdSlug? {
    return  priorityList.find { it.slug == slug }
}