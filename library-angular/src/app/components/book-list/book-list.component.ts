import {
    Component,
    OnInit,
    ViewChild,
    OnDestroy,
    AfterViewInit,
    ContentChild
} from '@angular/core';
import {
    BookSearch,
    BookSearchComponent
} from '../book-search/book-search.component';
import { BookService } from 'src/app/services/books.service';
import { switchMap } from 'rxjs/operators';
import { Subscription } from 'rxjs';
import { Book } from 'src/app/models/book.model';

@Component({
    selector: 'app-book-list',
    templateUrl: './book-list.component.html',
    styleUrls: ['./book-list.component.scss']
})
export class BookListComponent implements AfterViewInit, OnDestroy {
    page = 0;
    count = 10;

    @ViewChild(BookSearchComponent, { static: false })
    searcher: BookSearchComponent;

    searchSubscription: Subscription;

    bookList: Book[] = [];

    currentSearchOptions: BookSearch = {};
    listEnded = false;

    constructor(private books: BookService) {
        this.listItems();
    }

    ngAfterViewInit() {
        this.searchSubscription = this.searcher.search
            .pipe(
                switchMap(opts => {
                    this.page = 0;
                    this.bookList = [];
                    this.listEnded = false;
                    this.currentSearchOptions = opts;

                    return this.books.getBooks(
                        opts.name,
                        opts.author,
                        opts.isbn,
                        this.page,
                        this.count
                    );
                })
            )
            .subscribe(
                r => {
                    this.bookList = this.bookList.concat(r);
                },
                e => {}
            );
    }

    listItems() {
        this.books
            .getBooks(
                this.currentSearchOptions.name,
                this.currentSearchOptions.author,
                this.currentSearchOptions.isbn,
                this.page,
                this.count
            )
            .subscribe(
                r => {
                    if (r.length < this.count) {
                        this.listEnded = true;
                    }
                    this.bookList = this.bookList.concat(r);
                },
                e => {}
            );
    }

    viewMore() {
        if (this.listEnded) {
            return;
        }
        this.page++;
        this.listItems();
    }

    ngOnDestroy() {
        this.searchSubscription.unsubscribe();
    }
}
