import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalController } from '@ionic/angular';
import { FirstPagePage } from 'src/app/first-page/first-page.page';
import { UserInfoPage } from '../pages/tabs/user-info/user-info.page';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.page.html',
  styleUrls: ['./admin.page.scss'],
})
export class AdminPage implements OnInit {

  public token:any
  public user:any;
  public users:any
  private urlAdmin="https://smart-irrigation.me:8443/smart_irrigation-1.0-SNAPSHOT/api/person";
  constructor(private http:HttpClient,private route:ActivatedRoute,public modalCtrl: ModalController,private router:Router) { 
    this.getAllUsers();
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
      this.getAllUsers();
      event.target.complete();
    }, 2000);
  };

  getAllUsers(){
    const headers= new HttpHeaders()
  .set("Authorization", this.token)
  ;
  console.log(`${this.urlAdmin}/all`,{"headers":headers});
    this.http.get(`${this.urlAdmin}/all`).subscribe(
      data=>{
        console.log(data);
        this.users=data;
      },err=>{
        console.log(err);
      }
    )
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

  getUser(username:String){
    const headers= new HttpHeaders()
    .set("Authorization", this.token)
    ;
    console.log(`${this.urlAdmin}/single/${username}`,{"headers":headers});
    let url = "tabs/user-info/"+ username
    this.http.get(`${this.urlAdmin}/single/${username}`,{"headers":headers}).subscribe(
      async data=>{
        console.log(data);
        this.router.navigateByUrl(url);
        const modal = await this.modalCtrl.create({
          component: UserInfoPage,
          animated: true,
          mode: 'ios',
          backdropDismiss: false,
          cssClass: 'forget-modal',
        })
        return await modal.present();
        
      },err=>{
        console.log(err);
      }
    )
  }

  deleteUser(){
    const id= this.route.snapshot.paramMap.get('id');
    this.http.delete(`${this.urlAdmin}/${id}`).subscribe(
      data=>{
        console.log(data);
      },err=>{
        console.log(err);
      }
    )
  }

  async display () {
    const modal = await this.modalCtrl.create({
      component: UserInfoPage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'forget-modal',
    })
    return await modal.present();
  }

}
