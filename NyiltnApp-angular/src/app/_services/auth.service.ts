import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../_models/User";
import {H} from "@angular/cdk/keycodes";

const authBaseUrl = 'http://localhost:8080/api/user/auth/';
const baseUrl = 'http://localhost:8080/api/user/';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*'
  })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    let params = new HttpParams();
    params = params.append('username', username);
    params = params.append('password', password);

    return this.http.post(authBaseUrl + 'login', null, { params } );
  }

  register(user: User): Observable<any> {
    const body = JSON.stringify(user);
    return this.http.post(authBaseUrl + 'register', body, httpOptions);
  }

  logout(username: string): Observable<any> {
    return this.http.put(authBaseUrl + 'logout/' + username ,null, httpOptions);
  }

  resetPassword(email: string): Observable<any> {
    return this.http.post(baseUrl + 'resetPassword?email=' + email, null, httpOptions);
  }

  checkToken(token: string): Observable<any> {
    return this.http.get(baseUrl + 'changePassword?token=' + token)
  }

  savePassword(data: any): Observable<any> {
    return this.http.post(baseUrl + 'savePassword', data, httpOptions);
  }
}
