import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FillingComponent } from './filling.component';

describe('FillingComponent', () => {
  let component: FillingComponent;
  let fixture: ComponentFixture<FillingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FillingComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FillingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
