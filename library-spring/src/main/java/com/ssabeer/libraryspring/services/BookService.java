package com.ssabeer.libraryspring.services;

import com.ssabeer.libraryspring.models.Book;

import java.util.List;

public interface BookService {
    List<Book> loadBooks(String name, String author, String isbn, int page, int count);

    Book getBook(int bookId);

    Book updateLocation(int bookId, Book book);

    Book saveBook(Book book);
}
