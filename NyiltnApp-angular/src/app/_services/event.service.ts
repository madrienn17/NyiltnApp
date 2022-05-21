import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Event} from "../_models/event"

const baseUrl = 'http://localhost:8080/api/events';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*'
  })
};

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get(baseUrl + "/getAll")
  }

  saveEvent(event: Event) {
    return this.http.post(baseUrl + '/save', event, httpOptions)
  }

  saveMeta(eventMeta: any) {
    return this.http.post(baseUrl + "/meta/save", eventMeta, httpOptions)
  }
}
