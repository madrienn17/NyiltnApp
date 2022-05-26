import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

import {MessageService} from "primeng/api";
import {EventMeta} from "../../../_models/event-meta";
import {EventService} from "../../../_services/event.service";
import {RegistrationService} from "../../../_services/registration.service";
import {TokenStorageService} from "../../../_services/token-storage.service";
import {UserService} from "../../../_services/user.service";

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.scss']
})
export class EventListComponent implements OnInit {
  events: EventMeta[] = [];
  isAdmin: boolean = false;

  eventMetaCreate: boolean = false;

  addEventMetaForm: any = {
    id: null,
    name: '',
    description: ''
  };

  userRegisteredEvents: number[] = [];
  eventDeleteVisible: boolean = false;

  eventIdToDelete: number = 0;

  constructor(private eventService: EventService,
              private registrationService: RegistrationService,
              private tokenStorageService: TokenStorageService,
              private userService: UserService,
              private router: Router,
              private messageService: MessageService) { }

  ngOnInit(): void {
    this.isAdmin = this.tokenStorageService.getUser().role != undefined ?
      this.tokenStorageService.getUser().role.toUpperCase() === "ADMIN" : false;

    this.eventService.getAll().subscribe((e:any) => {
      console.log(e);
      this.events = e.data;
      console.log(this.events)
    })

    this.registrationService.getEventRegisteredByUsername(this.tokenStorageService.getUser().username).subscribe(
      (resp:any)=> {
        console.log(resp)
        if (resp.success) {
          this.userRegisteredEvents = resp.data;
        } else {
          this.messageService.add({severity: 'error', summary: 'Error!', detail: resp.message})
        }
    }, error => {
        this.messageService.add({severity: 'error', summary: 'Error!', detail: error.message})
      })

  }

  handlePlus(eventId: number) {
    // if admin -> go to add new event form
    // if plain user -> go to registration for event
    this.router.navigate(['/registration', eventId])
  }

  getRegisteredBoolean(eventId: number) {
    return this.userRegisteredEvents.includes(eventId)
  }



  addEventMetaButton() {
    console.log(this.addEventMetaForm)
    this.eventService.saveMeta(this.addEventMetaForm).subscribe((resp:any) => {
      console.log(resp)
      if (resp.success) {
        this.messageService.add({severity: 'success', summary: 'Success!', detail: "Event-meta successfully added"})
        this.closeAddEventMeta()
      }
    }, error => {
      this.messageService.add({severity: 'error', summary: 'Error!', detail: error.message})
    })
  }

  onDeleteEventButton() {
    this.eventService.deleteEvent(this.eventIdToDelete).subscribe((response: any) => {
      if (response.success) {
        this.messageService.add({severity: 'success', summary: 'Success', detail: "Event successfully deleted!"})
        this.closeDeleteEvent();
      } else {
        this.messageService.add({severity: 'error', summary: 'Error', detail: "Event was not deleted!"})
      }
    }, error => {
      this.messageService.add({severity: 'error', summary: 'Error!', detail: error.message})
    });
  }

  showAddEventMetaDialog() {
    this.eventMetaCreate = true;
  }

  closeAddEventMeta() {
    this.eventMetaCreate = false
  }

  closeDeleteEvent() {
    this.eventDeleteVisible = false;
  }

  showDeleteEventDialog(id: number) {
    this.eventIdToDelete = id;
    this.eventDeleteVisible = true;
  }


}
