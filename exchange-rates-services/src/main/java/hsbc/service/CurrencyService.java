package hsbc.service;

import hsbc.model.Currency;
import hsbc.util.exception.ServiceException;
import hsbc.util.exception.UnsupportedCurrencyException;
import java.util.List;

public interface CurrencyService {
  
  public Currency findById(Long id) throws UnsupportedCurrencyException;

  public List<Currency> findByIds(List<Long> ids) throws UnsupportedCurrencyException;

  public Currency findByCode(String code) throws UnsupportedCurrencyException;

  public List<Currency> findByCodes(List<String> codes) throws UnsupportedCurrencyException;
  
  public Currency findDefaultSubjectCurrency() throws ServiceException;
  
  public List<Currency> findDefaultOtherCurrencies();
  
  public List<Currency> findAll();
  
}
