import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminToolComponent } from './admin-tool.component';

describe('AdminToolComponent', () => {
  let component: AdminToolComponent;
  let fixture: ComponentFixture<AdminToolComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminToolComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminToolComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
