package hsbc.service;

import hsbc.model.Currency;
import hsbc.model.repository.CurrencyRepository;
import hsbc.util.exception.InvalidConfigurationException;
import hsbc.util.exception.ServiceException;
import hsbc.util.exception.UnsupportedCurrencyException;
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
  
  public static final String MESSAGE_INVALID_CONFIGURATION_NO_DEFAULT_SUBJECT_CURRENCY = "Invalid system configuration - no default subject currency set up.";
  public static final String MESSAGE_INVALID_CONFIGURATION_MULTIPLE_DEFAULT_SUBJECT_CURRENCIES = "Invalid system configuration - expecting 1 default subject currency but found %s.";
 
  @Autowired
  private CurrencyRepository currencyRepository;
  
  @Override
  public Currency findById(Long id) throws UnsupportedCurrencyException {
    LOGGER.traceEntry();
    Optional<Currency> optionalCurrency = currencyRepository.findById(id);
    if (!(optionalCurrency.isPresent())) {
      throw new UnsupportedCurrencyException(id);
    }
    return LOGGER.traceExit(optionalCurrency.get());
  }

  @Override
  public List<Currency> findByIds(List<Long> ids) throws UnsupportedCurrencyException {
    LOGGER.traceEntry();
    List<Currency> currencies = new ArrayList<>();
    for (Long id : ids) {
      Optional<Currency> optionalCurrency = currencyRepository.findById(id);
      if (!(optionalCurrency.isPresent())) {
        throw new UnsupportedCurrencyException(id);
      }
      currencies.add(optionalCurrency.get());
    }
    return LOGGER.traceExit(currencies);
  }

  @Override
  public Currency findByCode(String code) throws UnsupportedCurrencyException {
    LOGGER.traceEntry(code);
    Optional<Currency> optionalCurrency = currencyRepository.findByCode(code);
    if (!(optionalCurrency.isPresent())) {
      throw new UnsupportedCurrencyException(code);
    }
    return LOGGER.traceExit(optionalCurrency.get());
  }

  @Override
  public List<Currency> findByCodes(List<String> codes) throws UnsupportedCurrencyException {
    LOGGER.traceEntry();
    List<Currency> currencies = new ArrayList<>();
    for (String code : codes) {
      Optional<Currency> optionalCurrency = currencyRepository.findByCode(code);
      if (!(optionalCurrency.isPresent())) {
        throw new UnsupportedCurrencyException(code);
      }
      currencies.add(optionalCurrency.get());
    }
    return LOGGER.traceExit(currencies);
  }

  @Override
  public Currency findDefaultSubjectCurrency() throws InvalidConfigurationException {
    List<Currency> currencies = currencyRepository.findByDefaultSubjectCurrencyTrue();
    if (currencies.size() == 0) {
      throw new InvalidConfigurationException(MESSAGE_INVALID_CONFIGURATION_NO_DEFAULT_SUBJECT_CURRENCY);
    } else if (currencies.size() > 1) {
      String message = String.format(MESSAGE_INVALID_CONFIGURATION_MULTIPLE_DEFAULT_SUBJECT_CURRENCIES, currencies.size());
      throw new InvalidConfigurationException(message);
    }
    return currencies.get(0);
  }

  @Override
  public List<Currency> findDefaultOtherCurrencies() {
    return currencyRepository.findByDefaultOtherCurrencyTrue();
  }
  
  @Override
  public List<Currency> findAll() {
    return currencyRepository.findAll();
  }
 
}
