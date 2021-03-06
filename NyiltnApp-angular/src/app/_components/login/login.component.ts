import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../_services/auth.service";
import {TokenStorageService} from "../../_services/token-storage.service";
import {MessageService} from "primeng/api";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: any = {
    username: null,
    password: null
  };

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string = '';

  passwordDialog = false;
  email: string = '';

  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private messageService: MessageService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().role;
    }
  }

  onSubmit(): void {
    const { username, password } = this.form;
    this.authService.login(username, password).subscribe(
      data => {
        console.log(data);

        if (data.success) {
          this.tokenStorage.saveToken(data.data.token);
          this.tokenStorage.saveUser(data.data);
          this.isLoginFailed = false;
          this.isLoggedIn = true;
          this.roles = this.tokenStorage.getUser().role;
          this.reloadPage();
        } else {
          this.messageService.add({severity: 'error', summary: 'Error!', detail: data.message})
        }

      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }

  showPasswordMessage() {
    this.passwordDialog = true;
  }

  closePasswordDialog() {
    this.passwordDialog = false;
  }

  onResetPassword() {
    this.authService.resetPassword(this.email).subscribe((data: any) => {
      console.log(data)
      if (data.success) {
        //show message
      }
    });
  }
}
