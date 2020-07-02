import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { CurrencyExchangeRates } from '../model/currency-exchange-rates';
import { ExchangeRateService } from '../service/exchange-rate.service';
import { CurrencyService } from '../service/currency.service';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { CurrentSellExchangeRatesComponent } from './current-sell-exchange-rates.component';

describe('CurrentSellExchangeRatesComponent', () => {
    let component: CurrentSellExchangeRatesComponent;
    let fixture: ComponentFixture<CurrentSellExchangeRatesComponent>;

    class MockCurrencyService {

    }

    class MockExchangeRateService {
        public getCurrentSellingExchangeRates(): Observable<CurrencyExchangeRates> {
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
            declarations: [CurrentSellExchangeRatesComponent],
            providers: [
                CurrentSellExchangeRatesComponent,
                { provide: CurrencyService, useClass: MockCurrencyService },
                { provide: ExchangeRateService, useClass: MockExchangeRateService }
            ]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(CurrentSellExchangeRatesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
