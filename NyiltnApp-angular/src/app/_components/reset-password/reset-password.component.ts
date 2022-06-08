import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../_services/auth.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent implements OnInit {

  constructor(public authService: AuthService,
              private activatedRoute: ActivatedRoute) { }

  token: string = '';
  isTokenValid: boolean = false;
  form: any = {password1: '', password2: ''};

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.token = params['token'];
    });

    this.authService.checkToken(this.token).subscribe((resp: any) => {
      if (resp.success) {
        this.isTokenValid = true;
      }
    });
  }

  onSubmit() {
    if (this.form.password1 === this.form.password2) {
      this.authService.savePassword({ "newPassword": this.form.password1, "token": this.token })
        .subscribe((response: any) => {
          console.log(response)
        })
    }
  }
}
