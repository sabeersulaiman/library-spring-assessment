package com.ssabeer.libraryspring.controllers;

import com.ssabeer.libraryspring.exceptions.LocationAlreadyTakenException;
import com.ssabeer.libraryspring.models.Book;
import com.ssabeer.libraryspring.models.BookIssue;
import com.ssabeer.libraryspring.services.BookIssueService;
import com.ssabeer.libraryspring.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/books")
public class BookController {
    private final BookService bookService;
    private final BookIssueService bookIssueService;

    @Autowired
    public BookController(BookService bookService, BookIssueService bookIssueService) {
        this.bookService = bookService;
        this.bookIssueService = bookIssueService;
    }

    @GetMapping
    public List<Book> listBooks(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "isbn", required = false) String isbn,
            @RequestParam(name = "page", required = false) int page,
            @RequestParam(name = "count", required = false) int count) {
        // count is constrained into [1, 100] - else defaults to 10
        if(count > 100 || count <= 0) count = 10;
        if(page < 0) page = 0;

        // if any of the search params is just whitespace, we set it to null,
        // so that they could be removed from the search
        if(name != null && name.isBlank()) name = null;
        if(author != null && author.isBlank()) author = null;
        if(isbn != null && isbn.isBlank()) isbn = null;

        return bookService.loadBooks(name, author, isbn, page, count);
    }

    @GetMapping("{id}/location")
    public Book getBook(@PathVariable(name = "id") int bookId) {
        return bookService.getBook(bookId);
    }

    @PutMapping("{id}/location")
    public Book updateBookLocation(@PathVariable(name = "id") int bookId,
                                   @RequestBody @Valid Book book) {
        try {
            return bookService.updateLocation(bookId, book);
        }
        catch (DataIntegrityViolationException e) {
            if(e.getCause() != null && e.getCause() instanceof ConstraintViolationException
            ) {
                throw new LocationAlreadyTakenException();
            }
            else {
                throw e;
            }
        }
    }

    @PostMapping("issue")
    public BookIssue createBookIssue(@RequestBody @Valid BookIssue issue) {
        return bookIssueService.createBookIssue(issue);
    }

    @PutMapping("issue")
    public BookIssue returnBook(@RequestBody @Valid BookIssue issue) {
        return bookIssueService.returnBook(issue);
    }

    @GetMapping("{id}/issue-history")
    public List<BookIssue> getAllBookIssues(@PathVariable(name = "id") int bookId) {
        return bookIssueService.getAllIssues(bookId);
    }
}
