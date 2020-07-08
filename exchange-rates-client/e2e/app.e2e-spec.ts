import { AppPage } from './app.po';
import { browser } from 'protractor';

describe('exchange-rates-client App', () => {
    let page: AppPage;

    beforeEach(() => {
        page = new AppPage();
    });

    it('should display home screen', () => {
        page.navigateTo();
        expect(page.getParagraphText()).toEqual('Exchange Rates');
    });

    it('should display menu screen', () => {
        page.navigateToMenu();
    });

    it('should click subject currency', () => {
        page.clickSubjectCurrency();
        browser.pause(20000);
    });

    /*it('should select subject currency', () => {
        page.selectSubjectCurrency();
    });*/

    it('should display buy current screen', () => {
        page.navigateToBuyCurrent();
        browser.pause(20000);
    });

});
