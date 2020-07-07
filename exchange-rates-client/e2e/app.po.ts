import { browser, by, element } from 'protractor';

export class AppPage {

  navigateTo() {
    return browser.get('/');
  }

  getParagraphText() {
    return element(by.css('app-root h2')).getText();
  }

  navigateToMenu(){
      return element(by.linkText('Menu')).click();
  }

  selectSubjectCurrency(){
    element(by.id('subjectCurrency')).click();
  }
}
