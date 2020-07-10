import { TestBed, inject } from '@angular/core/testing';
import { CurrencyService } from './currency.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('CurrencyService', () => {

    const currencies = [{ 'id': '1', 'code': 'EUR', 'description': 'Euros' },
        { 'id': '3', 'code': 'GBP', 'description': 'Pounds Sterling' }];
    const currency = { 'id': '1', 'code': 'EUR', 'description': 'Euros' };

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
        expect(service).toBeDefined();
    }));

    it('should successfully fetch all currencies', (done) => {
        currencyService.fetchAllCurrencies()
            .subscribe(data => {
                expect(data).toBe(currencies);
                done();
            });
        const request = httpMock.expectOne('http://localhost:8080/currencies/all');
        request.flush(currencies);
        httpMock.verify();
    });

    it('should successfully fetch default subject currency', (done) => {
        currencyService.fetchDefaultSubjectCurrency()
            .subscribe(data => {
                expect(data).toBe(currency);
                done();
            });
        const request = httpMock.expectOne('http://localhost:8080/currencies/defaultSubjectCurrency');
        request.flush(currency);
        httpMock.verify();
    });

    it('should successfully fetch default comparison currencies', (done) => {
        currencyService.fetchDefaultComparisonCurrencies()
            .subscribe(data => {
                expect(data).toBe(currencies);
                done();
            });
        const request = httpMock.expectOne('http://localhost:8080/currencies/defaultComparisonCurrencies');
        request.flush(currencies);
        httpMock.verify();
    });

});
