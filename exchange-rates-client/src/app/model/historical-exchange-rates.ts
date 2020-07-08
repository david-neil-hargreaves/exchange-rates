import { Currency } from '../model/currency';
import { ExchangeRates } from '../model/exchange-rates';
import { Period } from '../model/period';

export class HistoricalExchangeRates {
    subjectCurrency: Currency;
    periods: Period[];
    historicalExchangeRates: ExchangeRates[];
}
