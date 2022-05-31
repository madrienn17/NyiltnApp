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

  getById(id: number) {
    return this.http.get(`${baseUrl}/${id}`)
  }

  getAll() {
    return this.http.get(baseUrl)
  }

  saveEvent(event: Event) {
    return this.http.post(baseUrl, event, httpOptions)
  }

  deleteEvent(id: number) {
    return this.http.delete(`${baseUrl}/${id}`)
  }

  updateEvent(id: number, event: Event) {
    return this.http.put(`${baseUrl}/${id}`, event, httpOptions)
  }

  getMetaById(id: number) {
    return this.http.get(baseUrl + "/meta/" + id)
  }

  saveMeta(eventMeta: any) {
    return this.http.post(baseUrl + "/meta", eventMeta, httpOptions)
  }

  deleteMetaById(id: number) {
    return this.http.delete(baseUrl + "/meta/" + id)
  }

  updateMeta(id: number, eventMeta: any) {
    return this.http.put(baseUrl + "/meta/" + id, eventMeta, httpOptions)
  }
}
