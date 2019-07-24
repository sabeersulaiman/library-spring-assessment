import { Routes } from '@angular/router';
import { ContainerComponent } from './components/container/container.component';
import { BookListComponent } from './components/book-list/book-list.component';
import { BookViewComponent } from './components/book-view/book-view.component';

export const appRoutes: Routes = [
    {
        path: '',
        component: ContainerComponent,
        children: [
            {
                path: '',
                component: BookListComponent,
                children: [
                    { path: 'book/:bookId', component: BookViewComponent }
                ]
            }
        ]
    }
];
