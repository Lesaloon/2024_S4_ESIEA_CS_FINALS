import { Component, Input } from '@angular/core';
import { Tutorial } from '../../interfaces/tutorial';
import { DifficultyLevel } from '../../enum/difficulty-level';
import { TypeOfTutorial } from '../../enum/type-of-tutorial';
import { UtilsService } from '../../services/utils.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tutorial-card',
  standalone: true,
  imports: [],
  templateUrl: './tutorial-card.component.html',
  styleUrl: './tutorial-card.component.css'
})
export class TutorialCardComponent {
  @Input()
  tutorial: Tutorial = {} as Tutorial;

  constructor(private utils: UtilsService,
    private router: Router) { }

  getFrenchType(type: TypeOfTutorial): string {
    return this.utils.getTypeOfTutorial(type);
  }

  getFrenchDifficulty(difficulty: DifficultyLevel): string {
    return this.utils.getDifficultyLevel(difficulty);
  }


  goToTutorial() {
    this.router.navigate(['/tutorial', this.tutorial.id]);
  }

  goToEditTutorial() {
    this.router.navigate(['/edit', this.tutorial.id]);
  }
}
