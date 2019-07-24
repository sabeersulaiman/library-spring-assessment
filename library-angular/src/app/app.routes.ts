import { Routes } from '@angular/router';
import { ContainerComponent } from './components/container/container.component';
import { BookListComponent } from './components/book-list/book-list.component';

export const appRoutes: Routes = [
    {
        path: '',
        component: ContainerComponent,
        children: [
            {
                path: '',
                component: BookListComponent
            }
        ]
    }
];
