package com.kotlin.api.board.service

import com.kotlin.api.board.controller.articles
import com.kotlin.api.board.model.Article
import com.kotlin.api.board.repository.BoardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/*
* 게시물 CURD interface
*/
interface BoardService {

    open fun create(article: Article?) : Any?
    open fun list() : Any?
    open fun detail(articleId: Int?): Any?
    open fun update(article: Article?) : Any?
    open fun delete(articleId: Int?): Any?

}

@Service
class BoardServiceImpl : BoardService {

    @Autowired
    lateinit var boardRepository : BoardRepository


    /*
     * 저장
     */
    override fun create(article: Article?) :Int {

        article ?: throw IllegalArgumentException("article can be null");

        boardRepository.save(article)

        return article.articleId?:0
    }

    /*
     * 리스트
    */
    override fun list(): Any? {
        return boardRepository.findAll()
    }

    /*
     * 조회
    */
    override fun detail(boardId: Int?) : Optional<Article>? {

        boardId?: throw IllegalArgumentException("articleId can be null")

        return boardRepository.findById(boardId)
    }
    /*
     * 수정
     */
    override fun update(article: Article?): Int {

        article ?: throw IllegalArgumentException("article can be null")

        article.articleId ?: throw IllegalArgumentException("articleId can be null")

        boardRepository.save(article)

        return article.articleId?:0
    }

    /*
     * 삭제
     */
    override fun delete(articleId: Int?): Int {

        articleId ?: throw IllegalArgumentException("articleId can be null")

        try {
            boardRepository.deleteById(articleId)
            return 1

        }catch (e: Exception) {
            return 0
        }
    }
}
