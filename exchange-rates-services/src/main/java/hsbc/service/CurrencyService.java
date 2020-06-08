package hsbc.service;

import hsbc.model.Currency;
import hsbc.util.exception.UnsupportedCurrencyCodeException;
import java.util.List;

public interface CurrencyService {

  public Currency findByCode(String code) throws UnsupportedCurrencyCodeException;

  public List<Currency> findByCodes(List<String> codes) throws UnsupportedCurrencyCodeException;

}
