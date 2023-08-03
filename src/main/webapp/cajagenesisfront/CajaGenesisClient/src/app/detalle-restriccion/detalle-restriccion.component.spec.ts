import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalleRestriccionComponent } from './detalle-restriccion.component';

describe('DetalleRestriccionComponent', () => {
  let component: DetalleRestriccionComponent;
  let fixture: ComponentFixture<DetalleRestriccionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetalleRestriccionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetalleRestriccionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
