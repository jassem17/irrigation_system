import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ModalController } from '@ionic/angular';
import { FirstPagePage } from 'src/app/first-page/first-page.page';
import { FieldServiceService } from 'src/app/services/field-service.service';
import { AddFieldPage } from '../pages/tabs/add-field/add-field.page';

import { ParcelPage } from '../parcel/parcel.page';


@Component({
  selector: 'app-fields',
  templateUrl: './fields.page.html',
  styleUrls: ['./fields.page.scss'],
})
export class FieldsPage implements OnInit {

  public token:any;
  public user:any;
  public fields : any;
  private urlField = "https://smart-irrigation.me:8443/smart_irrigation-1.0-SNAPSHOT/api/field/";
  private urlParcel = "https://smart-irrigation.me:8443/smart_irrigation-1.0-SNAPSHOT/api/field/parcels";

  constructor(private fieldService:FieldServiceService ,private http:HttpClient,private router:Router,public modalCtrl: ModalController) {
    this.getFieldsList();
   }

  ngOnInit() {
    this.user=localStorage.getItem('currentUser');
    this.user=JSON.parse(localStorage.getItem('currentUser'))["username"];
    this.token=localStorage.getItem('token');
    this.token=JSON.parse(localStorage.getItem('token'))["accessToken"];
    console.log(this.token)
  }
  handleRefresh(event) {
    setTimeout(() => {
      // Any calls to load data go here
      this.getFieldsList();
      event.target.complete();
    }, 5000);
  };

  async logout() {
    localStorage.removeItem('currentUser');
    const modal = await this.modalCtrl.create({
      component: FirstPagePage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'forget-modal',
    })
    return await modal.present();

  }
  getFieldsList(){
    const headers= new HttpHeaders()
  .set("Authorization", this.token)
  ;
  console.log(`${this.urlField}/all`,{"headers":headers});
     this.http.get(this.urlField).subscribe(
      data=>{
        this.fields=data;
        console.log(this.fields)
      
      },e=>{
        console.log(e);
      }
    );
  }
  getParcelById(id:string){
    let url = "tabs/parcel/"+ id
    console.log(url)
    this.http.get(`${this.urlParcel}/${id}`).subscribe(
      async data=>{
       console.log(data);
       this.router.navigate(['/','parcel',id]);
       const modal = await this.modalCtrl.create({
        component: ParcelPage,
        animated: true,
        mode: 'ios',
        backdropDismiss: false,
        cssClass: 'forget-modal',
      })
      return await modal.present();

      },err=>{
       console.log(err);
      });
    
    
}

async addField () {
  const modal = await this.modalCtrl.create({
    component: AddFieldPage,
    animated: true,
    mode: 'ios',
    backdropDismiss: false,
    cssClass: 'forget-modal',
  })
  return await modal.present();
}

}
