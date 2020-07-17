import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomErrorHandlerComponent } from './custom-error-handler.component';

describe('CustomErrorHandlerComponent', () => {
  let component: CustomErrorHandlerComponent;
  let fixture: ComponentFixture<CustomErrorHandlerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomErrorHandlerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomErrorHandlerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
