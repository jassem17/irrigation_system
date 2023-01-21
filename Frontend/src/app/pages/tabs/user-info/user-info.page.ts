import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalController } from '@ionic/angular';
import { FirstPagePage } from 'src/app/first-page/first-page.page';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.page.html',
  styleUrls: ['./user-info.page.scss'],
})

export class UserInfoPage implements OnInit {

  userupdate = {
    "username":"",
    "email":"",
    "password":"",
    "userId":0,
    "roles":[]
  }
  public token:any;
  public details:any
  public user:any;
  private urlUser="https://smart-irrigation.me:8443/smart_irrigation-1.0-SNAPSHOT/api/person"
  constructor(private http:HttpClient,private route:ActivatedRoute,private router:Router,public modalCtrl: ModalController) {
    const username=this.route.snapshot.paramMap.get('username');
    this.getUserInfo(username);
   }

  ngOnInit() {
    this.details=localStorage.getItem('currentUser');
    this.details=JSON.parse(localStorage.getItem('currentUser'))["username"];
    this.token=localStorage.getItem('token');
    this.token=JSON.parse(localStorage.getItem('token'))["accessToken"];
    console.log(this.token)
  }
  handleRefresh(event) {
    setTimeout(() => {
      // Any calls to load data go here
      window.location.reload();
      this.getUserInfo(this.user.username);
      event.target.complete();
    }, 2000);
  };

  getUserInfo(username:String){
    this.http.get(`${this.urlUser}/single/${username}`).subscribe(
      data=>{
        console.log("say hello",data);
        this.user=data
        console.log("user   :  ",this.user)
        
      },err=>{
        console.log(err);
      }
    )
  }

  deleteUser(id:String){
    console.log(`${this.urlUser}/${id}`)
    this.http.delete(`${this.urlUser}/${id}`).subscribe(
      data=>{
        console.log("delete successful");
      },err=>{
        console.log(err);
      }
    )
  }

  addRole(id:String){
    let body:any
    if(this.user.roles[0]=='USER' && this.user.roles.length<2 ){
      this.user.roles.push("ADMIN");
      console.log(this.user.roles);
    }
    if(this.user.roles[0]=='ADMIN' && this.user.roles.length<2){
      this.user.roles.push("USER");
      console.log(this.user.roles);
    }
    body=[id,this.user.roles];
    let roles ={
      "roles": []
    }
    /*let roles = {
      "roles": body[1]
    }*/
    roles.roles = this.user.roles
    
    console.log(roles.roles);
    this.userupdate=this.user;
    this.userupdate.roles=roles.roles;
    this.http.put(`${this.urlUser}/role/${id}`,roles).subscribe(
      data=>{
        console.log("sent",data);

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

}
