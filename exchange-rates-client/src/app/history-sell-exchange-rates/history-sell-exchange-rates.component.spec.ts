import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { CurrencyExchangeRates } from '../model/currency-exchange-rates';
import { ExchangeRateService } from '../service/exchange-rate.service';
import { CurrencyService } from '../service/currency.service';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { HistorySellExchangeRatesComponent } from './history-sell-exchange-rates.component';

describe('HistorySellExchangeRatesComponent', () => {
    let component: HistorySellExchangeRatesComponent;
    let fixture: ComponentFixture<HistorySellExchangeRatesComponent>;

    class MockCurrencyService {

    }

    class MockExchangeRateService {
        public getHistoricalSellingExchangeRates(): Observable<CurrencyExchangeRates> {
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
            declarations: [HistorySellExchangeRatesComponent],
            providers: [
                HistorySellExchangeRatesComponent,
                { provide: CurrencyService, useClass: MockCurrencyService },
                { provide: ExchangeRateService, useClass: MockExchangeRateService }
            ]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(HistorySellExchangeRatesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
