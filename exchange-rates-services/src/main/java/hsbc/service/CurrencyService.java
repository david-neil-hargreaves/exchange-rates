package hsbc.service;

import hsbc.model.Currency;
import hsbc.util.exception.InvalidConfigurationException;
import hsbc.util.exception.UnsupportedCurrencyException;
import java.util.List;

/**
 * Provides service methods for currencies.
 *
 */
public interface CurrencyService {

  /**
   * Finds a currency for the given i.d.
   * 
   * @param id The i.d.
   * @return The currency.
   * @throws UnsupportedCurrencyException If no currency exists for the given i.d.
   */
  public Currency findById(Long id) throws UnsupportedCurrencyException;

  /**
   * Finds a list of currencies for the given i.d.s.
   * 
   * @param ids The i.d.s.
   * @return The list of currencies.
   * @throws UnsupportedCurrencyException If no currency for exists for any of the the given i.d.s.
   */
  public List<Currency> findByIds(List<Long> ids) throws UnsupportedCurrencyException;

  /**
   * Finds a currency for the given code.
   * 
   * @param code The code.
   * @return The currency.
   * @throws UnsupportedCurrencyException If no currency exists for the given code.
   */
  public Currency findByCode(String code) throws UnsupportedCurrencyException;

  /**
   * Finds a list of currencies for the given codes.
   * 
   * @param codes The codes.
   * @return The list of currencies.
   * @throws UnsupportedCurrencyException If no currency for exists for any of the the given codes.
   */
  public List<Currency> findByCodes(List<String> codes) throws UnsupportedCurrencyException;

  /**
   * Finds the default subject currency i.e. the one with default subject currency flagged as true.
   * 
   * @return The default subject currency.
   * @throws InvalidConfigurationException If there is no currency flagged as default subject
   *         currency or multiple currencies are flagged as default subject currency.
   */
  public Currency findDefaultSubjectCurrency() throws InvalidConfigurationException;

  /**
   * Finds the default other currencies i.e. currencies with default other currency flagged as true.
   * 
   * @return The default other currencies.
   */
  public List<Currency> findDefaultOtherCurrencies();

  /**
   * Finds all currencies.
   * 
   * @return The currencies.
   */
  public List<Currency> findAll();

}
