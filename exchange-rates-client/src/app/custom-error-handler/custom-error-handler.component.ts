import { Component, ErrorHandler } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app-custom-error-handler',
    templateUrl: './custom-error-handler.component.html',
    styleUrls: ['./custom-error-handler.component.css']
})
export class CustomErrorHandlerComponent implements ErrorHandler {

    //private router: Router;

    //constructor(private router: Router) {
    //    console.log('DEBUG: Constructing CustomErrorHandlerComponent');
    //}

    handleError(error) {
        console.log("TODO " + error);
        //this.router.navigate(['/error']);
    }

}
