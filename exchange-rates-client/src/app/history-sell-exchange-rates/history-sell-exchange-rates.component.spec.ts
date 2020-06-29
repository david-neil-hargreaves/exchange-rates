import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HistorySellExchangeRatesComponent } from './history-sell-exchange-rates.component';

describe('HistorySellExchangeRatesComponent', () => {
  let component: HistorySellExchangeRatesComponent;
  let fixture: ComponentFixture<HistorySellExchangeRatesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HistorySellExchangeRatesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HistorySellExchangeRatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
