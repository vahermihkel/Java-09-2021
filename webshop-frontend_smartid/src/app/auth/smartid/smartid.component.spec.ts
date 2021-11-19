import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SmartidComponent } from './smartid.component';

describe('SmartidComponent', () => {
  let component: SmartidComponent;
  let fixture: ComponentFixture<SmartidComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SmartidComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SmartidComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
