package com.ssabeer.libraryspring.services;

import com.ssabeer.libraryspring.exceptions.BadRequestException;
import com.ssabeer.libraryspring.exceptions.BookNotAvailableException;
import com.ssabeer.libraryspring.exceptions.BookNotIssuedException;
import com.ssabeer.libraryspring.exceptions.ResourceNotFoundException;
import com.ssabeer.libraryspring.models.Book;
import com.ssabeer.libraryspring.models.BookIssue;
import com.ssabeer.libraryspring.models.enums.BookIssueStatus;
import com.ssabeer.libraryspring.models.enums.BookStatus;
import com.ssabeer.libraryspring.repositories.BookIssueRepository;
import com.ssabeer.libraryspring.repositories.BookRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookIssueServiceImpl implements BookIssueService {
    private final BookIssueRepository bookIssueRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BookIssueServiceImpl(BookIssueRepository bookIssueRepository,
                                BookRepository bookRepository) {
        this.bookIssueRepository = bookIssueRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public BookIssue createBookIssue(@NonNull BookIssue issue) {
        if(issue.getBook() == null) {
            throw new BadRequestException("BookIssue", "book", null);
        }
        Book book = bookRepository.findById(issue.getBook().getBookId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Book", "bookId", issue.getBook().getBookId()));

        if(book.getStatus() != BookStatus.AVAILABLE) {
            // book is not available for issuing
            throw new BookNotAvailableException();
        }

        book.setStatus(BookStatus.ISSUED);
        if(issue.getIssuedFrom().after(issue.getIssuedTo())) {
            throw new BadRequestException("BookIssue", "issuedFrom", "(invalid) From should be less than to");
        }
        bookRepository.save(book);

        issue.setBook(book);
        issue.setIssueStatus(BookIssueStatus.CIRCULATED);

        return bookIssueRepository.save(issue);
    }

    @Override
    public List<BookIssue> getAllIssues(int bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "bookId", bookId));

        // will fetch this lazily at this point
        return book.getBookIssues();
    }

    @Override
    public BookIssue returnBook(BookIssue issue) {
        if(issue.getBook() == null) {
            throw new BadRequestException("BookIssue", "book", null);
        }
        Book book = bookRepository.findById(issue.getBook().getBookId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Book", "bookId", issue.getBook().getBookId()));

        if(book.getStatus() != BookStatus.ISSUED) {
            // book is not available for issuing
            throw new BookNotIssuedException();
        }

        book.setStatus(BookStatus.AVAILABLE);
        issue.setBook(book);

        bookRepository.save(book);

        issue.setIssueStatus(BookIssueStatus.RETURNED);
        return bookIssueRepository.save(issue);
    }
}
