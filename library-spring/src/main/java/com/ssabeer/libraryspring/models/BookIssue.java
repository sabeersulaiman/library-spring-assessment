package com.ssabeer.libraryspring.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssabeer.libraryspring.models.enums.BookIssueStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode(of = "issueId", callSuper = false)
@Entity
@Table(name = "book_issues")
@JsonIgnoreProperties(value = {"dateAdded", "dateModified", "deleted"},
        allowGetters = true)
public class BookIssue extends AuditModel {
    @Id
    @Column(name = "issue_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer issueId;

    @Column(name = "person_name", nullable = false)
    private String personName;

    @Column(name = "issued_from_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date issuedFrom;

    @Column(name = "issued_to_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date issuedTo;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId")
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(name = "issue_status", nullable = false)
    private BookIssueStatus issueStatus;
}
