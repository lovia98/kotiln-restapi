package com.kotlin.api.board.controller

import com.kotlin.api.board.model.Article
import com.kotlin.api.common.model.ResponseFormat
import com.kotlin.api.common.model.ResultCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

import com.kotlin.api.board.service.*
import org.slf4j.LoggerFactory
import java.util.logging.Logger

var articles = mutableListOf<Article>()


/*
 * 게시판 api를 만들어 보장
 *
 * 생성, 리스트, 상세, 삭제, 수정
 */
@RestController
@RequestMapping("/board")
class BoardController {

    val logger: org.slf4j.Logger = LoggerFactory.getLogger(BoardController::class.java)


    @Autowired
    lateinit var boardService: BoardService

    /*
     * 게시물 저장
     */
    @PostMapping
    fun createBoard(@RequestBody article: Article?) : ResponseFormat {

        logger.info("save Start-----")
        logger.debug("param : ${article}")

        try {
            return ResponseFormat(
                    result = ResultCode.SUCCESS,
                    data = boardService.create(article))

        }catch(e: Exception) {
            return ResponseFormat(ResultCode.SERVERERROR, errorMessage = e.message!!)
        }
    }


    /*
     * 리스트
     */
    @GetMapping
    fun list() : ResponseFormat {

        try {
            return ResponseFormat(
                    result = ResultCode.SUCCESS,
                    data = boardService.list())

        }catch(e: Exception) {
            return ResponseFormat(ResultCode.SERVERERROR,
                    errorMessage = e.message!!)
        }
    }


    /*
     * 상세
     */
    @GetMapping("/{id}")
    fun detail(@PathVariable("id") articleId: Int) : ResponseFormat {

        try {

            return ResponseFormat(
                    result = ResultCode.SUCCESS,
                    data = boardService.detail(articleId))

        }catch (e: Exception) {
            return ResponseFormat(ResultCode.SERVERERROR, errorMessage = e.message!!)
        }
    }

    /*
     * 수정
     */
    @PutMapping
    fun update(@RequestBody article:Article?) : ResponseFormat {
        try {
            return ResponseFormat(
                    result = ResultCode.SUCCESS,
                    data = boardService.update(article))

        }catch (e: Exception) {
            return ResponseFormat(result = ResultCode.SERVERERROR, errorMessage = e.message!!)
        }
    }

    /*
     * 삭제
     */
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") articleId: Int) : ResponseFormat {

        try {

            return ResponseFormat(
                    result = ResultCode.SUCCESS,
                    data = boardService.delete(articleId))

        }catch (e: Exception) {
            return ResponseFormat(ResultCode.SERVERERROR, errorMessage = e.message!!)
        }
    }

}