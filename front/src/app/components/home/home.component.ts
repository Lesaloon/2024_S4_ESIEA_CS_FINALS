import { Component } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { OnInit } from '@angular/core';
import { Tutorial } from '../../interfaces/tutorial';
import { DifficultyLevel } from '../../enum/difficulty-level';
import { Router } from '@angular/router';
import { QuillModule } from 'ngx-quill';
import { TutorialCardComponent } from '../../shared/tutorial-card/tutorial-card.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [QuillModule, TutorialCardComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  data: Tutorial[] = [];

  constructor(private ApiService: ApiService, private router: Router) { }
  ngOnInit(): void {
    this.ApiService.getAllTutorials().subscribe((data: Tutorial[]) => {
      this.data = data;
    })
  }

  goToTutorial(id: number) {
    this.router.navigate(['/tutorial', id]);
  }

  goToEditTutorial(id: number) {
    this.router.navigate(['/edit', id]);
  }
}
