import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  isLoggedIn = false;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.isLoggedInChanged.subscribe(loggedInValue => {
        this.isLoggedIn = loggedInValue;
    });
    console.log(this.isLoggedIn);
  }

  onLogout() {
    sessionStorage.removeItem("user");
    this.authService.isLoggedInChanged.next(false);
  }
}
