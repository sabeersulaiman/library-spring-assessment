import {
    Component,
    Output,
    EventEmitter,
    ViewChild,
    TemplateRef,
    ElementRef
} from '@angular/core';
import { BookService } from 'src/app/services/books.service';
import { Subject, Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';

interface SearchOption {
    title: string;
    value: string;
}

export interface BookSearch {
    name?: string;
    author?: string;
    isbn?: string;
}

@Component({
    selector: 'app-book-search',
    templateUrl: './book-search.component.html',
    styleUrls: ['book-search.component.scss']
})
export class BookSearchComponent {
    searchOptions: SearchOption[] = [
        {
            title: 'By Name',
            value: 'name'
        },
        {
            title: 'By Author',
            value: 'author'
        },
        {
            title: 'By ISBN',
            value: 'isbn'
        }
    ];

    searchOption = 'name';

    @ViewChild('search', { static: false })
    searchElement: ElementRef;

    searchKeyWord$ = new Subject<string>();

    @Output()
    search = new EventEmitter<BookSearch>();

    constructor() {
        this.initiateSearch(this.searchKeyWord$);
    }

    initiateSearch(keys: Observable<string>) {
        keys.pipe(
            debounceTime(400),
            distinctUntilChanged()
        ).subscribe(term => this.search.next(this.getSearchOptions(term)));
    }

    getSearchOptions(term: string): BookSearch {
        if (this.searchOption === 'name') {
            return { name: term };
        }
        if (this.searchOption === 'author') {
            return { author: term };
        }
        if (this.searchOption === 'isbn') {
            return { isbn: term };
        }

        return null;
    }

    onOptionChange() {
        this.searchKeyWord$.next('');
        (this.searchElement.nativeElement as HTMLInputElement).value = '';
    }
}
