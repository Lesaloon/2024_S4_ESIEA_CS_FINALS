import { Component, OnInit } from '@angular/core';
import { Tutorial } from '../../interfaces/tutorial';
import { ApiService } from '../../services/api.service';
import { ActivatedRoute } from '@angular/router';
import { DifficultyLevel } from '../../enum/difficulty-level';
import { TypeOfTutorial } from '../../enum/type-of-tutorial';

@Component({
  selector: 'app-tutorial',
  standalone: true,
  imports: [],
  templateUrl: './tutorial.component.html',
  styleUrl: './tutorial.component.css'
})
export class TutorialComponent implements OnInit {

  tutorial: Tutorial = {} as Tutorial;

  constructor(private apiService: ApiService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.apiService.getTutorial(this.route.snapshot.params['id']).subscribe((data: Tutorial[]) => {
      this.tutorial = data[0];
    });
  }

  getFrenchDifficulty(difficulty: DifficultyLevel): string {
    switch (difficulty) {
      case DifficultyLevel.EASY:
        return 'Facile';
      case DifficultyLevel.MEDIUM:
        return 'Moyen';
      case DifficultyLevel.HARD:
        return 'Difficile';
      default:
        return 'Inconnu';
    }
  }

  getFrenchType(type: TypeOfTutorial): string {
    switch (type) {
      case TypeOfTutorial.ARTS_AND_CRAFTS:
        return 'Loisirs créatifs';
      case TypeOfTutorial.COOKING:
        return 'Cuisine';
      case TypeOfTutorial.DIY:
        return 'Bricolage';
      default:
        return 'Inconnu';
    }
  }
}
