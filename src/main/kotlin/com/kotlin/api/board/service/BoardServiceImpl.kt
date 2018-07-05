package com.kotlin.api.board.service

import com.kotlin.api.board.controller.articles
import com.kotlin.api.board.model.Article
import org.springframework.stereotype.Service


@Service
class BoardServiceImpl : BoardService {


    /*
     * 저장
     */
    override fun create(article: Article?) :Int {

        article ?: throw IllegalArgumentException("article can be null");
        article.articleId = articles?.maxId()+1
        articles.add(article)

        return article.articleId?:0
    }

    /*
     * 조회
     */
    override fun detail(boardId: Int?) : Article?{

        boardId?: throw IllegalArgumentException("articleId can be null")


        return articles.asSequence()
                .find { it.articleId == boardId}
    }
    /*
     * 수정
     */
    override fun update(article: Article?): Int {

        article ?: throw IllegalArgumentException("article can be null")

        article.articleId ?: throw IllegalArgumentException("articleId can be null")

        var find = articles.asSequence().find { it.articleId == article.articleId}

        find ?: throw IllegalArgumentException("We can not find any Article width Id : ${article.articleId}")

        //update
        with(find) {
            title = article.title
            author = article.author
            content = article.content
            category = article.category
        }

        return find.articleId?:0
    }

    /*
     * 삭제
     */
    override fun delete(articleId: Int?): Int {

        articleId ?: throw IllegalArgumentException("articleId can be null")

        val removed = articles.removeIf { it.articleId == articleId  }

        return if(removed) 1 else 0
    }
}

