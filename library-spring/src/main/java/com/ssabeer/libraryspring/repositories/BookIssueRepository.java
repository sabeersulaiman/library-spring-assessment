package com.ssabeer.libraryspring.repositories;

import com.ssabeer.libraryspring.models.BookIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookIssueRepository extends JpaRepository<BookIssue, Integer> {
}
