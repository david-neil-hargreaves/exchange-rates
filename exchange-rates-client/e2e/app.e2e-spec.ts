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

});
