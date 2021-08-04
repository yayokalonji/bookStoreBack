package com.book.bookstore;

import com.book.bookstore.controllers.BookController;
import com.book.bookstore.mappers.MapStructMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookstoreApplicationTests {

    @Autowired
    private BookController bookController;

    @Autowired
    private MapStructMapper mapStructMapper;

    @Test
    void contextLoads() {
        assertThat(bookController).isNotNull();
    }

}
