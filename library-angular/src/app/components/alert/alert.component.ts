import { Component, OnDestroy } from '@angular/core';
import { AlertService } from 'src/app/services/alert.service';
import { Subscription } from 'rxjs';

@Component({
    selector: 'app-alert',
    templateUrl: './alert.component.html',
    styleUrls: ['./alert.component.scss']
})
export class AlertComponent implements OnDestroy {
    visible = false;
    message: string = null;
    alertSubscription: Subscription;

    constructor(alertService: AlertService) {
        this.alertSubscription = alertService.alertData.subscribe(m => {
            if (m === null) {
                this.hide();
                return;
            }

            this.message = m;
            this.visible = true;
        });
    }

    hide() {
        this.visible = false;
        this.message = null;
    }

    ngOnDestroy() {
        this.alertSubscription.unsubscribe();
    }
}
