package com.kotlin.api.board.repository

import com.kotlin.api.board.model.Article
import org.springframework.data.repository.PagingAndSortingRepository

interface BoardRepository : PagingAndSortingRepository<Article, Int> {


}














