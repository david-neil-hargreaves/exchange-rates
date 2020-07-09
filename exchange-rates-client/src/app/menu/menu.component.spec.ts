import { ComponentFixture, TestBed, async, fakeAsync, tick } from '@angular/core/testing';
import { ChangeDetectionStrategy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ExchangeRateService } from '../service/exchange-rate.service';
import { CurrencyService } from '../service/currency.service';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { By } from "@angular/platform-browser";
import { MenuComponent } from './menu.component';

describe('MenuComponent', () => {
    let component: MenuComponent;
    let fixture: ComponentFixture<MenuComponent>;
    let currencyService: CurrencyService;
    let exchangeRateService: ExchangeRateService;

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
    const comparisonCurrencies = [
        { id: '2', code: 'HKD', description: 'Hong Kong Dollars' },
        { id: '3', code: 'GBP', description: 'Pounds Sterling' }
    ];
    const candidateComparisonCurrencies = [
        { id: '4', code: 'USD', description: 'US Dollars' },
        { id: '5', code: 'HUF', description: 'Forints' }
    ];

    class MockCurrencyService {
        public fetchAllCurrencies() {
            return Observable.of(allCurrencies);
        }

        public fetchDefaultSubjectCurrency() {
            return Observable.of(defaultSubjectCurrency);
        }
    }

    class MockExchangeRateService {

        public getAllCurrencies() {
            return allCurrencies;
        }

        public getSubjectCurrency() {
            return subjectCurrency;
        }

        public getComparisonCurrencies() {
            return comparisonCurrencies;
        }

        public getCandidateComparisonCurrencies() {
            return candidateComparisonCurrencies;
        }

        public setAllCurrencies() {
        }

        public setSubjectCurrency() {
        }

        public setComparisonCurrencies() {
        }

        public setCandidateComparisonCurrencies() {
        }

    }

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [MenuComponent],
            imports: [ReactiveFormsModule],
            providers: [
                FormBuilder,
                { provide: CurrencyService, useClass: MockCurrencyService },
                { provide: ExchangeRateService, useClass: MockExchangeRateService }
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
    });

    it('should create', () => {
        expect(component).toBeDefined();
    });

    it('should select subject currency when selected', fakeAsync(() => {
       expect(component.subjectCurrency.value.code).toEqual('EUR');
       const select: HTMLSelectElement = fixture.debugElement.query(By.css('#subjectCurrency')).nativeElement;
       select.value = select.options[1].value;  
       select.dispatchEvent(new Event('change'));
       fixture.detectChanges();
       tick();
       expect(component.subjectCurrency.value.code).toEqual('HKD');
    }));

    // TODO This is temporary code whilst debugging the unit tests.
    it('should allow input', fakeAsync(() => {
        const hostElement = fixture.nativeElement;
        const nameInput: HTMLInputElement = hostElement.querySelector('input');
        // simulate user entering a new name into the input box
        nameInput.value = 'Dave';
        // dispatch a DOM event so that Angular learns of input value change.
        nameInput.dispatchEvent(new Event('input'));
        // Tell Angular to update the display binding.
        fixture.detectChanges();
        tick();
        expect(component.input.value).toEqual('Dave');
    }));

});
