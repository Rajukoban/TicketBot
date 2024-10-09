import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SystemadminComponent } from './systemadmin.component';

describe('SystemadminComponent', () => {
  let component: SystemadminComponent;
  let fixture: ComponentFixture<SystemadminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SystemadminComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SystemadminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
