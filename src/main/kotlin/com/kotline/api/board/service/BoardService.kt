package com.kotline.api.board.service

import com.kotline.api.board.controller.articles
import com.kotline.api.board.model.Article
import org.springframework.stereotype.Service


interface BoardService {

    open fun create(article: Article?) : Int
    open fun detail(articleId: Int): Article?

}

fun MutableList<Article>.maxId() : Int {

    if (size == 0) return 0

    val list = this.toList()
    val max = list.asSequence().maxBy { it->it.articleId?:0 }

    return max?.articleId?:0
}

@Service
class BoardServiceImpl : BoardService {

    override fun create(article: Article?) :Int {

        article ?: throw IllegalArgumentException("article can be null");
        article.articleId = articles?.maxId()+1
        articles.add(article)

        return article.articleId?:0
    }

    override fun detail(boardId: Int) : Article?{

        boardId?: throw IllegalArgumentException("articleId can be null")


        return articles.asSequence()
                .find { it.articleId == boardId}
    }
}

