import { ComponentFixture, TestBed, async, fakeAsync, tick } from '@angular/core/testing';
import { ExchangeRateService } from '../service/exchange-rate.service';
import { CurrencyService } from '../service/currency.service';
import { HistoryBuyExchangeRatesComponent } from './history-buy-exchange-rates.component';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ReactiveFormsModule } from '@angular/forms';

describe('HistoryBuyExchangeRatesComponent', () => {

    let component: HistoryBuyExchangeRatesComponent;
    let fixture: ComponentFixture<HistoryBuyExchangeRatesComponent>;
    let httpMock: HttpTestingController;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [HistoryBuyExchangeRatesComponent],
            imports: [ReactiveFormsModule, HttpClientTestingModule],
            providers: [
                HistoryBuyExchangeRatesComponent,
                CurrencyService,
                ExchangeRateService
            ]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(HistoryBuyExchangeRatesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
        httpMock = TestBed.get(HttpTestingController);
    });

    it('should create', () => {
        expect(component).toBeDefined();
    });

});
