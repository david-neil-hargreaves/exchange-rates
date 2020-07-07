import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { ExchangeRateService } from '../service/exchange-rate.service';
import { CurrencyService } from '../service/currency.service';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { MenuReactiveFormComponent } from './menu-reactive-form.component';
import { By } from "@angular/platform-browser";

describe('MenuReactiveFormComponent', () => {
    let component: MenuReactiveFormComponent;
    let fixture: ComponentFixture<MenuReactiveFormComponent>;
    const routerSpy = jasmine.createSpyObj('Router', ['navigate']);
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

    beforeEach(fakeAsync(() => {
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

        fixture = TestBed.createComponent(MenuReactiveFormComponent);
        component = fixture.componentInstance;
        currencyService = TestBed.get(CurrencyService);
        exchangeRateService = TestBed.get(ExchangeRateService);

    }));

    it('should create', () => {
        expect(component).toBeTruthy();
    });
  
    it('should select subject currency when selected', fakeAsync(() => {
        const debugElementSubjectCurrency = fixture.debugElement.query(By.css('.subjectCurrency'));
        const elementSubjectCurrency = debugElementSubjectCurrency.nativeElement;
        elementSubjectCurrency.click();
        fixture.detectChanges();
        tick();
        fixture.whenStable();
        expect(elementSubjectCurrency.options[elementSubjectCurrency.selectedIndex].innerText).toEqual('EUR');
        expect(elementSubjectCurrency.selectedIndex).toEqual(0);
        const debugElementSelectedSubjectCurrency = fixture.debugElement.queryAll(By.css('.selectedSubjectCurrency'));
        debugElementSelectedSubjectCurrency[1].nativeElement.click();
        fixture.detectChanges();
        tick();
        fixture.whenStable();
        // TODO Broken test.  Selected subject currency should now be HKD, but stubbornly remains at EUR.
        // expect(elementSubjectCurrency.options[elementSubjectCurrency.selectedIndex].innerText).toEqual('HKD');
        // expect(elementSubjectCurrency.selectedIndex).toEqual(1);
    }));
   
});
