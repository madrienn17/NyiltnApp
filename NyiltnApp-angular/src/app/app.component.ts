import { Component } from '@angular/core';
import {TokenStorageService} from "./_services/token-storage.service";
import {MenuItem} from "primeng/api";
import {TranslateService} from "@ngx-translate/core";
import {AuthService} from "./_services/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: [
    './app.component.scss'
  ]
})
export class AppComponent {
  title = 'NyiltnApp-angular';

  private role: string = '';
  isLoggedIn = false;
  isAdmin = false;
  username?: string;
  languageItems: MenuItem[] = [];

  constructor(private tokenStorageService: TokenStorageService,
              private authService: AuthService,
              public translate: TranslateService) {
    // initializing translation module
    translate.addLangs(['en', 'ro', 'hu']);
    translate.setDefaultLang('hu');
    translate.use('hu');
  }

  useLanguage(language: string) {
    this.translate.use(language);
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    this.isAdmin = this.tokenStorageService.getUser().role != undefined ?
      this.tokenStorageService.getUser().role.toUpperCase() === "ADMIN" : false;

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.role = user.role;
      this.username = user.username;
    }

    this.setMenu()

    this.translate.onLangChange.subscribe(() => {
      this.setMenu();
    });
  }

  setMenu() {
    this.languageItems = [
      {
        label: this.translate.instant('English'),
        command: () => {
          this.useLanguage('en');
        }
      },
      {
        label: this.translate.instant('Hungarian'),
        command: () => {
          this.useLanguage('hu');
        }
      },
      {
        label: this.translate.instant('Romanian'),
        command: () => {
          this.useLanguage('ro');
        }
      }
    ];
  }

  logout(): void {
    this.authService.logout(this.tokenStorageService.getUsername()).toPromise();
    this.tokenStorageService.signOut()
    window.location.reload();
  }
}
