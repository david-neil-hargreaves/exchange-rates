import { ComponentFixture, TestBed, async, fakeAsync, tick } from '@angular/core/testing';
import { ExchangeRateService } from '../service/exchange-rate.service';
import { CurrencyService } from '../service/currency.service';
import { HistorySellExchangeRatesComponent } from './history-sell-exchange-rates.component';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ReactiveFormsModule } from '@angular/forms';

describe('HistorySellExchangeRatesComponent', () => {

    let component: HistorySellExchangeRatesComponent;
    let fixture: ComponentFixture<HistorySellExchangeRatesComponent>;
    let httpMock: HttpTestingController;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [HistorySellExchangeRatesComponent],
            imports: [ReactiveFormsModule, HttpClientTestingModule],
            providers: [
                HistorySellExchangeRatesComponent,
                CurrencyService,
                ExchangeRateService
            ]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(HistorySellExchangeRatesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
        httpMock = TestBed.get(HttpTestingController);
    });

    it('should create', () => {
        expect(component).toBeDefined();
    });

});
