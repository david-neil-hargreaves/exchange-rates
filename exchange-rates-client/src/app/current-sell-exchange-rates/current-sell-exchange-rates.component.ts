import { Component, OnInit } from '@angular/core';
import { CurrentExchangeRates } from '../model/current-exchange-rates';
import { CurrencyService } from '../service/currency.service';
import { ExchangeRateService } from '../service/exchange-rate.service';
import { Subscription } from 'rxjs/Subscription';

@Component({
    selector: 'app-current-sell-exchange-rates',
    templateUrl: './current-sell-exchange-rates.component.html',
    styleUrls: ['./current-sell-exchange-rates.component.css']
})
export class CurrentSellExchangeRatesComponent implements OnInit {

    currentExchangeRates: CurrentExchangeRates;
    private masterSubscription: Subscription = new Subscription();

    constructor(private currencyService: CurrencyService, private exchangeRateService: ExchangeRateService) {
        console.log('DEBUG: Constructing CurrentSellExchangeRatesComponent');
    }

    ngOnInit() {
        if (this.exchangeRateService.getSubjectCurrency() === undefined) {
            const defaultSubjectCurrencySubscription = this.currencyService.fetchDefaultSubjectCurrency().subscribe(data => {
                this.exchangeRateService.setSubjectCurrency(data);
            });
            this.masterSubscription.add(defaultSubjectCurrencySubscription);
        }
        if (this.exchangeRateService.getComparisonCurrencies() === undefined) {
            const defaultComparisonCurrenciesSubscription = this.currencyService.fetchDefaultComparisonCurrencies().subscribe(data => {
                this.exchangeRateService.setComparisonCurrencies(data);
            });
            this.masterSubscription.add(defaultComparisonCurrenciesSubscription);
        }
        const exchangeRatesSubscription = this.exchangeRateService.getCurrentSellingExchangeRates().subscribe(data => {
            this.currentExchangeRates = data;
        });
        this.masterSubscription.add(exchangeRatesSubscription);
    }

}
