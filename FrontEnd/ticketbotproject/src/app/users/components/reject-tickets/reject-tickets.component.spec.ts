import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RejectTicketsComponent } from './reject-tickets.component';

describe('RejectTicketsComponent', () => {
  let component: RejectTicketsComponent;
  let fixture: ComponentFixture<RejectTicketsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RejectTicketsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RejectTicketsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
