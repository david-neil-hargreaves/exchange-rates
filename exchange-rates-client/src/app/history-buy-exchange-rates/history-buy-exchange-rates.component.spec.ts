import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { CurrencyExchangeRates } from '../model/currency-exchange-rates';
import { ExchangeRateService } from '../service/exchange-rate.service';
import { CurrencyService } from '../service/currency.service';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { HistoryBuyExchangeRatesComponent } from './history-buy-exchange-rates.component';

describe('HistoryBuyExchangeRatesComponent', () => {
  let component: HistoryBuyExchangeRatesComponent;
  let fixture: ComponentFixture<HistoryBuyExchangeRatesComponent>;
  
  class MockCurrencyService {
	  
  }	  
  
  class MockExchangeRateService {
	public getHistoricalBuyingExchangeRates(): Observable<CurrencyExchangeRates> {  
	  return Observable.of(null);
	}
	
	public getAllCurrencies() {
	  return null;
	}
	  
	public getSubjectCurrency() {
	  return null;
	}
	  
	public getComparisonCurrencies() {
	  return null;
	}
	  
	public getCandidateComparisonCurrencies() {
	  return null;
	}
  }	  

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HistoryBuyExchangeRatesComponent ],
	  providers: [
	    HistoryBuyExchangeRatesComponent,
		{ provide: CurrencyService, useClass: MockCurrencyService },
	    { provide: ExchangeRateService, useClass: MockExchangeRateService }
	  ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoryBuyExchangeRatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
