import { TestBed, inject } from '@angular/core/testing';
import { ExchangeRateService } from './exchange-rate.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('ExchangeRateService', () => {

    const currency = { 'id': '1', 'code': 'EUR', 'description': 'Euros' };
    const currencies = [{ 'id': '1', 'code': 'EUR', 'description': 'Euros' },
    { 'id': '3', 'code': 'GBP', 'description': 'Pounds Sterling' }];
    const currenciesExceptSubjectCurrency = [{ 'id': '3', 'code': 'GBP', 'description': 'Pounds Sterling' }];
    const currentExchangeRates = { 'subjectCurrency': { 'id': '1', 'code': 'EUR', 'description': 'Euros' },
                                'currentExchangeRates': []
    };
    const historicalExchangeRates = { 'subjectCurrency': { 'id': '1', 'code': 'EUR', 'description': 'Euros' },
                                'periods': [],
                                'historicalExchangeRates': []
    };
    let exchangeRateService: ExchangeRateService;
    let httpMock: HttpTestingController;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule
            ],
            providers: [
                ExchangeRateService
            ]
        });
        exchangeRateService = TestBed.get(ExchangeRateService);
        httpMock = TestBed.get(HttpTestingController);
    });

    it('should be created', inject([ExchangeRateService], (service: ExchangeRateService) => {
        expect(service).toBeTruthy();
    }));

    it('should successfully get current buying exchange rates', (done) => {
        exchangeRateService.setSubjectCurrency(currency);
        exchangeRateService.getCurrentBuyingExchangeRates()
            .subscribe(data => {
                expect(data).toBe(currentExchangeRates);
                done();
            });
        const request = httpMock.expectOne('http://localhost:8080/exchange-rates/buy/current/1');
        request.flush(currentExchangeRates);
        httpMock.verify();
    });

    it('should successfully get current buying exchange rates (subject currency not specified)', (done) => {
        exchangeRateService.getCurrentBuyingExchangeRates()
            .subscribe(data => {
                expect(data).toBe(currentExchangeRates);
                done();
            });
        const request = httpMock.expectOne('http://localhost:8080/exchange-rates/buy/current');
        request.flush(currentExchangeRates);
        httpMock.verify();
    });

    it('should successfully get current selling exchange rates', (done) => {
        exchangeRateService.setSubjectCurrency(currency);
        exchangeRateService.getCurrentSellingExchangeRates()
            .subscribe(data => {
                expect(data).toBe(currentExchangeRates);
                done();
            });
        const request = httpMock.expectOne('http://localhost:8080/exchange-rates/sell/current/1');
        request.flush(currentExchangeRates);
        httpMock.verify();
    });

    it('should successfully get current selling exchange rates (subject currency not specified)', (done) => {
        exchangeRateService.getCurrentSellingExchangeRates()
            .subscribe(data => {
                expect(data).toBe(currentExchangeRates);
                done();
            });
        const request = httpMock.expectOne('http://localhost:8080/exchange-rates/sell/current');
        request.flush(currentExchangeRates);
        httpMock.verify();
    });

    it('should successfully get historical buying exchange rates', (done) => {
        exchangeRateService.setSubjectCurrency(currency);
        exchangeRateService.getHistoricalBuyingExchangeRates()
            .subscribe(data => {
                expect(data).toBe(historicalExchangeRates);
                done();
            });
        const request = httpMock.expectOne('http://localhost:8080/exchange-rates/buy/history/1');
        request.flush(historicalExchangeRates);
        httpMock.verify();
    });

    it('should successfully get historical buying exchange rates (subject currency not specified)', (done) => {
        exchangeRateService.getHistoricalBuyingExchangeRates()
            .subscribe(data => {
                expect(data).toBe(historicalExchangeRates);
                done();
            });
        const request = httpMock.expectOne('http://localhost:8080/exchange-rates/buy/history');
        request.flush(historicalExchangeRates);
        httpMock.verify();
    });

    it('should successfully get historical selling exchange rates', (done) => {
        exchangeRateService.setSubjectCurrency(currency);
        exchangeRateService.getHistoricalSellingExchangeRates()
            .subscribe(data => {
                expect(data).toBe(historicalExchangeRates);
                done();
            });
        const request = httpMock.expectOne('http://localhost:8080/exchange-rates/sell/history/1');
        request.flush(historicalExchangeRates);
        httpMock.verify();
    });

    it('should successfully get historical selling exchange rates (subject currency not specified)', (done) => {
        exchangeRateService.getHistoricalSellingExchangeRates()
            .subscribe(data => {
                expect(data).toBe(historicalExchangeRates);
                done();
            });
        const request = httpMock.expectOne('http://localhost:8080/exchange-rates/sell/history');
        request.flush(historicalExchangeRates);
        httpMock.verify();
    });

    it('should successfully set all currencies)', (done) => {
        exchangeRateService.setAllCurrencies(currencies);
        expect(exchangeRateService.getAllCurrencies()).toBe(currencies);
        expect(exchangeRateService.getCandidateComparisonCurrencies()).toEqual(currencies);
        done();
    });

    it('should successfully set subject currency)', (done) => {
        exchangeRateService.setAllCurrencies(currencies);
        exchangeRateService.setSubjectCurrency(currency);
        expect(exchangeRateService.getSubjectCurrency()).toBe(currency);
        expect(exchangeRateService.getCandidateComparisonCurrencies()).toEqual(currenciesExceptSubjectCurrency);
        done();
    });

    it('should successfully set comparison currencies)', (done) => {
        exchangeRateService.setComparisonCurrencies(currencies);
        expect(exchangeRateService.getComparisonCurrencies()).toBe(currencies);
        done();
    });

});
