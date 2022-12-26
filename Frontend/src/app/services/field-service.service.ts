import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FieldServiceService {


  
  private url = "http://localhost:8080/smart_irrigation-1.0-SNAPSHOT/api/field/";

  constructor() { }

  getFields(){
    
    
  }


}
