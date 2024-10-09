import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ITSupportComponent } from './itsupport.component';

describe('ITSupportComponent', () => {
  let component: ITSupportComponent;
  let fixture: ComponentFixture<ITSupportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ITSupportComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ITSupportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
