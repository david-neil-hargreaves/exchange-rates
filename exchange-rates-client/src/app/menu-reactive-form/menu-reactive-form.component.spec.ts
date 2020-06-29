import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuReactiveFormComponent } from './menu-reactive-form.component';

describe('MenuReactiveFormComponent', () => {
  let component: MenuReactiveFormComponent;
  let fixture: ComponentFixture<MenuReactiveFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MenuReactiveFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuReactiveFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
