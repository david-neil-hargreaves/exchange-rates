import { Injectable, ErrorHandler, Injector } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable()
export class CustomErrorHandlerService implements ErrorHandler {

    constructor(private injector: Injector) { }

    handleError(error: any) {
        const router = this.injector.get(Router);
        //console.log('URL: ' + router.url);
        //console.error('QUAGGA');
        if (error instanceof HttpErrorResponse) {
            console.error('Error status code: ', error.status);
            console.error(error.message);
        } else {
            // A client-side or network error occurred.
            console.error(error.message);
        }
        router.navigate(['/error']);
    }

}
