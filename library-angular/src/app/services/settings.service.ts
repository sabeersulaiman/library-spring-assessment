import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class SettingsService {
    private baseUrl: string;

    constructor() {
        this.baseUrl = 'http://localhost:8080/';
    }

    getBaseUrl(): string {
        return this.baseUrl;
    }
}
