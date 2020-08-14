#Frontend Documentation

This is the readme documentation for the frontend, which serves as a quick reference rather than the full documentation

##Techonologies used

We're using the following libraries/techonology stack on the frontend:

* [HTML5 Boilerplate](https://github.com/h5bp/html5-boilerplate) 
  * jQuery 
  * Modernizr
  * Normalize.css
* [Bootstrap](https://getbootstrap.com/)
  * popper.js
* [Font Awesome Free](https://fontawesome.com/)

All libraries are under the MIT License, aside from Font Awesome which also includes the [CC BY 4.0 and SIL OFL 1.1 licenses](https://fontawesome.com/license/free)

## Directory structure

* / - main files
* /css - CSS files
* /doc - Documentation (this file)
* /js - our JS + statically served libraries

## Files

index.html - reservation main page  
account.html - account details & reservation list  
about.html - about page

### REST controllers

api.js - Login REST controller  
api_reservation.js - Reservation REST controller - maps to ReservationController.java  
api_user.js - User REST controller - maps to UserController.java  
api_tool.js - Tool REST controller - maps to ToolController.java  

####  Format of API calls:
 
  parameters: [ data ,] callback, (err,) authError
 
  data: multiple parameters which are to be sent to the server as data  
  callback: callback function called by jQuery AJAX (can accept data parameter that contains server response)  
  err: standard error handler, optional  
  authError: HTTP 401 callback, should prompt user to login
 
  all calls return void

### UI controllers (presentation layer)

index.js  
account.js  
components.js  
common.js

These files handle all UI, and include many helper functions
