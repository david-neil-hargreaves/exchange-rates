package hsbc.service;

import hsbc.model.Currency;
import hsbc.model.repository.CurrencyRepository;
import hsbc.util.exception.UnsupportedCurrencyCodeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyServiceImpl implements CurrencyService {

  private static final Logger LOGGER = LogManager.getLogger(CurrencyServiceImpl.class);

  @Autowired
  private CurrencyRepository currencyRepository;

  @Override
  public Currency findByCode(String code) throws UnsupportedCurrencyCodeException {
    LOGGER.traceEntry(code);
    Optional<Currency> optionalCurrency = currencyRepository.findByCode(code);
    if (!(optionalCurrency.isPresent())) {
      throw new UnsupportedCurrencyCodeException(code);
    }
    return LOGGER.traceExit(optionalCurrency.get());
  }

  @Override
  public List<Currency> findByCodes(List<String> codes) throws UnsupportedCurrencyCodeException {
    LOGGER.traceEntry();
    List<Currency> currencies = new ArrayList<>();
    for (String code : codes) {
      Optional<Currency> optionalCurrency = currencyRepository.findByCode(code);
      if (!(optionalCurrency.isPresent())) {
        throw new UnsupportedCurrencyCodeException(code);
      }
      currencies.add(optionalCurrency.get());
    }
    return LOGGER.traceExit(currencies);
  }

}
