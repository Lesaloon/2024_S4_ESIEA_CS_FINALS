import { Component } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { OnInit } from '@angular/core';
import { Tutorial } from '../../interfaces/tutorial';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  data: Tutorial[] = [];

  constructor( private ApiService: ApiService) {}
  ngOnInit(): void {
    this.ApiService.getAllTutorials().subscribe((data: Tutorial[]) => {
      this.data = data;
      console.log(data);
    })
  }

  goToTutorial(id: number) {
    console.log(id);
  }
}
