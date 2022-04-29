import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../_models/User";

const baseUrl = 'http://localhost:8080/api/user/auth/';
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

    return this.http.post(baseUrl + 'login', null, { params } );
  }

  register(user: User): Observable<any> {
    const body = JSON.stringify(user);
    return this.http.post(baseUrl + 'register', body, httpOptions);
  }

}
