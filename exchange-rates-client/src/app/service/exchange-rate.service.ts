import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Currency } from '../model/currency';
import { CurrentExchangeRates } from '../model/current-exchange-rates';
import { HistoricalExchangeRates } from '../model/historical-exchange-rates';


@Injectable()
export class ExchangeRateService {

    private baseUrl: string;
    private buyCurrentPrefix: string;
    private sellCurrentPrefix: string;
    private buyHistoryPrefix: string;
    private sellHistoryPrefix: string;
    private allCurrencies: Currency[];
    private subjectCurrency: Currency;
    private comparisonCurrencies: Currency[];
    private candidateComparisonCurrencies: Currency[];

    constructor(private httpClient: HttpClient) {
        console.log('DEBUG: Constructing ExchangeRateService');
        this.baseUrl = 'http://localhost:8080/exchange-rates';
        this.buyCurrentPrefix = this.baseUrl + '/buy/current';
        this.sellCurrentPrefix = this.baseUrl + '/sell/current';
        this.buyHistoryPrefix = this.baseUrl + '/buy/history';
        this.sellHistoryPrefix = this.baseUrl + '/sell/history';
        this.subjectCurrency = new Currency();
        this.comparisonCurrencies = new Array<Currency>();
    }

    public getCurrentBuyingExchangeRates(): Observable<CurrentExchangeRates> {
        if ((this.subjectCurrency.id === undefined) && (this.comparisonCurrencies.length === 0)) {
            return this.httpClient.get<CurrentExchangeRates>(this.buyCurrentPrefix);
        } else {
            const url = this.buyCurrentPrefix + '/' + this.subjectCurrency.id;
            let params = new HttpParams();
            for (let i = 0; i < this.comparisonCurrencies.length; i++) {
                params = params.append('sellingCurrencyIds', this.comparisonCurrencies[i].id);
            }
            return this.httpClient.get<CurrentExchangeRates>(url, { params: params });
        }
    }

    public getCurrentSellingExchangeRates(): Observable<CurrentExchangeRates> {
        if ((this.subjectCurrency.id === undefined) && (this.comparisonCurrencies.length === 0)) {
            return this.httpClient.get<CurrentExchangeRates>(this.sellCurrentPrefix);
        } else {
            const url = this.sellCurrentPrefix + '/' + this.subjectCurrency.id;
            let params = new HttpParams();
            for (let i = 0; i < this.comparisonCurrencies.length; i++) {
                params = params.append('buyingCurrencyIds', this.comparisonCurrencies[i].id);
            }
            return this.httpClient.get<CurrentExchangeRates>(url, { params: params });
        }
    }

    public getHistoricalBuyingExchangeRates(): Observable<HistoricalExchangeRates> {
        if ((this.subjectCurrency.id === undefined) && (this.comparisonCurrencies.length === 0)) {
            return this.httpClient.get<HistoricalExchangeRates>(this.buyHistoryPrefix);
        } else {
            const url = this.buyHistoryPrefix + '/' + this.subjectCurrency.id;
            let params = new HttpParams();
            for (let i = 0; i < this.comparisonCurrencies.length; i++) {
                params = params.append('sellingCurrencyIds', this.comparisonCurrencies[i].id);
            }
            return this.httpClient.get<HistoricalExchangeRates>(url, { params: params });
        }
    }

    public getHistoricalSellingExchangeRates(): Observable<HistoricalExchangeRates> {
        if ((this.subjectCurrency.id === undefined) && (this.comparisonCurrencies.length === 0)) {
            return this.httpClient.get<HistoricalExchangeRates>(this.sellHistoryPrefix);
        } else {
            const url = this.sellHistoryPrefix + '/' + this.subjectCurrency.id;
            let params = new HttpParams();
            for (let i = 0; i < this.comparisonCurrencies.length; i++) {
                params = params.append('buyingCurrencyIds', this.comparisonCurrencies[i].id);
            }
            return this.httpClient.get<HistoricalExchangeRates>(url, { params: params });
        }
    }

    public setAllCurrencies(allCurrencies: Currency[]) {
        this.allCurrencies = allCurrencies;
        this.setCandidateComparisonCurrencies();
    }

    public setSubjectCurrency(subjectCurrency: Currency) {
        this.subjectCurrency = subjectCurrency;
        this.setCandidateComparisonCurrencies();
    }

    public setCandidateComparisonCurrencies() {
        const subjectCurrency = this.subjectCurrency;
		const comparisonCurrencies = this.comparisonCurrencies;
        if ((this.subjectCurrency !== undefined) && (this.allCurrencies !== undefined) && (this.comparisonCurrencies != undefined)) {
            this.candidateComparisonCurrencies = this.allCurrencies.slice();
            this.candidateComparisonCurrencies = this.candidateComparisonCurrencies.filter(function (currency) {
  			let alreadySelected = false;
		        if (subjectCurrency.id === currency.id) {
			        alreadySelected = true;
		        }
                if (alreadySelected === false) {		
			        for (let i = 0; i < comparisonCurrencies.length; i++) {
				        if (comparisonCurrencies[i].id === currency.id) {
					        alreadySelected = true;	
				        }	
			        }
		        }
		        return !alreadySelected;
            });
        }
    }
	
    public setComparisonCurrencies(comparisonCurrencies: Currency[]) {
        this.comparisonCurrencies = comparisonCurrencies;
		this.setCandidateComparisonCurrencies();
    }

    public getAllCurrencies() {
        return this.allCurrencies;
    }

    public getSubjectCurrency() {
        return this.subjectCurrency;
    }

    public getComparisonCurrencies() {
        return this.comparisonCurrencies;
    }

    public getCandidateComparisonCurrencies() {
        return this.candidateComparisonCurrencies;
    }

}
