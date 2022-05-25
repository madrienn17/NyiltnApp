import { Component, OnInit } from '@angular/core';
import {EventService} from "../../../_services/event.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.scss']
})
export class EventDetailsComponent implements OnInit {
  currentEvent: any;
  eventId: number = 0;
  eventmeta: any;

  constructor(private eventService: EventService,
              private activatedRoute:ActivatedRoute,
              private router: Router) { }

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


  backToList() {
    this.router.navigate(['/event-list'])
  }
}
