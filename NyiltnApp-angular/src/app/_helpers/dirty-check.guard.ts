import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanDeactivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import {ComponentCanDeactivate} from "../_models/component-can-deactivate";

@Injectable({
  providedIn: 'root'
})
export class DirtyCheckGuard implements CanActivate, CanDeactivate<ComponentCanDeactivate> {
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return true;
  }

  /**
   * Check routing when the component is dirty can not deactivate
   * this means you can't leave a page with unsaved changes until prompted if you're sure about it
   *
   * @param component
   * @param currentRoute
   * @param currentState
   * @param nextState
   */
  canDeactivate(
    component: ComponentCanDeactivate,
    currentRoute: ActivatedRouteSnapshot,
    currentState: RouterStateSnapshot,
    nextState?: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return component.canDeactivate();
  }

}

