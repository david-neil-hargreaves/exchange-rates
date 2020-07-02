import { TestBed, inject } from '@angular/core/testing';
import { HttpClient } from '@angular/common/http';
import { CurrencyService } from './currency.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Currency } from '../model/currency';
import { Observable } from 'rxjs/Observable';

describe('CurrencyService', () => {

    let currencyService: CurrencyService;
    let httpMock: HttpTestingController;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule
            ],
            providers: [
                CurrencyService
            ]
        });

        currencyService = TestBed.get(CurrencyService);
        httpMock = TestBed.get(HttpTestingController);
    });

    it('should be created', inject([CurrencyService], (service: CurrencyService) => {
        expect(service).toBeTruthy();
    }));

    it('should successfully fetch all currencies', (done) => {
        let currencies: Currency[];
        currencies = [{ 'id': '1', 'code': 'EUR', 'description': 'Euros' }, { 'id': '3', 'code': 'GBP', 'description': 'Pounds Sterling' }];
        currencyService.fetchAllCurrencies()
            .subscribe(data => {
                expect(data).toBe(currencies);
                done();
            });

        const request = httpMock.expectOne('http://localhost:8080/currencies/all');

        request.flush(
            currencies
        );

        httpMock.verify();
    });
});
