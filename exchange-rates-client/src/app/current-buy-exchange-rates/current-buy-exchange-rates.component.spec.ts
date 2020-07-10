import { ComponentFixture, TestBed, async, fakeAsync, tick } from '@angular/core/testing';
import { ExchangeRateService } from '../service/exchange-rate.service';
import { CurrencyService } from '../service/currency.service';
import { CurrentBuyExchangeRatesComponent } from './current-buy-exchange-rates.component';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ReactiveFormsModule } from '@angular/forms';

describe('CurrentBuyExchangeRatesComponent', () => {

    let component: CurrentBuyExchangeRatesComponent;
    let fixture: ComponentFixture<CurrentBuyExchangeRatesComponent>;
    let httpMock: HttpTestingController;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [CurrentBuyExchangeRatesComponent],
            imports: [ReactiveFormsModule, HttpClientTestingModule],
            providers: [
                CurrentBuyExchangeRatesComponent,
                CurrencyService,
                ExchangeRateService
            ]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(CurrentBuyExchangeRatesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
        httpMock = TestBed.get(HttpTestingController);
    });

    it('should create', () => {
        expect(component).toBeDefined();
    });

});
