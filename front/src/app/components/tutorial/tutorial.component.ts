import { Component, OnInit, Signal, WritableSignal, signal } from '@angular/core';
import { Tutorial } from '../../interfaces/tutorial';
import { ApiService } from '../../services/api.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DifficultyLevel } from '../../enum/difficulty-level';
import { TypeOfTutorial } from '../../enum/type-of-tutorial';
import { UtilsService } from '../../services/utils.service';
import { QuillModule } from 'ngx-quill';
import { RouterModule } from '@angular/router';


@Component({
  selector: 'app-tutorial',
  standalone: true,
  imports: [QuillModule, RouterModule],
  templateUrl: './tutorial.component.html',
  styleUrl: './tutorial.component.css'
})
export class TutorialComponent implements OnInit {

  tutorial: Tutorial = {} as Tutorial;

  constructor(private apiService: ApiService,
    private router: Router,
    private route: ActivatedRoute,
    private utils: UtilsService) { }

  ngOnInit(): void {
    this.apiService.getTutorial(this.route.snapshot.params['id']).subscribe((data: Tutorial[]) => {
      this.tutorial = data[0];
    });
  }

  getFrenchType(type: TypeOfTutorial): string {
    return this.utils.getTypeOfTutorial(type);
  }

  getFrenchDifficulty(difficulty: DifficultyLevel): string {
    return this.utils.getDifficultyLevel(difficulty);
  }

  deleteTutorial( id: number) {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce tutoriel ?')) {
      this.apiService.deleteTutorial(id).subscribe(() => {
        this.router.navigate(['/']);
      });
    }
  }
}
