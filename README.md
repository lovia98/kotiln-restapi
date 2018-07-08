# kotiln-restapi

1. Kotilin - Spring Boot - RestApi Example

2. 간단한 게시물 CRUD API

3. Swagger2

4. JPA 적용 주의할 점
   - 코틀린에서 JPA적용시 엔티티에 default 생성자가 필요함.(아무 인자가 없는 생성자)
    예)
    ```
    data class Article (@Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                var articleId: Int,
                var title: String,
                var author: String,
                var content: String,
                var category: Category) {



}
    ```
