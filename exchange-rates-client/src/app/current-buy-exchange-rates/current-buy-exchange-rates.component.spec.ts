import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { CurrentExchangeRates } from '../model/current-exchange-rates';
import { ExchangeRateService } from '../service/exchange-rate.service';
import { CurrencyService } from '../service/currency.service';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { CurrentBuyExchangeRatesComponent } from './current-buy-exchange-rates.component';

describe('CurrentBuyExchangeRatesComponent', () => {
    let component: CurrentBuyExchangeRatesComponent;
    let fixture: ComponentFixture<CurrentBuyExchangeRatesComponent>;

    class MockCurrencyService {

    }

    class MockExchangeRateService {
        public getCurrentBuyingExchangeRates(): Observable<CurrentExchangeRates> {
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
            declarations: [CurrentBuyExchangeRatesComponent],
            providers: [
                CurrentBuyExchangeRatesComponent,
                { provide: CurrencyService, useClass: MockCurrencyService },
                { provide: ExchangeRateService, useClass: MockExchangeRateService }
            ]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(CurrentBuyExchangeRatesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

});
