import { async, ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
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
    const routerSpy = jasmine.createSpyObj('Router', ['navigate']);

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

        public setComparisonCurrencies() {
        }

    }

    beforeEach(async(() => {
        const activatedRouteSpy = jasmine.createSpyObj('ActivatedRoute', ['getValue']);

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

    /*it('should successfully submit form', () => {
        component.onSubmit();
        //const submit = fixture.debugElement.nativeElement.querySelector('submit');
        //submit.click();
        const spy = routerSpy.navigate as jasmine.Spy;
        const navArgs = spy.calls.first().args[0];
        expect(navArgs[0]).toEqual('/buy/current',
            'should navigate to /buy/current');
    });

    it('should display error message when submit invalid form', () => {
        let button = fixture.debugElement.nativeElement.querySelector('button');
        button.click();
        button = fixture.debugElement.nativeElement.querySelector('button');
        button.click();
        button = fixture.debugElement.nativeElement.querySelector('button');
        button.click();
        component.onSubmit();
        const spy = routerSpy.navigate as jasmine.Spy;
        const navArgs = spy.calls.first().args[0];
        expect(navArgs[0]).toEqual('/buy/current',
            'should navigate to /buy/current');
    });*/

    it('should', fakeAsync(() => {
        fixture.detectChanges();
        spyOn(component, 'deleteComparisonCurrency'); //method attached to the click.
        // const button = fixture.debugElement.query(By.css('button'));
        const button = fixture.nativeElement.querySelector('button');
        //button.triggerEventHandler('click', null);
        tick(); // simulates the passage of time until all pending asynchronous activities finish
        fixture.detectChanges();
        //expect(component.deleteComparisonCurrency).toHaveBeenCalled();
    }));

});
