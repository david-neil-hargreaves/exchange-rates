import { ComponentFixture, TestBed, async, fakeAsync, tick } from '@angular/core/testing';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ExchangeRateService } from '../service/exchange-rate.service';
import { CurrencyService } from '../service/currency.service';
import { Currency } from '../model/currency';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { By } from "@angular/platform-browser";
import { MenuComponent } from './menu.component';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('MenuComponent', () => {
    let component: MenuComponent;
    let fixture: ComponentFixture<MenuComponent>;
    let currencyService: CurrencyService;
    let exchangeRateService: ExchangeRateService;
    let httpMock: HttpTestingController;

    const allCurrencies = [
        { id: '1', code: 'EUR', description: 'Euros' },
        { id: '2', code: 'HKD', description: 'Hong Kong Dollars' },
        { id: '3', code: 'GBP', description: 'Pounds Sterling' },
        { id: '4', code: 'USD', description: 'US Dollars' },
        { id: '5', code: 'HUF', description: 'Forints' }
    ];
    const defaultSubjectCurrency = { id: '1', code: 'EUR', description: 'Euros' }
        ;
    const subjectCurrency = { id: '1', code: 'EUR', description: 'Euros' }
        ;
    const euros = { id: '1', code: 'EUR', description: 'Euros' }
        ;    
    const hongKongDollars = { id: '2', code: 'HKD', description: 'Hong Kong Dollars' }
        ; 
    const poundsSterling = {  id: '3', code: 'GBP', description: 'Pounds Sterling' }
        ; 
    const usDollars = {  id: '4', code: 'USD', description: 'US Dollars' }
        ; 
    const forints = { id: '5', code: 'HUF', description: 'Forints'  }
        ;                 
    const defaultComparisonCurrencies = [
        { id: '2', code: 'HKD', description: 'Hong Kong Dollars' },
        { id: '3', code: 'GBP', description: 'Pounds Sterling' }
    ];
    const comparisonCurrencies = [
        { id: '2', code: 'HKD', description: 'Hong Kong Dollars' },
        { id: '3', code: 'GBP', description: 'Pounds Sterling' }
    ];
    const candidateComparisonCurrencies = [
        { id: '4', code: 'USD', description: 'US Dollars' },
        { id: '5', code: 'HUF', description: 'Forints' }
    ];
    const candidateComparisonCurrenciesAfterSelectingComparisonCurrency = [
        { id: '1', code: 'EUR', description: 'Euros' },
        { id: '4', code: 'USD', description: 'US Dollars' },
        { id: '5', code: 'HUF', description: 'Forints' }
    ];
    const candidateComparisonCurrenciesAfterSelectingCandidateComparisonCurrency = [
        { id: '1', code: 'EUR', description: 'Euros' },
        { id: '4', code: 'USD', description: 'US Dollars' },
    ];

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [MenuComponent],
            imports: [ReactiveFormsModule, HttpClientTestingModule],
            providers: [
                FormBuilder,
                CurrencyService,
                ExchangeRateService
            ]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(MenuComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
        currencyService = TestBed.get(CurrencyService);
        exchangeRateService = TestBed.get(ExchangeRateService);
        httpMock = TestBed.get(HttpTestingController);
    });

    beforeEach(fakeAsync(() => {
        const allCurrenciesRequest = httpMock.expectOne('http://localhost:8080/currencies/all');
        allCurrenciesRequest.flush(allCurrencies);
        const defaultSubjectCurrencyRequest = httpMock.expectOne('http://localhost:8080/currencies/defaultSubjectCurrency');
        defaultSubjectCurrencyRequest.flush(defaultSubjectCurrency);
        const defaultComparisonCurrenciesRequest = httpMock.expectOne('http://localhost:8080/currencies/defaultComparisonCurrencies');
        defaultComparisonCurrenciesRequest.flush(defaultComparisonCurrencies);
        fixture.detectChanges();
        tick();
        httpMock.verify();
    }));
    
    beforeEach(() => {
        expect(component.subjectCurrency.value).toEqual(euros);
        expect(component.comparisonCurrencies.length).toEqual(3);
        expect(component.comparisonCurrencies.at(0).value).toEqual(hongKongDollars);
        expect(component.comparisonCurrencies.at(1).value).toEqual(poundsSterling);
        expect(component.comparisonCurrencies.at(2).value).toEqual('');
        expect(component.candidateComparisonCurrencies).toEqual(candidateComparisonCurrencies);
    });

    it('should create', () => {
        expect(component).toBeDefined();
    });

    it('should make no changes to form when subject currency set to a existing subject currency', fakeAsync(() => {
        const select: HTMLSelectElement = fixture.debugElement.query(By.css('#subjectCurrency')).nativeElement;
        select.value = select.options[0].value;
        select.dispatchEvent(new Event('change'));
        fixture.detectChanges();
        tick();
        expect(component.subjectCurrency.value).toEqual(euros);
        expect(component.comparisonCurrencies.length).toEqual(3);
        expect(component.comparisonCurrencies.at(0).value).toEqual(hongKongDollars);
        expect(component.comparisonCurrencies.at(1).value).toEqual(poundsSterling);
        expect(component.comparisonCurrencies.at(2).value).toEqual('');
        expect(component.candidateComparisonCurrencies).toEqual(candidateComparisonCurrencies);
    }));

    it('should refresh form when subject currency set to a comparison currency', fakeAsync(() => {
        const select: HTMLSelectElement = fixture.debugElement.query(By.css('#subjectCurrency')).nativeElement;
        select.value = select.options[1].value;
        select.dispatchEvent(new Event('change'));
        fixture.detectChanges();
        tick();
        expect(component.subjectCurrency.value).toEqual(hongKongDollars);
        expect(component.comparisonCurrencies.length).toEqual(2);
        expect(component.comparisonCurrencies.at(0).value).toEqual(poundsSterling);
        expect(component.comparisonCurrencies.at(1).value).toEqual('');
        expect(component.candidateComparisonCurrencies).toEqual(candidateComparisonCurrenciesAfterSelectingComparisonCurrency);
    }));

     it('should refresh form when subject currency set to a candidate comparison currency', fakeAsync(() => {
        const select: HTMLSelectElement = fixture.debugElement.query(By.css('#subjectCurrency')).nativeElement;
        select.value = select.options[4].value;
        select.dispatchEvent(new Event('change'));
        fixture.detectChanges();
        tick();
        expect(component.subjectCurrency.value).toEqual(forints);
        expect(component.comparisonCurrencies.length).toEqual(3);
        expect(component.comparisonCurrencies.at(0).value).toEqual(hongKongDollars);
        expect(component.comparisonCurrencies.at(1).value).toEqual(poundsSterling);
        expect(component.comparisonCurrencies.at(2).value).toEqual('');
        expect(component.candidateComparisonCurrencies).toEqual(candidateComparisonCurrenciesAfterSelectingCandidateComparisonCurrency);
    }));

});
