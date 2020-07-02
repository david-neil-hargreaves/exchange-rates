import { Component } from '@angular/core';
import { Currency } from './model/currency';
import { ExchangeRateService } from './service/exchange-rate.service';
import { CurrencyService } from './service/currency.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {

    title: string;

    constructor() {
        console.log('DEBUG: Constructing AppComponent');
        this.title = 'Exchange Rates';
    }

}
