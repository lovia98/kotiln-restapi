package com.kotlin.api.board.service

import com.kotlin.api.board.model.Article

/*
* 게시물 CURD interface
*/
interface BoardService {

    open fun create(article: Article?) : Any?
    open fun detail(articleId: Int?): Article?
    open fun update(article: Article?) : Any?
    open fun delete(articleId: Int?): Any?

}

/*
 * Article 리스트에서 maxId구하기
 */
fun MutableList<Article>.maxId() : Int {

    if (size == 0) return 0

    val list = this.toList()
    val max = list.asSequence().maxBy { it->it.articleId?:0 }

    return max?.articleId?:0
}