import { Component, Input } from '@angular/core';
import { Tutorial } from '../../interfaces/tutorial';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { UtilsService } from '../../services/utils.service';
import { DifficultyLevel } from '../../enum/difficulty-level';
import { TypeOfTutorial } from '../../enum/type-of-tutorial';
@Component({
  selector: 'app-tutorial-list',
  standalone: true,
  imports: [],
  templateUrl: './tutorial-list.component.html',
  styleUrl: './tutorial-list.component.css'
})
export class TutorialListComponent {
  @Input({required: true})
  tutorials: Tutorial[] = [];

  constructor(
    private router: Router,
    private apiservice: ApiService,
    private utils: UtilsService) { }

  getFrenchType(type: TypeOfTutorial): string {
    return this.utils.getTypeOfTutorial(type);
  }

  getFrenchDifficulty(difficulty: DifficultyLevel): string {
    return this.utils.getDifficultyLevel(difficulty);
  }

  goToTutorial(id: number) {
    this.router.navigate(['/tutorial', id]);
  }

  goToEditTutorial(id: number) {
    this.router.navigate(['/edit', id]);
  }
}
