package com.kotline.api.board.controller

import com.kotline.api.board.model.Article
import com.kotline.api.model.ResponseFormat
import com.kotline.api.model.ResultCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

import com.kotline.api.board.service.*

var articles = mutableListOf<Article>()


/*
 * 게시판 api를 만들어 보장
 *
 * 생성, 리스트, 상세, 삭제, 수정
 */
@RestController
@RequestMapping("/board")
class BoardController {

    @Autowired
    lateinit var boardService: BoardService


    /*
     * 게시물 저장
     */
    @PostMapping
    fun createBoard(@RequestBody article: Article?) : ResponseFormat {

        try {
            return ResponseFormat(
                    result=ResultCode.SUCCESS,
                    data=boardService.create(article))

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
                    data= articles)

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
                    result=ResultCode.SUCCESS,
                    data=boardService.detail(articleId))

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
                    data= boardService.update(article))

        }catch (e: Exception) {
            return ResponseFormat(result=ResultCode.SERVERERROR, errorMessage = e.message!!)
        }
    }

    /*
     * 삭제
     */
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") articleId: Int) : ResponseFormat{

        try {

            return ResponseFormat(
                    result=ResultCode.SUCCESS,
                    data=boardService.delete(articleId))

        }catch (e: Exception) {
            return ResponseFormat(ResultCode.SERVERERROR, errorMessage = e.message!!)
        }
    }

}