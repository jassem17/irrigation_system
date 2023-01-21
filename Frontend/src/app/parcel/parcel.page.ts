import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalController } from '@ionic/angular';
import { FirstPagePage } from 'src/app/first-page/first-page.page';
import { AddParcelPage } from '../pages/tabs/add-parcel/add-parcel.page';
import { HomePage } from '../pages/tabs/home/home.page';

@Component({
  selector: 'app-parcel',
  templateUrl: './parcel.page.html',
  styleUrls: ['./parcel.page.scss'],
})
export class ParcelPage implements OnInit {

  private ids:any
  public user:any;
  public parcels : any;
  private url = "https://smart-irrigation.me:8443/smart_irrigation-1.0-SNAPSHOT/api/parcel/";
  private urlSensor = "https://smart-irrigation.me:8443/smart_irrigation-1.0-SNAPSHOT/api/parcel/sensor";
  private urlParcel = "https://smart-irrigation.me:8443/smart_irrigation-1.0-SNAPSHOT/api/field/parcels";
  private id = this.route.snapshot.paramMap.get('id');


  constructor(private http:HttpClient,private router:Router,public modalCtrl: ModalController,private route: ActivatedRoute) {
    
    this.getParcelById(this.id);


   }

  ngOnInit() {
    
    const id = this.route.snapshot.paramMap.get('id');
    console.log(id)
    this.ids=id

    this.user=localStorage.getItem('currentUser');
    this.user=JSON.parse(localStorage.getItem('currentUser'))["username"];
  }
  handleRefresh(event) {
    setTimeout(() => {
      window.location.reload();
      this.getParcelById(this.ids)
      // Any calls to load data go here
      event.target.complete();
    }, 2000);
  };

  getParcelList(){
    return this.http.get(this.url).subscribe(
      data=>{
        this.parcels=data;
        console.log(this.parcels)
      
      },e=>{
        console.log(e);
      }
    );
  }
  getParcelById(id:string){
    let url = "tabs/parcel/"+ id
    console.log(url)
    this.http.get(`${this.urlParcel}/${id}`).subscribe(
     data=>{
      this.parcels=data;
      console.log(data);
      
      
     },err=>{
      console.log(err);
     });
}

  getSensorById(id:string){
    return this.http.get(`${this.urlSensor}/${id}`).subscribe(
     async data=>{
      console.log(data);
      this.router.navigate(['/','tabs','home']);
      
     },err=>{
      console.log(err);
     });
}
  async addParcels () {
    const modal = await this.modalCtrl.create({
      component: AddParcelPage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'forget-modal',
    } )
    return await modal.present();
}
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
}
