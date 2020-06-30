import { TestBed, inject } from '@angular/core/testing';
import { HttpClient } from '@angular/common/http';
import { CurrencyService } from './currency.service';

// let currencyService: CurrencyService;
// let httpClientSpy: jasmine.SpyObj<HttpClient>;

describe('CurrencyService', () => {
  beforeEach(() => {
	
	const httpClientSpy = jasmine.createSpyObj('HttpClient', ['getValue']);
    
	TestBed.configureTestingModule({
      providers: [
	    CurrencyService,
	    { provide: HttpClient, useValue: httpClientSpy }
	  ]
    });
  });

  it('should be created', inject([CurrencyService], (service: CurrencyService) => {
    expect(service).toBeTruthy();
  }));
});
