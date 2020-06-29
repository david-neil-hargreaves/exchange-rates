import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Currency } from '../model/currency';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class CurrencyService {

  private baseUrl: string;
  private allUrl: string;
  private defaultSubjectCurrencyUrl: string;
  private defaultComparisonCurrenciesUrl: string;
  currencies: Currency[];	
 
  constructor(private httpClient: HttpClient) {
	console.log('DEBUG: Constructing CurrencyService');
	this.baseUrl = 'http://localhost:8080/currencies';	
	this.allUrl = this.baseUrl + "/all";
	this.defaultSubjectCurrencyUrl = this.baseUrl + "/defaultSubjectCurrency";
	this.defaultComparisonCurrenciesUrl = this.baseUrl + "/defaultComparisonCurrencies";
  }
 
  public fetchAllCurrencies(): Observable<Currency[]> {
	return this.httpClient.get<Currency[]>(this.allUrl);
  }
  
  public fetchDefaultSubjectCurrency(): Observable<Currency> {
	return this.httpClient.get<Currency>(this.defaultSubjectCurrencyUrl);
  }
  
  public fetchDefaultComparisonCurrencies(): Observable<Currency[]> {
	return this.httpClient.get<Currency[]>(this.defaultComparisonCurrenciesUrl);
  }
  
}
