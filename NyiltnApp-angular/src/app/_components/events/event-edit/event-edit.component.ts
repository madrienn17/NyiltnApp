import { Component, OnInit } from '@angular/core';
import {EventService} from "../../../_services/event.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-event-edit',
  templateUrl: './event-edit.component.html',
  styleUrls: ['./event-edit.component.scss']
})
export class EventEditComponent implements OnInit {
  currentEvent: any;
  eventId: number = 0;
  eventmeta: any;

  constructor(private eventService: EventService,
              private activatedRoute:ActivatedRoute,
              private router: Router,
              private messageService: MessageService) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      // @ts-ignore
      this.eventId = +params.get('id');
      console.log(this.eventId)
    });

    this.eventService.getById(this.eventId).subscribe((res: any) => {
      if (res.success) {
        this.currentEvent = res.data;
        this.eventService.getMetaById(this.currentEvent.eventMeta).subscribe((result: any) => {
          console.log(result)
          if (result.success) {
            this.eventmeta = result.data;
          }
        })
      }
    })
  }

  updateEventButton() {
    this.eventService.updateEvent(this.eventId, this.currentEvent).subscribe((res: any) => {
      if (res.success) {
        this.currentEvent = res.data;
        this.messageService.add({severity: 'success', summary: 'Success', detail: "Event successfully updated!"})
      }
    },error => {
      this.messageService.add({severity: 'error', summary: 'Error!', detail: error.message})
    });
  }

  backToList() {
    this.router.navigate(['/event-list'])
  }

}
