import { Component, OnInit } from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {AuthService} from "../../_services/auth.service";
import {TokenStorageService} from "../../_services/token-storage.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  content?: string;
  welcomeText: string = "";
  username!: string;
  started!: string

  constructor(public translate: TranslateService,
              private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.username = this.tokenStorageService.getUsername();

    this.setText();

    this.translate.onLangChange.subscribe(() => {
      this.setText();
    });
  }

  private setText() {
    this.welcomeText = this.translate.instant('Welcome');
    this.started = this.translate.instant('Started')
  }

  isLoggedIn() {
    return this.tokenStorageService.getToken();
  }
}
