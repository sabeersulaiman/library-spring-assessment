package com.ssabeer.libraryspring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssabeer.libraryspring.models.Book;
import com.ssabeer.libraryspring.models.enums.BookStatus;
import com.ssabeer.libraryspring.services.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LibraryApplication {
	@EventListener
	public void seedBooks(ContextRefreshedEvent event) {
		// seed the books
		BookService bookService = event.getApplicationContext().getBean(BookService.class);
		int shelf = 1, row = 1, column = 1;

		try {
			ObjectMapper mapper = new ObjectMapper();
			ClassLoader classLoader = this.getClass().getClassLoader();
			Book[] books = mapper.readValue(
							classLoader.getResource("books.json"), Book[].class);

			for(Book book: books) {
				book.setShelfLocation(shelf);
				book.setRowLocation(row);
				book.setColumnLocation(column);

				book.setStatus(BookStatus.AVAILABLE);

				bookService.saveBook(book);

				// considering 10 shelf with 10 rows having 10 columns each - for ease
				column ++;
				if(column > 10) {
					row++;
					column = 1;
				}
				if(row > 10) {
					shelf ++;
					row = 1;
				}
				if(shelf > 10) {
					shelf = 1;
					row = 1;
					column = 1;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

}
