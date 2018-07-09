package com.kotlin.api.repository

import com.kotlin.api.board.model.Article
import com.kotlin.api.board.model.Category
import com.kotlin.api.board.repository.BoardRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@RunWith(MockitoJUnitRunner::class)
class BoardRepositoryTest {

    @Mock
    lateinit var repository : BoardRepository

    lateinit var article:Article

    @Before
    fun beforeSetup() {
        article = Article(
                articleId = 1,
                title = "test article",
                author = "tester",
                content = "text",
                category = Category.FREE
        )

    }

    @Test
    fun saveTest() {
        repository.save(article)
    }

    @Test
    fun save_and_list() {

        repository.save(article)

        println(article)
        println(repository.findAll())
    }

}