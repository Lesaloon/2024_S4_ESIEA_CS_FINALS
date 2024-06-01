import { Injectable } from '@angular/core';
import { DifficultyLevel } from '../enum/difficulty-level';
import { TypeOfTutorial } from '../enum/type-of-tutorial';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {

  getTypeOfTutorial(type: TypeOfTutorial): string {
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

  getDifficultyLevel(difficulty: DifficultyLevel): string {
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
  constructor() { }
}
