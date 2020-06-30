import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute, Router } from '@angular/router';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
// import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CurrencyExchangeRates } from '../model/currency-exchange-rates';
import { ExchangeRateService } from '../service/exchange-rate.service';
import { CurrencyService } from '../service/currency.service';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { MenuReactiveFormComponent } from './menu-reactive-form.component';

describe('MenuReactiveFormComponent', () => {
  let component: MenuReactiveFormComponent;
  let fixture: ComponentFixture<MenuReactiveFormComponent>;
  
  class MockFormBuilder {
	public group() {
	  return new FormGroup({
        new FormControl(),
		new FormArray()
      });
    }
	
	public array() {
	  return new FormArray();
    }
  }	  
  
  class MockCurrencyService {
	  public fetchAllCurrencies () {
		  let currencies = [
			{
			  id: "1",
			  code: "EUR",
			  description: "Euros"
			},
			{
			  id: "2",
			  code: "GBP",
			  description: "Pounds sterling"
			},
			{
			  id: "3",
			  code: "HKD",
			  description: "Hong Kong dollars"
			}
			];
	    return Observable.of(currencies);
	  }
	  
	  public fetchDefaultSubjectCurrency(){
		  let currency = 
			{
			  id: "1",
			  code: "EUR",
			  description: "Euros"
			};
	    return Observable.of(currency);
	  }	  
  }	  
  
  class MockExchangeRateService {
	public getHistoricalBuyingExchangeRates(): Observable<CurrencyExchangeRates> {  
	  return Observable.of(null);
	}
	
	public getAllCurrencies() {
	  return null;
	}
	  
	public getSubjectCurrency() {
	  let currency = 
		{
		  id: "1",
		  code: "EUR",
		  description: "Euros"
		};
	  return Observable.of(currency);
	}
	  
	public getComparisonCurrencies() {
	  let currencies = [
	    {
          id: "2",
          code: "GBP",
          description: "Pounds sterling"
        },
		{
          id: "3",
          code: "HKD",
          description: "Hong Kong dollars"
        }
		];
	  return currencies;
	}
	  
	public getCandidateComparisonCurrencies() {
	  return null;
	}
	
	public setAllCurrencies(){
	}
	
	public setSubjectCurrency(){
	}	

  }
  
  beforeEach(async(() => {
	
	const formBuilderSpy = jasmine.createSpyObj('FormBuilder', ['getValue']);
	const activatedRouteSpy = jasmine.createSpyObj('ActivatedRoute', ['getValue']);
	const routerSpy = jasmine.createSpyObj('Router', ['getValue']);
		
    TestBed.configureTestingModule({
      declarations: [ MenuReactiveFormComponent ],
      providers: [
	   	//{ provide: FormBuilder, useClass: MockFormBuilder },
		FormBuilder,
		{ provide: ActivatedRoute, useValue: activatedRouteSpy },
		{ provide: Router, useValue: routerSpy },
		{ provide: CurrencyService, useClass: MockCurrencyService },
	    { provide: ExchangeRateService, useClass: MockExchangeRateService }
	  ]
	  
	  
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuReactiveFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
