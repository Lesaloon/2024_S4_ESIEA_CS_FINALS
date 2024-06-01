import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent {
  isNavbarCollapsed = true;

  toggleNavbar() {
    this.isNavbarCollapsed = !this.isNavbarCollapsed;
  }

  constructor(private router: Router) {}

  isActive(route: string) {
    return this.router.url === route;
  }

  goHome() {
    this.router.navigate(['/']);
  }
  goCreate() {
    this.router.navigate(['/create']);
  }
}
