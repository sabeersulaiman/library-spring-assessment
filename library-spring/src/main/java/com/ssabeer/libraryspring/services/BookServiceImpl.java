package com.ssabeer.libraryspring.services;

import com.ssabeer.libraryspring.exceptions.ResourceNotFoundException;
import com.ssabeer.libraryspring.models.Book;
import com.ssabeer.libraryspring.models.enums.BookStatus;
import com.ssabeer.libraryspring.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.ssabeer.libraryspring.repositories.BookSpecifications.*;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> loadBooks(String name, String author, String isbn, int page, int count) {
        Specification<Book> searchSpec = null;

        // if name based search is requested, do it
        if(name != null) {
            searchSpec = containsInName(name);
        }

        // if author based search is also requested, do it conditionally
        if(author != null) {
            if(searchSpec == null) {
                searchSpec = containsInAuthor(author);
            }
            else {
                searchSpec = searchSpec.or(containsInAuthor((author)));
            }
        }

        // if isbn based search is also requested, do it conditionally
        if(isbn != null) {
            if(searchSpec == null) {
                searchSpec = containsInIsbn(isbn);
            }
            else {
                searchSpec = searchSpec.or(containsInIsbn(isbn));
            }
        }

        Pageable pageable = PageRequest.of(page, count);

        // discarding the pagination information as it won't be relevant to
        // our application.
        return bookRepository.findAll(searchSpec, pageable).getContent();
    }

    @Override
    public Book getBook(int bookId) {
        return bookRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book", "bookId", bookId)
        );
    }

    @Override
    @Transactional
    public Book updateLocation(int bookId, Book toUpdate) {
        Book book = getBook(bookId);

        book.setShelfLocation(toUpdate.getShelfLocation());
        book.setRowLocation(toUpdate.getRowLocation());
        book.setColumnLocation(toUpdate.getColumnLocation());

        // update the status to available since the book is back
        book.setStatus(BookStatus.AVAILABLE);

        return bookRepository.save(book);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
}
