import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  message = "";

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  onSubmit(signupForm: NgForm) {
    if (signupForm.valid && signupForm.value.password == signupForm.value.password2) {
      this.authService.signup(signupForm.value).subscribe(
        () => { },
        errorResponse => {
          signupForm.reset();
          this.showMessage(errorResponse.error.message);
        },
      );
    } else if (signupForm.value.password != signupForm.value.password2) {
      this.showMessage("Paroolid ei kattu omavahel");
    } else if (signupForm.invalid) {
      this.showMessage("Palun täida kõik kohustuslikud väljad");
    }
  }

  showMessage(message: string) {
    this.message = message;
    setTimeout(() => this.message = "", 5000)
  }

}
