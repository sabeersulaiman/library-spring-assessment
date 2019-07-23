package com.ssabeer.libraryspring.services;

import com.ssabeer.libraryspring.models.BookIssue;

import java.util.List;

public interface BookIssueService {
    BookIssue createBookIssue(BookIssue issue);

    List<BookIssue> getAllIssues(int bookId);

    BookIssue returnBook(BookIssue issue);
}
