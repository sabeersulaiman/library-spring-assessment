import {
    Component,
    OnDestroy,
    AfterViewInit,
    Renderer2,
    Inject
} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from 'src/app/services/books.service';
import { AlertService } from 'src/app/services/alert.service';
import { Book, BookStatus, BookIssue } from 'src/app/models/book.model';
import { DOCUMENT } from '@angular/common';

@Component({
    selector: 'app-book-view',
    templateUrl: './book-view.component.html',
    styleUrls: ['./book-view.component.scss']
})
export class BookViewComponent implements OnDestroy, AfterViewInit {
    bookId: number;
    selectedBook: Book;
    loading = true;
    bookStatus = BookStatus;
    issueVisible = false;
    personName: string;
    issueLoading: boolean;
    issueList: BookIssue[];
    issueListLoading: boolean;

    constructor(
        activatedRoute: ActivatedRoute,
        private router: Router,
        private books: BookService,
        private renderer: Renderer2,
        private alert: AlertService,
        @Inject(DOCUMENT) private document: Document
    ) {
        activatedRoute.params.subscribe(params => {
            this.bookId = +params.bookId;
            this.books.getBook(this.bookId).subscribe(
                r => {
                    this.selectedBook = r;
                    this.loading = false;
                },
                e => {
                    this.router.navigate(['/']);
                }
            );
        });
    }

    issueBook() {
        if (this.personName && this.personName.trim() !== '') {
            const from = new Date();
            const to = new Date(from.getTime() + 864e5);

            const issue: BookIssue = {
                book: this.selectedBook,
                issuedFrom: from,
                issuedTo: to,
                personName: this.personName
            };

            this.issueLoading = true;
            this.books.addBookIssue(issue).subscribe(
                r => {
                    this.selectedBook = r.book;
                    this.personName = null;
                    this.issueVisible = false;
                    this.issueLoading = false;
                },
                e => {
                    this.alert.show(
                        'An error occured while issuing the book, please try again.'
                    );
                    this.issueLoading = false;
                }
            );
        } else {
            this.alert.show('Please enter a valid name before continuing.');
        }
    }

    loadIssueList() {
        this.issueListLoading = true;
        this.books.getIssueHistory(this.selectedBook.bookId).subscribe(
            r => {
                this.issueList = r;
                this.issueListLoading = false;
            },
            e => {
                this.issueListLoading = false;
            }
        );
    }

    returnBook(issue: BookIssue) {
        this.books.returnBook(issue).subscribe(
            r => {
                this.selectedBook = r.book;
                issue.issueStatus = r.issueStatus;
            },
            e => {}
        );
    }

    ngAfterViewInit() {
        this.renderer.setStyle(this.document.body, 'overflow-y', 'hidden');
    }

    ngOnDestroy() {
        this.renderer.setStyle(this.document.body, 'overflow-y', 'auto');
    }
}
