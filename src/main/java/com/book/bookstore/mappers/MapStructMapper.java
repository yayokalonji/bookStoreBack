package com.book.bookstore.mappers;


import com.book.bookstore.dtos.BooksDTO;
import com.book.bookstore.entity.BooksEntity;
import org.mapstruct.Mapper;

@Mapper
public interface MapStructMapper {

    BooksDTO bookEntityToBookDTO(BooksEntity booksEntity);

    BooksEntity bookDTOToBookEntity(BooksDTO booksDTO);
}
