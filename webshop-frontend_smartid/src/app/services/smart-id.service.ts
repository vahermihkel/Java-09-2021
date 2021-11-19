import { Time } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SmartIdService {

  smartIdUrl: string = "http://localhost:8080/authenticate/smart-id/";

  smartIdLoginUrl: string = "http://localhost:8080//authenticate/smart-id/login/";

  authUserStatus = new Subject<number>();
  smartIdAuthUserStatus = new Subject<number>();
  smartIdUser = new Subject<SmartIdUser>();
  token: string = "";

  constructor(private http: HttpClient,
    private router: Router) {}

  startSmartIdAuthentication(personalCode: string) {
    const userRequest: UserRequest = {
      nationalIdentityNumber: personalCode,
      countryCode: "EE",
    };
    return this.http
      .post<SmartIdUser>(this.smartIdUrl, userRequest, {
        withCredentials: true,
        observe: "response",
      })
      .toPromise()
      .then((response) => {
        if (response.body) {
          console.log("start smart id auth:", response);
          this.smartIdUser.next(response.body);
        }
      })
      .catch((err) => {
        if (err) {
          console.log(err);
        } else {
          console.log("error");
        }
      });
  }

  authenticateSmartIdAndLogin(smartIdUser: SmartIdUser) {
    smartIdUser.countryCode = "EE";
    return this.http
      .post<AppUser>(this.smartIdLoginUrl, smartIdUser, {
        withCredentials: true,
        observe: "response",
      })
      .toPromise()
      .then((response) => {
        if (response.status === 200) 
          console.log("authenticate smart id and login:", response);
        if (response.body) {
          sessionStorage.setItem("id_token", response.body.jwt);
          sessionStorage.setItem("personId", JSON.stringify(response.body.personId));
          sessionStorage.setItem("firstName", response.body.firstName);
          sessionStorage.setItem("lastName", response.body.lastName);
          this.router.navigate(["/admin"]);

        }
      })
      .catch((err) => {
        console.log(err);
      });
  }

  logout() {
    sessionStorage.clear();
    this.router.navigate(["/login"]);
  }

  getSmartIdUser(): Observable<SmartIdUser> {
    return this.smartIdUser.asObservable();
  }
}

export interface UserRequest {
  phoneNumber?: string;
  nationalIdentityNumber: string;
  countryCode?: string;
}

export interface SmartIdUser {
  personalCode: string;
  hashBase64: string;
  verificationCode: string;
  countryCode?: string;
}

export interface AppUser {
  personId: number;
  firstName: string;
  lastName: string;
  jwt: string;
  expiration: Time;
}
