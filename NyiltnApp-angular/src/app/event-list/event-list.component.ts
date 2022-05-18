import { Component, OnInit } from '@angular/core';
import {EventMeta} from "../_models/event-meta";
import {EventService} from "../_services/event.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.scss']
})
export class EventListComponent implements OnInit {
  events: EventMeta[] = [];

  constructor(private eventService: EventService, private router: Router) { }

  ngOnInit(): void {
    this.eventService.getAll().subscribe((e:any) => {
      console.log(e);
      this.events = e.data;
      console.log(this.events)
    })
  }

  addNewEventMeta() {

  }

  handlePlus(eventId: number) {
    // if admin -> go to add new event form
    // if plain user -> go to registration for event
    this.router.navigate(['/registration', eventId])
  }
}
