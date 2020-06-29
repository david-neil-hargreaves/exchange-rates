import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Currency } from '../model/currency';
import { CurrencyExchangeRates } from '../model/currency-exchange-rates';
import { CurrencyService } from '../service/currency.service';

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
 
  constructor(private httpClient: HttpClient, private currencyService: CurrencyService) {
	console.log('DEBUG: Constructing ExchangeRateService');
	this.baseUrl = 'http://localhost:8080/exchange-rates';	
	this.buyCurrentPrefix = this.baseUrl + "/buy/current";
	this.sellCurrentPrefix = this.baseUrl + "/sell/current";
	this.buyHistoryPrefix = this.baseUrl + "/buy/history";
	this.sellHistoryPrefix = this.baseUrl + "/sell/history";
	this.subjectCurrency = new Currency();
	this.comparisonCurrencies = new Array<Currency>();
  }
 
  public getCurrentBuyingExchangeRates(): Observable<CurrencyExchangeRates> {
	if ((this.subjectCurrency.id === undefined) && (this.comparisonCurrencies.length === 0)) {
	  return this.httpClient.get<CurrencyExchangeRates>(this.buyCurrentPrefix);
	} else {
	  let url = this.buyCurrentPrefix + '/' + this.subjectCurrency.id;
	  let params = new HttpParams();
	  for (let i = 0; i < this.comparisonCurrencies.length; i++) {
	    params = params.append('sellingCurrencyIds', this.comparisonCurrencies[i].id);
      }
	  return this.httpClient.get<CurrencyExchangeRates>(url, {params: params});
	}
  }
  
  public getCurrentSellingExchangeRates(): Observable<CurrencyExchangeRates> {
	if ((this.subjectCurrency.id === undefined) && (this.comparisonCurrencies.length === 0)) {
	  return this.httpClient.get<CurrencyExchangeRates>(this.sellCurrentPrefix);
	} else {  
	  let url = this.sellCurrentPrefix + '/' + this.subjectCurrency.id;
	  let params = new HttpParams();
	  for (let i = 0; i < this.comparisonCurrencies.length; i++) {
	    params = params.append('buyingCurrencyIds', this.comparisonCurrencies[i].id);
      }
	  return this.httpClient.get<CurrencyExchangeRates>(url, {params: params});
	}  
  }
  
  public getHistoricalBuyingExchangeRates(): Observable<CurrencyExchangeRates> {
	if ((this.subjectCurrency.id === undefined) && (this.comparisonCurrencies.length === 0)) {
	  return this.httpClient.get<CurrencyExchangeRates>(this.buyHistoryPrefix);
	} else {   
	  let url = this.buyHistoryPrefix + '/' + this.subjectCurrency.id;
	  let params = new HttpParams();
	  for (let i = 0; i < this.comparisonCurrencies.length; i++) {
	    params = params.append('sellingCurrencyIds', this.comparisonCurrencies[i].id);
      }
	  return this.httpClient.get<CurrencyExchangeRates>(url, {params: params});
	}  
  }
  
  public getHistoricalSellingExchangeRates(): Observable<CurrencyExchangeRates> {
	if ((this.subjectCurrency.id === undefined) && (this.comparisonCurrencies.length === 0)) {
	  return this.httpClient.get<CurrencyExchangeRates>(this.sellHistoryPrefix);
	} else {     
	  let url = this.sellHistoryPrefix + '/' + this.subjectCurrency.id;
	  let params = new HttpParams();
	  for (let i = 0; i < this.comparisonCurrencies.length; i++) {
	    params = params.append('buyingCurrencyIds', this.comparisonCurrencies[i].id);
      }
	  return this.httpClient.get<CurrencyExchangeRates>(url, {params: params});
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
  
  private setCandidateComparisonCurrencies() {
	let subjectCurrency = this.subjectCurrency;  
	if ((this.subjectCurrency !== undefined) && (this.allCurrencies !== undefined)) {
      this.candidateComparisonCurrencies = this.allCurrencies.slice();
	  this.candidateComparisonCurrencies = this.candidateComparisonCurrencies.filter(function(currency) {
        return currency.id !== subjectCurrency.id;
	  })
	}
  }
  
  public setComparisonCurrencies(comparisonCurrencies: Currency[]) {
    this.comparisonCurrencies = comparisonCurrencies;
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
