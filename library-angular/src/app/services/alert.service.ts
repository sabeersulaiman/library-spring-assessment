import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class AlertService {
    private alertSubject = new Subject<string>();
    alertData = this.alertSubject.asObservable();

    constructor() {}

    show(msg: string) {
        this.alertSubject.next(msg);
    }

    hide() {
        this.alertSubject.next(null);
    }
}
