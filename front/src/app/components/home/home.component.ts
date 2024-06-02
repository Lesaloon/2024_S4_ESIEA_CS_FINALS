import { Component } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { OnInit } from '@angular/core';
import { Tutorial } from '../../interfaces/tutorial';
import { DifficultyLevel } from '../../enum/difficulty-level';
import { Router } from '@angular/router';
import { QuillModule } from 'ngx-quill';
import { TutorialCardComponent } from '../../shared/tutorial-card/tutorial-card.component';
import { TutorialListComponent } from '../../shared/tutorial-list/tutorial-list.component';
import { FormsModule  } from '@angular/forms';
import { TypeOfTutorial } from '../../enum/type-of-tutorial';
import { UtilsService } from '../../services/utils.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [QuillModule, TutorialCardComponent, TutorialListComponent, FormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  isLoaded: boolean = false;
  data: Tutorial[] = [];
  viewMode: string = 'card';
  searchText: string = '';
  searchCategory: TypeOfTutorial | "" = "";
  ListOfCategories: string[] = Object.values(TypeOfTutorial);


  constructor(private ApiService: ApiService, private router: Router, private util: UtilsService) { }


  ngOnInit(): void {
    this.ApiService.getAllTutorials().subscribe((data: Tutorial[]) => {
      this.data = data;
      this.isLoaded = true;
    })
  }

  goToTutorial(id: number) {
    this.router.navigate(['/tutorial', id]);
  }

  goToEditTutorial(id: number) {
    this.router.navigate(['/edit', id]);
  }

  getTypeOfTutorial(category: string): string {
    return this.util.getTypeOfTutorial(category as TypeOfTutorial);
  }
  get shownData() {
    if (this.searchCategory) {
      return this.data.filter((tutorial) => tutorial.type === this.searchCategory).filter((tutorial) => tutorial.title.toLowerCase().includes(this.searchText.toLowerCase()));
    }
    return this.data.filter((tutorial) => tutorial.title.toLowerCase().includes(this.searchText.toLowerCase()));
  }
}
