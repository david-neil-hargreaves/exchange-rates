import { Component, OnInit } from '@angular/core';
import { HistoricalExchangeRates } from '../model/historical-exchange-rates';
import { CurrencyService } from '../service/currency.service';
import { ExchangeRateService } from '../service/exchange-rate.service';
import { Subscription } from 'rxjs/Subscription';

@Component({
    selector: 'app-history-sell-exchange-rates',
    templateUrl: './history-sell-exchange-rates.component.html',
    styleUrls: ['./history-sell-exchange-rates.component.css']
})
export class HistorySellExchangeRatesComponent implements OnInit {

    historicalExchangeRates: HistoricalExchangeRates;
    private masterSubscription: Subscription = new Subscription();

    constructor(private currencyService: CurrencyService, private exchangeRateService: ExchangeRateService) {
        console.log('DEBUG: Constructing HistorySellExchangeRatesComponent');
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
        const exchangeRatesSubscription = this.exchangeRateService.getHistoricalSellingExchangeRates().subscribe(data => {
            this.historicalExchangeRates = data;
        });
        this.masterSubscription.add(exchangeRatesSubscription);
    }

}
