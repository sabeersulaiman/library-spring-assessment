import { Injectable } from '@angular/core';
import { SettingsService } from './settings.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { trimTrailingNulls } from '@angular/compiler/src/render3/view/util';
import { Book, BookIssue } from '../models/book.model';

@Injectable({
    providedIn: 'root'
})
export class BookService {
    private basePath: string;

    constructor(settings: SettingsService, private http: HttpClient) {
        this.basePath = settings.getBaseUrl() + 'v1/books/';
    }

    getBooks(
        name: string,
        author: string,
        isbn: string,
        page: number,
        count: number
    ) {
        let params = new HttpParams()
            .set('page', page.toString())
            .set('count', count.toString());

        if (name && name.trim() !== '') {
            params = params.set('name', name);
        }
        if (author && author.trim() !== '') {
            params = params.set('author', author);
        }
        if (isbn && isbn.trim() !== '') {
            params = params.set('isbn', isbn);
        }

        return this.http.get<Book[]>(this.basePath, { params });
    }

    getBook(bookId: number) {
        return this.http.get<Book>(`${this.basePath}${bookId}/location`);
    }

    updateBookLocation(book: Book) {
        return this.http.put<Book>(
            `${this.basePath}${book.bookId}/location`,
            book
        );
    }

    addBookIssue(issue: BookIssue) {
        return this.http.post<BookIssue>(this.basePath + 'issue', issue);
    }

    returnBook(issue: BookIssue) {
        return this.http.put<BookIssue>(this.basePath + 'issue', issue);
    }

    getIssueHistory(bookId: number) {
        return this.http.get<BookIssue[]>(
            `${this.basePath}${bookId}/issue-history`
        );
    }
}
