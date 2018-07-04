package com.kotline.api.board.model

enum class Category {NOTICE, QNA, FNQ, FREE}


data class Article(
    var articleId: Int? = null,
    val title: String,
    val author: String,
    val content: String,
    val category: Category
) {
}
