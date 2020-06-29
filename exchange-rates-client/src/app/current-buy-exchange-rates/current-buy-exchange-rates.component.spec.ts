import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentBuyExchangeRatesComponent } from './current-buy-exchange-rates.component';

describe('CurrentBuyExchangeRatesComponent', () => {
  let component: CurrentBuyExchangeRatesComponent;
  let fixture: ComponentFixture<CurrentBuyExchangeRatesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CurrentBuyExchangeRatesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentBuyExchangeRatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
