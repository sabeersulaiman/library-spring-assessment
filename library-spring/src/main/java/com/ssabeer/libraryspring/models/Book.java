package com.ssabeer.libraryspring.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssabeer.libraryspring.models.enums.BookStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(of = "bookId", callSuper = false)
@Entity
@Table(name = "books")
@JsonIgnoreProperties(value = {"dateAdded", "dateModified", "deleted"},
        allowGetters = true)
public class Book extends AuditModel {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookId;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "book_image")
    private String bookImage;

    @Column(name = "book_description")
    private String bookDescription;

    @Column(name = "author", nullable = false)
    private String Author;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "shelf_location")
    private Integer shelfLocation;

    @Column(name = "row_location")
    private Integer rowLocation;

    @Column(name = "column_location")
    private Integer columnLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookStatus status;
}
