import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './components/app/app.component';
import { HeaderComponent } from './components/header/header.component';
import { ContainerComponent } from './components/container/container.component';
import { AlertComponent } from './components/alert/alert.component';
import { BookListComponent } from './components/book-list/book-list.component';
import { BookSearchComponent } from './components/book-search/book-search.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { BookViewComponent } from './components/book-view/book-view.component';

@NgModule({
    declarations: [
        AppComponent,
        HeaderComponent,
        ContainerComponent,
        AlertComponent,
        BookListComponent,
        BookSearchComponent,
        BookViewComponent
    ],
    imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {}
