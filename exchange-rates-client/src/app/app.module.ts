import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ErrorHandler } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CurrencyService } from './service/currency.service';
import { ExchangeRateService } from './service/exchange-rate.service';
import { CustomErrorHandlerService } from './service/custom-error-handler.service';
import { CurrentBuyExchangeRatesComponent } from './current-buy-exchange-rates/current-buy-exchange-rates.component';
import { CurrentSellExchangeRatesComponent } from './current-sell-exchange-rates/current-sell-exchange-rates.component';
import { HistoryBuyExchangeRatesComponent } from './history-buy-exchange-rates/history-buy-exchange-rates.component';
import { HistorySellExchangeRatesComponent } from './history-sell-exchange-rates/history-sell-exchange-rates.component';
import { MenuComponent } from './menu/menu.component';
import { CustomErrorDisplayComponent } from './custom-error-display/custom-error-display.component';


@NgModule({
    declarations: [
        AppComponent,
        CurrentBuyExchangeRatesComponent,
        CurrentSellExchangeRatesComponent,
        HistoryBuyExchangeRatesComponent,
        HistorySellExchangeRatesComponent,
        MenuComponent,
        CustomErrorDisplayComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        AppRoutingModule,
        HttpClientModule
    ],
    providers: [
        ExchangeRateService,
        CurrencyService,
        {provide: ErrorHandler, useClass: CustomErrorHandlerService}
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
