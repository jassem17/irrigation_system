import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  public token:any;
  constructor(){
    this.token=localStorage.getItem('token');
    this.token=JSON.parse(localStorage.getItem('token'))["accessToken"];
    console.log(this.token)

  }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("hellloooooo "+this.token)
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${this.token}`
      }
    });

    return next.handle(req);
  }
}
