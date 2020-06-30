import { TestBed, inject } from '@angular/core/testing';
import { HttpClient } from '@angular/common/http';
import { ExchangeRateService } from './exchange-rate.service';
import { CurrencyService } from './currency.service';

describe('ExchangeRateService', () => {
  beforeEach(() => {
	  
	const httpClientSpy = jasmine.createSpyObj('HttpClient', ['getValue']);
	const currencyServiceSpy = jasmine.createSpyObj('CurrencyService', ['getValue']);
	  
    TestBed.configureTestingModule({
      providers: [
	    ExchangeRateService,
		{ provide: HttpClient, useValue: httpClientSpy },
		{ provide: CurrencyService, useValue: currencyServiceSpy }
	  
	  ]
    });
  });

  it('should be created', inject([ExchangeRateService], (service: ExchangeRateService) => {
    expect(service).toBeTruthy();
  }));
});
