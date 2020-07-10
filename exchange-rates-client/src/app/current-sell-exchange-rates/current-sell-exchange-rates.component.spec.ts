import { ComponentFixture, TestBed, async, fakeAsync, tick } from '@angular/core/testing';
import { ExchangeRateService } from '../service/exchange-rate.service';
import { CurrencyService } from '../service/currency.service';
import { CurrentSellExchangeRatesComponent } from './current-sell-exchange-rates.component';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ReactiveFormsModule } from '@angular/forms';

describe('CurrentSellExchangeRatesComponent', () => {

    let component: CurrentSellExchangeRatesComponent;
    let fixture: ComponentFixture<CurrentSellExchangeRatesComponent>;
    let httpMock: HttpTestingController;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [CurrentSellExchangeRatesComponent],
            imports: [ReactiveFormsModule, HttpClientTestingModule],
            providers: [
                CurrentSellExchangeRatesComponent,
                CurrencyService,
                ExchangeRateService
            ]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(CurrentSellExchangeRatesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
        httpMock = TestBed.get(HttpTestingController);
    });

    it('should create', () => {
        expect(component).toBeDefined();
    });

});
