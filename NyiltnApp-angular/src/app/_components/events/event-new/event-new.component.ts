import { Component, OnInit } from '@angular/core';
import {MessageService} from "primeng/api";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../../_services/user.service";
import {EventService} from "../../../_services/event.service";
import {User} from "../../../_models/User";
import {Event} from "../../../_models/event";

@Component({
  selector: 'app-event-new',
  templateUrl: './event-new.component.html',
  styleUrls: ['./event-new.component.scss']
})
export class EventNewComponent implements OnInit {

  eventId: number = 0

  constructor(private router: Router,
              private userService: UserService,
              private eventService: EventService,
              private messageService: MessageService,
              private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      // @ts-ignore
      this.eventId = +params.get('id');
      console.log(this.eventId)
    });
  }

  user: User = {} as User
  // event form handling object
  addEventForm: any = this.restoreForm()

  prepareData() {
    return {
      id: 0,
      hostUser: this.addEventForm.hostUser,
      eventMeta: this.eventId,
      startTime: this.addEventForm.startTime,
      endTime: this.addEventForm.endTime,
      maxAttendeeNr: this.addEventForm.maxAttendeeNr,
      registeredNr: 0,
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

  }

  backToList() {
    this.router.navigate(['/event-list'])
  }

  async addEventButton() {
    this.eventService.saveEvent(this.prepareData()).toPromise()
      .then((resp: any) => console.log(resp))
      .catch((error: any) => {
        this.messageService.add({severity: 'error', summary: 'Error!', detail: error.message})
      });

    this.addEventForm = this.restoreForm();
  }

  private restoreForm() {
    return {
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
  }
}
