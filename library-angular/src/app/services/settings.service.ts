import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})
export class SettingsService {
    private baseUrl: string;

    constructor() {
        this.baseUrl = environment.baseUrl;
    }

    getBaseUrl(): string {
        return this.baseUrl;
    }
}
