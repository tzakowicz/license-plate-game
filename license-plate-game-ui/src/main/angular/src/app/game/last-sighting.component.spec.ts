import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LastSightingComponent } from './last-sighting.component';

describe('LastSightingComponent', () => {
  let component: LastSightingComponent;
  let fixture: ComponentFixture<LastSightingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LastSightingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LastSightingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
