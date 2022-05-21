import { Component, OnInit } from '@angular/core';
import {EventMeta} from "../_models/event-meta";
import {Event} from "../_models/event";
import {EventService} from "../_services/event.service";
import {Router} from "@angular/router";
import {RegistrationService} from "../_services/registration.service";
import {TokenStorageService} from "../_services/token-storage.service";
import {UserService} from "../_services/user.service";
import {MessageService} from "primeng/api";
import {User} from "../_models/User";

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.scss']
})
export class EventListComponent implements OnInit {
  events: EventMeta[] = [];
  isAdmin: boolean = false;
  // dialog for event creation
  eventCreate:boolean = false;

  eventToSave: Event = {} as Event

  // event form handling object
  addEventForm: any = {
    hostUser: '',
    startTime: '',
    endTime: '',
    maxAttendeeNr: 0,
    link: '',
    location: {
      cityName: '',
      latCoord: 0.00,
      lngCoord: 0.00,
      streetName: '',
      streetNumber: ''
    }
  }

  eventMetaCreate: boolean = false;

  addEventMetaForm: any = {
    id: null,
    name: '',
    description: ''
  };

  userRegisteredEvents: number[] = [];

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

  addNewEventMeta() {
    this.router.navigate(['/event-meta-new'])
  }

  handlePlus(eventId: number) {
    // if admin -> go to add new event form
    // if plain user -> go to registration for event
    this.router.navigate(['/registration', eventId])
  }

  getRegisteredBoolean(eventId: number) {
    return this.userRegisteredEvents.includes(eventId)
  }

  closeAddEvent() {
    this.eventCreate = false;
  }

  async prepareData() {
     var resp = await this.userService.getUserByUsername(this.addEventForm.hostUser).subscribe((response: any) => {
        console.log(response)
        if (!response.success) {
          this.messageService.add({severity: 'error', summary: 'Error!', detail: response.message})
        } else {
          this.eventToSave = {
            id: 0,
            hostUser: response.data,
            startTime: this.addEventForm.startTime,
            endTime: this.addEventForm.endTime,
            maxAttendeeNr: this.addEventForm.maxAttendeeNr,
            link: this.addEventForm.link,
            presentators: [],
            location: {
              cityName: this.addEventForm.location.cityName,
              latCoord: this.addEventForm.location.latCoord,
              lngCoord: this.addEventForm.location.lngCoord,
              streetName: this.addEventForm.location.streetName,
              streetNumber: this.addEventForm.location.streetNumber,
            }
          } as Event

          this.eventService.saveEvent(this.eventToSave).subscribe((response:any) => {
              if (response.success) {
                this.messageService.add({severity: 'success', summary: 'Success!', detail: "Event successfully added"})
              } else {
                this.messageService.add({severity: 'error', summary: 'Error!', detail: response.message})
              }
            },
              error => {
                this.messageService.add({severity: 'error', summary: 'Error!', detail: error.message})
              })
        }
      }, error => {
        this.messageService.add({severity: 'error', summary: 'Error!', detail: error.message})
      }
    );
     if (resp) {
       console.log("done")
     }
  }

  async addEventButton() {
    await this.prepareData();
  }


  showAddEventDialog() {
    this.eventCreate = true;
  }

  showAddEventMetaDialog() {
    this.eventMetaCreate = true;
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

  closeAddEventMeta() {
    this.eventMetaCreate = false
  }
}
