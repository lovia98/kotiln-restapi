package com.kotlin.api.board.model

import javax.persistence.*

enum class Category {NOTICE, QNA, FNQ, FREE}

@Entity
data class Article (@Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                var articleId: Int,
                var title: String,
                var author: String,
                var content: String,
                var category: Category) {

}

fun method(a: String) {

}

