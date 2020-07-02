import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CurrencyExchangeRates } from '../model/currency-exchange-rates';
import { ExchangeRateService } from '../service/exchange-rate.service';
import { CurrencyService } from '../service/currency.service';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { MenuReactiveFormComponent } from './menu-reactive-form.component';

describe('MenuReactiveFormComponent', () => {
    let component: MenuReactiveFormComponent;
    let fixture: ComponentFixture<MenuReactiveFormComponent>;

    class MockCurrencyService {
        public fetchAllCurrencies() {
            const currencies = [
                {
                    id: '1',
                    code: 'EUR',
                    description: 'Euros'
                },
                {
                    id: '2',
                    code: 'GBP',
                    description: 'Pounds sterling'
                },
                {
                    id: '3',
                    code: 'HKD',
                    description: 'Hong Kong dollars'
                }
            ];
            return Observable.of(currencies);
        }

        public fetchDefaultSubjectCurrency() {
            const currency = {
                id: '1',
                code: 'EUR',
                description: 'Euros'
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
            const currency = {
                id: '1',
                code: 'EUR',
                description: 'Euros'
            };
            return Observable.of(currency);
        }

        public getComparisonCurrencies() {
            const currencies = [
                {
                    id: '2',
                    code: 'GBP',
                    description: 'Pounds sterling'
                },
                {
                    id: '3',
                    code: 'HKD',
                    description: 'Hong Kong dollars'
                }
            ];
            return currencies;
        }

        public getCandidateComparisonCurrencies() {
            return null;
        }

        public setAllCurrencies() {
        }

        public setSubjectCurrency() {
        }

    }

    beforeEach(async(() => {

        const activatedRouteSpy = jasmine.createSpyObj('ActivatedRoute', ['getValue']);
        const routerSpy = jasmine.createSpyObj('Router', ['getValue']);

        TestBed.configureTestingModule({
            declarations: [MenuReactiveFormComponent],
            imports: [ReactiveFormsModule],
            providers: [
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
