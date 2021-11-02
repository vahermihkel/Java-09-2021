import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  message = "";

  constructor(private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(loginForm: NgForm) {
    if (loginForm.valid) {
      this.authService.getUserByPersonCode(loginForm.value.personCode).subscribe(
        person => {
          if (person.password == loginForm.value.password) {
            sessionStorage.setItem("user", loginForm.value.personCode);
            this.authService.isLoggedInChanged.next(true);
            this.router.navigateByUrl("/");
          } else {
            this.showMessage("Parool ei ole korrektne");
          }
        },
        errorResponse => {
          loginForm.reset();
          this.showMessage(errorResponse.error.message);
        },
      );
    } else {
      this.showMessage("Palun täida kõik kohustuslikud väljad");
    }
  }

  showMessage(message: string) {
    this.message = message;
    setTimeout(() => this.message = "", 5000)
  }

}
