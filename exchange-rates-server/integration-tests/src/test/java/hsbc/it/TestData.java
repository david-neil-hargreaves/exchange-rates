package hsbc.it;

import java.util.HashMap;
import java.util.Map;

public class TestData {

  public final static String URL_LANDING_SCREEN = "http://localhost:4200/";
  public final static String URL_BUY_CURRENT_SCREEN = "http://localhost:4200/buy/current";
  public final static String URL_SELL_CURRENT_SCREEN = "http://localhost:4200/sell/current";
  public final static String URL_BUY_HISTORY_SCREEN = "http://localhost:4200/buy/history";
  public final static String URL_SELL_HISTORY_SCREEN = "http://localhost:4200/sell/history";
  public final static String URL_MENU = "http://localhost:4200/menu";
  public final static String LINK_TEXT_BUY_CURRENT = "Buy (Current)";
  public final static String LINK_TEXT_SELL_CURRENT = "Sell (Current)";
  public final static String LINK_TEXT_BUY_HISTORY = "Buy (History)";
  public final static String LINK_TEXT_SELL_HISTORY = "Sell (History)";
  public final static String LINK_TEXT_MENU = "Menu";
  public final static String TAG_HEADING = "H4";
  public final static String TAG_HEADING_TEXT_BUY = "Buy EUR Euros";
  public final static String TAG_HEADING_TEXT_SELL = "Sell EUR Euros";
  public final static String COLUMN_HEADING_CURRENCY_CODE = "Code";
  public final static String COLUMN_HEADING_CURRENCY_DESCRIPTION = "Description";
  public final static String[] PERIODS = new String[] {"December 2019", "January 2020",
      "February 2020", "March 2020", "April 2020", "May 2020"};
  public final static String[] CURRENCY_CODES = new String[] {"GBP", "USD", "HKD"};
  public final static String[] CURRENCY_DESCRIPTIONS =
      new String[] {"Pounds Sterling", "US Dollars", "Hong Kong Dollars"};
  public final static String[] EXCHANGE_RATES_BUY_CURRENT = new String[] {"0.89", "", "8.5"};
  public final static String[] EXCHANGE_RATES_SELL_CURRENT = new String[] {"1.12", "", "0.12"};
  public final static Map<Integer, String[]> EXCHANGE_RATES_BUY_HISTORY = new HashMap<>();
  static {
    EXCHANGE_RATES_BUY_HISTORY.put(1,
        new String[] {"0.86", "0.86", "0.86", "0.86", "0.86", "0.89"});
    EXCHANGE_RATES_BUY_HISTORY.put(2, new String[] {"1.41", "1.41", "1.41", "", "", ""});
    EXCHANGE_RATES_BUY_HISTORY.put(3, new String[] {"8.23", "8.3", "8.3", "8.5", "8.5", "8.5"});
  }
  public final static Map<Integer, String[]> EXCHANGE_RATES_SELL_HISTORY = new HashMap<>();
  static {
    EXCHANGE_RATES_SELL_HISTORY.put(1, new String[] {"", "", "", "", "", "1.12"});
    EXCHANGE_RATES_SELL_HISTORY.put(2, new String[] {"", "", "", "", "", ""});
    EXCHANGE_RATES_SELL_HISTORY.put(3, new String[] {"", "0.12", "0.12", "0.12", "0.12", "0.12"});
  }
  public final static String ATTRIBUTE_URL = "URL";
  public final static String ATTRIBUTE_HEADING = "Heading";
  public final static String ATTRIBUTE_COLUMN_HEADING_CURRENCY_CODE =
      "Column Heading Currency Code";
  public final static String ATTRIBUTE_COLUMN_HEADING_CURRENCY_DESCRIPTION =
      "Column Heading Currency Description";
  public final static String ATTRIBUTE_PERIOD = "Period";
  public final static String ATTRIBUTE_CURRENCY_CODE = "Currency Code";
  public final static String ATTRIBUTE_CURRENCY_DESCRIPTION = "Currency Description";
  public final static String ATTRIBUTE_EXCHANGE_RATE = "Exchange Rate";
  public final static int COLUMN_CURRENCY_CODE = 1;
  public final static int COLUMN_CURRENCY_DESCRIPTION = 2;
  public final static int COLUMN_EXCHANGE_RATE = 3;
}
