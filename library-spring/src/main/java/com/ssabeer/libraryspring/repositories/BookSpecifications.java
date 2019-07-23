package com.ssabeer.libraryspring.repositories;

import com.ssabeer.libraryspring.models.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {
    public static Specification<Book> containsInName(String name) {
        return (item, query, builder) ->
                builder.like(item.get("name"), "%" + name + "%");
    }

    public static Specification<Book> containsInAuthor(String author) {
        return (item, query, builder) ->
                builder.like(item.get("author"), "%" + author + "%");
    }

    public static Specification<Book> containsInIsbn(String isbn) {
        return (item, query, builder) ->
                builder.like(item.get("isbn"), "%" + isbn + "%");
    }
}
