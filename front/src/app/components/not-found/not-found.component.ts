import { Component } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-not-found',
  standalone: true,
  imports: [],
  templateUrl: './not-found.component.html',
  styleUrl: './not-found.component.css'
})
export class NotFoundComponent {
  errorcode = 404;

  constructor(private router: Router) {}

  goHome() {
    this.router.navigate(['/']);
  }
}
