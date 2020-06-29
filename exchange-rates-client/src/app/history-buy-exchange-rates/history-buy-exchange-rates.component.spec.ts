import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryBuyExchangeRatesComponent } from './history-buy-exchange-rates.component';

describe('HistoryBuyExchangeRatesComponent', () => {
  let component: HistoryBuyExchangeRatesComponent;
  let fixture: ComponentFixture<HistoryBuyExchangeRatesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HistoryBuyExchangeRatesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoryBuyExchangeRatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
