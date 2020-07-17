import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CurrentBuyExchangeRatesComponent } from './current-buy-exchange-rates/current-buy-exchange-rates.component';
import { CurrentSellExchangeRatesComponent } from './current-sell-exchange-rates/current-sell-exchange-rates.component';
import { HistoryBuyExchangeRatesComponent } from './history-buy-exchange-rates/history-buy-exchange-rates.component';
import { HistorySellExchangeRatesComponent } from './history-sell-exchange-rates/history-sell-exchange-rates.component';
import { MenuComponent } from './menu/menu.component';
import { CustomErrorHandlerComponent } from './custom-error-handler/custom-error-handler.component';

const routes: Routes = [
    { path: 'buy/current', component: CurrentBuyExchangeRatesComponent },
    { path: 'sell/current', component: CurrentSellExchangeRatesComponent },
    { path: 'buy/history', component: HistoryBuyExchangeRatesComponent },
    { path: 'sell/history', component: HistorySellExchangeRatesComponent },
    { path: 'menu', component: MenuComponent },
    { path: 'error', component: CustomErrorHandlerComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
