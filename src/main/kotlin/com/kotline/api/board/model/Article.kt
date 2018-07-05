package com.kotline.api.board.model

enum class Category {NOTICE, QNA, FNQ, FREE}


data class Article(
    var articleId: Int? = null,
    var title: String,
    var author: String,
    var content: String,
    var category: Category
) {
}
