# irrigation_system
## Description 
This project consists in building an intelligent irrigation system that allows to follow the humidity level in order to
to keep the plants alive as much as possible, as each plant needs a specific humidity level.
The irrigation system to be built automatically controls the irrigation cycles and prevents
the waste of water by cutting automatically the irrigation in case of rain through an alert.
## Technologies 
* Front end : Ionic , Capacitor 
* Middleware : Jakarta
* Backend : Mosquitto , raspberry pi 
* Database : Mongo DB
[![image](https://www.linkpicture.com/q/Sans-titre_22.png)](https://www.linkpicture.com/view.php?img=LPic63ca721373bfd1355904269)
## Technical Documentation  
###### Server Side
* Download and Install Jakarta 
* Run the server
###### Client Side
* Run the application  
  ionic serve 
 ## Deployment 
 The Jakarta server part is deployed on an Azure virtual machine with an Ubuntu 20.4 OS and accessible via the URL smart-irrigation.me  
 This server is secured with a Wildard SSL certificate associated with an rsa 4096 key, issued by Let's Encrypt and generated 
with the command :   

> sudo certbot certonly --manual -d *.$smart-irrigation.me -d $smart-irrigation.me --agree-tos --manual-public-ip-logging-ok --preferred-challenges dns-01 
--server https://acme-v02.api.letsencrypt.org/directory --register-unsafely-without-email --rsa-key-size 4096
