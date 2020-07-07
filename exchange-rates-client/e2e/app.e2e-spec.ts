import { AppPage } from './app.po';

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

    it('should select subject currency', () => {
        page.selectSubjectCurrency();
    });


});
