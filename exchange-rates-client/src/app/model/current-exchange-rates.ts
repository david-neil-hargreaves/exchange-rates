import { Currency } from '../model/currency';
import { ExchangeRate } from '../model/exchange-rate';

export class CurrentExchangeRates {
    subjectCurrency: Currency;
    currentExchangeRates: ExchangeRate[];
}
