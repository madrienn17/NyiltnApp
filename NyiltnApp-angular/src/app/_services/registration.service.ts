import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Registration} from "../_models/registration";

const baseUrl = 'http://localhost:8080/api/registration';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*'
  })
};

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http: HttpClient) { }

  addRegistration(registration: Registration) {
    const body = JSON.stringify(registration)

    return this.http.post(baseUrl, body, httpOptions)
  }

  getRegistrations() {
    return this.http.get(baseUrl + "/list")
  }
}
