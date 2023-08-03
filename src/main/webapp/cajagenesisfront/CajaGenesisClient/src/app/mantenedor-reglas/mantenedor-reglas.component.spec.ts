import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MantenedorReglasComponent } from './mantenedor-reglas.component';

describe('MantenedorReglasComponent', () => {
  let component: MantenedorReglasComponent;
  let fixture: ComponentFixture<MantenedorReglasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MantenedorReglasComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MantenedorReglasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
