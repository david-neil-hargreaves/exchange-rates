import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentSellExchangeRatesComponent } from './current-sell-exchange-rates.component';

describe('CurrentSellExchangeRatesComponent', () => {
  let component: CurrentSellExchangeRatesComponent;
  let fixture: ComponentFixture<CurrentSellExchangeRatesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CurrentSellExchangeRatesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentSellExchangeRatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
