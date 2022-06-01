import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Registration} from "../_models/registration";
import {User} from "../_models/User";

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

  getRegistrations(eventId: number) {
    return this.http.get(`${baseUrl}/${eventId}`)
  }

  getSchoolNames() {
    return this.http.get(baseUrl + "/schools")
  }

  isLoggedInUserRegisteredToEvent(eventId: number) {
    let user: User = JSON.parse(window.sessionStorage.getItem('auth-user') as string);
    return this.http.get(`${baseUrl}/isUserRegistered/${eventId}/${user.username}`)
  }

  getEventRegisteredByUsername(username: string) {
    return this.http.get(baseUrl + `/registered/${username}`)
  }

  removeRegistration(id: number, username: string) {
    return this.http.delete(baseUrl + "/" + id + "/" + username);
  }
}
