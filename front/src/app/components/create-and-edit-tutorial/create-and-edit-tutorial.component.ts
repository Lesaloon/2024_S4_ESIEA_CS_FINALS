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
  selector: 'app-create-and-edit-tutorial',
  standalone: true,
  imports: [ReactiveFormsModule, QuillModule],
  templateUrl: './create-and-edit-tutorial.component.html',
  styleUrl: './create-and-edit-tutorial.component.css'
})
export class CreateAndEditTutorialComponent {

  // variables
  isLoaded = false;
  createMode = true;

  tutorialForm: FormGroup = this.fb.group({});
  types = Object.values(TypeOfTutorial);
  difficulties = Object.values(DifficultyLevel);

  // injection de dépendances
  constructor(private apiService: ApiService,
    private router: Router,
    private utils: UtilsService,
    private fb: FormBuilder,
    private renderer: Renderer2) { }

  /**
   * a l'initialisation du composant
   * si on est en mode création, on crée un formulaire vide
   * sinon on récupère le tutoriel à modifier
   * @returns void
   */
  ngOnInit() {
    this.router.url.includes('edit') ? this.createMode = false : this.createMode = true;
    // si on est en mode création
    if (this.createMode) {
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
      this.isLoaded = true;
    } else {
      const id = this.router.url.split('/')[2];
      if (id === undefined || id === '' || id === null || isNaN(Number(id))) {
        console.log('id is not a number or is undefined');
        this.router.navigate(['/create']);
        return;
      }

      const tutorialId = Number(id);
      this.apiService.getTutorial(tutorialId).subscribe(
        {
          // si on a bien récupéré le tutoriel
          next: (tutorials: Tutorial[]) => {
            const tutorial = tutorials[0];
            if (tutorial === undefined || tutorial === null) {
              this.router.navigate(['/create']);
              return;
            }
            console.log(tutorial);
            this.tutorialForm = this.fb.group({
              title: [tutorial.title, Validators.required],
              type: [tutorial.type, Validators.required],
              description: [tutorial.description, Validators.required],
              tools: [tutorial.tools, Validators.required],
              toolsPhoto: [tutorial.toolsPhoto, Validators.required],
              materials: this.fb.array([]),
              photo: [tutorial.photo, Validators.required],
              timeToComplete: [tutorial.timeToComplete, [Validators.required, Validators.min(0)]],
              cost: [tutorial.cost, [Validators.required, Validators.min(0)]],
              difficultyLevel: [tutorial.difficultyLevel, Validators.required],
              steps: this.fb.array([])
            });

            // add materials
            console.log(tutorial.materials);
            tutorial.materials.forEach((material: Material) => {
              this.materials.push(this.fb.group({
                name: [material.name, Validators.required],
                description: [material.description, Validators.required],
                photo: [material.photo, Validators.required],
                quantity: [material.quantity, [Validators.required, Validators.min(0)]],
                unit: [material.unit, Validators.required]
              }));
            });

            // add steps
            console.log(tutorial.steps);
            tutorial.steps.forEach((step: Step) => {
              this.steps.push(this.fb.group({
                title: [step.title, Validators.required],
                sequenceNumber: [step.sequenceNumber, [Validators.required, Validators.min(0)]],
                description: [step.description, Validators.required],
                photo: [step.photo, Validators.required]
              }));
            });
          },
          error: (error) => {
            console.error(error);
            this.router.navigate(['/create']);
          },
          complete: () => {
            this.isLoaded = true;
          }
        }
      );
    }
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
      this.steps.at(i).patchValue({ sequenceNumber: i + 1 });
    }
    this.tutorialForm.setControl('steps', this.steps);
    console.log("submitting form");
    if (this.tutorialForm.valid) {
      if (this.createMode) {
        console.log("create tutorial");
        this.apiService.addTutorial(this.tutorialForm.value).subscribe({
          next: (response) => {
            const id = response[0].id;
            this.router.navigate(['/tutorial/' + id]);
          },
          error: (error) => {
            console.error(error);
          }
        });
        return;
      } else {
        console.log("update tutorial");
        this.tutorialForm.value.id = this.router.url.split('/')[2];
        this.apiService.updateTutorial(this.tutorialForm.value).subscribe({
          next: (response: unknown[]) => {
            const tutorial = response[0] as Tutorial;
            const id = tutorial.id;
            this.router.navigate(['/tutorial/' + id]);
          },
          error: (error) => {
            console.error(error);
          }
        });
        return;
      }
    } else {
      for (const control in this.tutorialForm.controls) {
        console.log(control.toString() + ' ' + this.tutorialForm.controls[control].status + ' ' + this.tutorialForm.controls[control].value);
        if (this.tutorialForm.controls[control].status === 'INVALID') {
          console.warn(this.tutorialForm.controls[control].value);
        }
        this.tutorialForm.controls[control].markAsTouched();
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
    // show the image
    const img = document.getElementById(controlName + '_img_' + index) as HTMLImageElement;
    img.src = URL.createObjectURL(file);
  }
}
