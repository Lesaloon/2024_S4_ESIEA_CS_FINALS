import { Component, OnInit, Renderer2 } from '@angular/core';
import { QuillModule } from 'ngx-quill';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Tutorial } from '../../interfaces/tutorial';
import { Step } from '../../interfaces/step';
import { Material } from '../../interfaces/material';
import { DifficultyLevel } from '../../enum/difficulty-level';
import { TypeOfTutorial } from '../../enum/type-of-tutorial';
import { ApiService } from '../../services/api.service';
import { UtilsService } from '../../services/utils.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-create-tutorial',
  standalone: true,
  imports: [ReactiveFormsModule, QuillModule],
  templateUrl: './create-tutorial.component.html',
  styleUrl: './create-tutorial.component.css'
})
export class CreateTutorialComponent {
  tutorialForm: FormGroup = this.fb.group({});

  types = Object.values(TypeOfTutorial);
  difficulties = Object.values(DifficultyLevel);

  constructor(private apiService: ApiService,
    private router: Router,
    private utils: UtilsService,
    private fb: FormBuilder,
    private renderer: Renderer2) { }

  // getFrenchType(type: TypeOfTutorial): string {
  //   return this.utils.getTypeOfTutorial(type);
  // }
  // getFrenchDifficulty(difficulty: DifficultyLevel): string {
  //   return this.utils.getDifficultyLevel(difficulty);
  // }

  ngOnInit() {
    this.tutorialForm = this.fb.group({
      title: ['', Validators.required],
      type: ['', Validators.required],
      description: ['', Validators.required],
      tools: ['', Validators.required],
      toolsPhoto: ['', Validators.required],
      materials: this.fb.array([]),
      photo: ['', Validators.required],
      timeToComplete: ['', [Validators.required, Validators.min(0)]],
      cost: ['', [Validators.required, Validators.min(0)]],
      difficultyLevel: ['', Validators.required],
      steps: this.fb.array([])
    });
  }
  get materials() {
    return this.tutorialForm.get('materials') as FormArray;
  }

  get steps() {
    return this.tutorialForm.get('steps') as FormArray;
  }

  addMaterial() {
    this.materials.push(this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      photo: ['', Validators.required],
      photoType: ['', Validators.required],
      quantity: ['', [Validators.required, Validators.min(0)]],
      unit: ['', Validators.required]
    }));
  }
  removeMaterial(index: number) {
    this.materials.removeAt(index);
  }

  addStep() {
    this.steps.push(this.fb.group({
      title: ['', Validators.required],
      sequenceNumber: ['', [Validators.required, Validators.min(0)]],
      description: ['', Validators.required],
      photo: ['', Validators.required],
      photoType: ['', Validators.required]
    }));
  }



  removeStep(index: number) {
    this.steps.removeAt(index);
  }

  moveStepUp(index: number) {
    if (index > 0) {
      const step = this.steps.at(index);
      const previousStep = this.steps.at(index - 1);
      this.steps.setControl(index, previousStep);
      this.steps.setControl(index - 1, step);
      this.tutorialForm.setControl('steps', this.steps);
    }
  }

  moveStepDown(index: number) {
    if (index < this.steps.length - 1) {
      const step = this.steps.at(index);
      const nextStep = this.steps.at(index + 1);
      this.steps.setControl(index, nextStep);
      this.steps.setControl(index + 1, step);
      this.tutorialForm.setControl('steps', this.steps);
    }
    console.log(this.steps);
  }

  trackByIndex(index: number, obj: any): any {
    return index;
  }
  getFrenchType(type: TypeOfTutorial): string {
    return this.utils.getTypeOfTutorial(type);
  }

  getFrenchDifficulty(difficulty: DifficultyLevel): string {
    return this.utils.getDifficultyLevel(difficulty);
  }
  onSubmit() {
    for (let i = 0; i < this.steps.length; i++) {
      this.steps.at(i).patchValue({ sequenceNumber: i+1 });
    }
    this.tutorialForm.setControl('steps', this.steps);

    console.log(this.tutorialForm.value);

    if (this.tutorialForm.valid) {
      console.log(this.tutorialForm.value);
      this.apiService.addTutorial(this.tutorialForm.value).subscribe(
        (response) => {
          const id = response[0].id;
          this.router.navigate(['/tutorial/' + id]);
        },
        (error) => {
          console.error(error);
        }
      );
      return;
    }
    // show the invalid input in red
    for (const key in this.tutorialForm.controls) {
      if (this.tutorialForm.controls[key].invalid) {
        try {
          this.renderer.addClass(document.getElementById(key), 'is-danger');
        } catch (error) {
        }
      }
    }

  }

  onImageChange(event: any, controlName: string) {
    const file = event.target.files[0];
    // make base64 string from file
    const reader = new FileReader();
    reader.onload = () => {
      this.tutorialForm.patchValue({ [controlName]: reader.result });
      console.log(this.tutorialForm.value.photo);
    };
    reader.readAsDataURL(file);
    // set photoType
    this.tutorialForm.patchValue({ [controlName + 'Type']: file.type });
    console.log(this.tutorialForm.value.photoType);

    // show the image
    const img = document.getElementById(controlName + '_img') as HTMLImageElement;
    img.src = URL.createObjectURL(file);

  }

  onStepImageChange(event: any, controlName: string, index: number) {
    const file = event.target.files[0];
    // make base64 string from file
    const reader = new FileReader();
    reader.onload = () => {
      this.steps.at(index).patchValue({ [controlName]: reader.result });
      console.log(this.steps.at(index).value.photo);
    };
    reader.readAsDataURL(file);
    // set photoType
    this.steps.at(index).patchValue({ [controlName + 'Type']: file.type });
    console.log(this.steps.at(index).value.photoType);

    // show the image
    const img = document.getElementById(controlName + '_img_' + index) as HTMLImageElement;
    img.src = URL.createObjectURL(file);
  }
}
