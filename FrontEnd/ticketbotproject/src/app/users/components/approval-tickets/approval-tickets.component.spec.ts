import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovalTicketsComponent } from './approval-tickets.component';

describe('ApprovalTicketsComponent', () => {
  let component: ApprovalTicketsComponent;
  let fixture: ComponentFixture<ApprovalTicketsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ApprovalTicketsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ApprovalTicketsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
