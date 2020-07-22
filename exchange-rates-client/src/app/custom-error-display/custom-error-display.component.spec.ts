import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomErrorDisplayComponent } from './custom-error-display.component';

describe('CustomErrorDisplayComponent', () => {
  let component: CustomErrorDisplayComponent;
  let fixture: ComponentFixture<CustomErrorDisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomErrorDisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomErrorDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeDefined();
  });
});
