import { Component, OnInit } from '@angular/core';
import {AuthService} from "../_services/auth.service";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  form: any = {
    firstName: null,
    lastName: null,
    mobileNumber: null,
    username: null,
    email: null,
    password: null
  };

  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService,
              private message: MessageService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const user = this.form
    // explicitly setting the default role as USER
    user.role = "USER"
    this.authService.register(user).subscribe(
      (data:any) => {
        console.log(data);
        if (data.success) {
          this.isSuccessful = true;
          this.isSignUpFailed = false;
        } else {
          this.message.add({severity: 'error', summary: 'Error!', detail: data.message})
        }

      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }
}
