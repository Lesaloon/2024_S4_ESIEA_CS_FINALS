import { Injectable } from '@angular/core';
import { HttpClientService } from './http-client.service';
import { Tutorial } from '../interfaces/tutorial';
import { ApiResponse } from '../api-response';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private HttpClientService: HttpClientService) { }

  getAllTutorials() : Observable<Tutorial[]> {
    return this.HttpClientService.get<Tutorial>('tutorials/all')
  }

  getTutorial(id: number) : Observable<Tutorial[]> {
    // there is only one element in the array, its an array only for the sake of the interface
    return this.HttpClientService.get<Tutorial>('tutorials/' + id);
  }

  addTutorial(tutorial: Tutorial) {
    return this.HttpClientService.post('tutorials/add', tutorial);
  }

  updateTutorial(tutorial: Tutorial) {
    const id = tutorial.id;
    return this.HttpClientService.put('tutorials/update/' + id, tutorial);
  }
}
