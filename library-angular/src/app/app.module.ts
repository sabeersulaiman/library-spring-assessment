import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './components/app/app.component';
import { HeaderComponent } from './components/header/header.component';
import { ContainerComponent } from './components/container/container.component';
import { AlertComponent } from './components/alert/alert.component';
import { BookListComponent } from './components/book-list/book-list.component';

@NgModule({
    declarations: [
        AppComponent,
        HeaderComponent,
        ContainerComponent,
        AlertComponent,
        BookListComponent
    ],
    imports: [BrowserModule, AppRoutingModule],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {}
