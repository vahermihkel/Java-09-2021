import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { SmartIdService } from 'src/app/services/smart-id.service';

@Component({
  selector: 'app-smartid',
  templateUrl: './smartid.component.html',
  styleUrls: ['./smartid.component.css']
})
export class SmartidComponent implements OnInit {

  @ViewChild("smartIdForm") submittedForm!: NgForm;

  personalCode!: number;
  sub1!: Subscription;
  sub2!: Subscription;
  verificationCode!: string;

  constructor(
    private authenticateService: SmartIdService
  ) {}

  ngOnInit(): void {
  }

  onLoginClick(): void {
    this.personalCode = this.submittedForm.form.value.personalCode;

    if (this.personalCode) {
      console.log("starting smart id auth");
      this.authenticateService.startSmartIdAuthentication(
        this.personalCode.toString()
      );
      this.sub1 = this.authenticateService
        .getSmartIdUser()
        .subscribe((smartIdUser: any) => {
          console.log("smartiduser:", smartIdUser);
          this.verificationCode = smartIdUser.verificationCode;
          this.sub1.unsubscribe();
          if (this.verificationCode) {
            this.authenticateService.authenticateSmartIdAndLogin(smartIdUser);
          }
        });
    }
  }

}
