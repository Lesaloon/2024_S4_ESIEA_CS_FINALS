import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateAndEditTutorialComponent } from './create-and-edit-tutorial.component';

describe('CreateAndEditTutorialComponent', () => {
  let component: CreateAndEditTutorialComponent;
  let fixture: ComponentFixture<CreateAndEditTutorialComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateAndEditTutorialComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateAndEditTutorialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
