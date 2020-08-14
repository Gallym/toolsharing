/*
 * Component functions
 *
 * Components act as templates for HTML.
 *
 * returns: string with HTML for that component, with parameters inserted into the string where appropriate
 */

// Logout button - DEPRECATED/UNUSED
const logoutButton = "<li class=\"nav-item ml-2\">\n" +
  "            <button class=\"btn btn-outline-primary\">Logout</button>\n" +
  "          </li>"

//Alert informing user that the reservation was successful
function reservationSuccessfulAlert(){
  return '<div class="alert alert-success alert-dismissible fade show" role="alert">\n' +
    '                    Reservation created successfully.\n' +
    '                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">\n' +
    '                      <span aria-hidden="true">&times;</span>\n' +
    '                    </button>\n' +
    '                  </div>'
}

// Alert informing user that the reservation failed
function reservationFailedAlert(){
  return '<div class="alert alert-danger alert-dismissible fade show" role="alert">\n' +
    '                    <strong>Alert!</strong> Reservation creation failed.\n' +
    '                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">\n' +
    '                      <span aria-hidden="true">&times;</span>\n' +
    '                    </button>\n' +
    '                  </div>'
}

// A card showing each tool on the homepage
// parameters:
/*
id: ID of tool
name: tool Title
price: price of tool/day
reservations: list of reservations (DEPRECATED)
 */
function toolCard(id, name, price, reservations){

  var available;
  if(true){ // TODO: update this according to reservation list
    available = "Available"
  }else{
    available = "Not Available"
  }


   return '<div id="'+id+'" class="row bg-light shadow rounded text-body my-2 collapse.show">\n' +
     '            <div class="col my-3">\n' +
     '              <div class="row">\n' +
     '                <div class="col">\n' +
     '                  <h2><strong>'+name+'</strong></h2>\n' +
     '                </div>\n' +
     '              </div>\n' +
     '              <div class="row">\n' +
     '                <div class="col">\n' +
     '                  <p>\n' +
     '                    Placeholder description.\n' +
     '                  </p>\n' +
     '                  <p class="text-secondary">\n' +
     '                    '+available+' | Rental price: '+price+'kc/day | ID: '+id+'\n' +
     '                  </p>\n' +
     '                </div>\n' +
     '                <div class="col-2 text-justify pr-5">\n' +
     '                  <img src="img/3dprinter.png" class="img-thumbnail float-right" style="max-height: 128px; max-width: 128px;">\n' +
     '                </div>\n' +
     '              </div>\n' +
     '              <div class="row mt-4">\n' +
     '                <div class="col text-right pr-5">\n' +
     '                  <button name="openReservation" type="button" class="btn btn-lg btn-primary" data-toggle="collapse" data-target="#reserve">Reserve</button>\n' +
     '                  <input type="hidden" name="id" value="'+id+'">\n' +
     '                </div>\n' +
     '              </div >\n' +
     '              <div name="reservationMenu" class="collapse">\n' +
     '                <div class="row mt-4">\n' +
     '                  <div class="col-12">\n' +
     '                    <hr>\n' +
     '                  </div>\n' +
     '                </div>\n' +
     '                <div class="row justify-content-end">\n' +
     '                  <div class="col-8 text-center">\n' +
     '                    <h3 name="selectTitle">Select Start Date</h3>\n' +
     '                  </div>\n' +
     '                  <div class="col-2 text-center">\n' +
     '                    <button name="closeReservationMenu" class="btn btn-outline-primary"><i class="fas fa-times"></i></button>\n' +
     '                  </div>\n' +
     '                </div>\n' +
     '                <div class="row justify-content-center">\n' +
     '                  <div class="col-8 text-center" >\n' +
     '                    <div name="dateList"  class="list-group">\n' +
     '\n' +
     '                    </div>\n' +
     '                  </div>\n' +
     '                </div>\n' +
     '                <div class="row justify-content-center mt-5">\n' +
     '                  <div class="col-3 text-center">\n' +
     '                    <button name="confirmReservation" class="btn btn-primary">Confirm Reservation</button>\n' +
     '                  </div>\n' +
     '                </div>\n' +
     '              </div>\n' +
     '              <div class="row justify-content-center">\n' +
     '                <div name="alertPane" class="col-6">\n' +
     '                  \n' +
     '                </div>\n' +
     '              </div>'+
     '            </div>\n' +
     '          </div>'
}

// Card showing user details
/*
name: User's first name
surname: User's surname
email: User's email
phone: User's telephone
 */
function userDetails(name, surname, email, phone){
  return '<dl class="row">\n' +
    '          <dt class="col-sm-5">Name:</dt>\n' +
    '          <dd class="col-sm-7">'+name+'</dd>\n' +
    '          <dt class="col-sm-5">Surname:</dt>\n' +
    '          <dd class="col-sm-7">'+surname+'</dd>\n' +
    '          <dt class="col-sm-5">Email:</dt>\n' +
    '          <dd class="col-sm-7">'+email+'</dd>\n' +
    '          <dt class="col-sm-5">Phone:</dt>\n' +
    '          <dd class="col-sm-7">'+phone+'</dd>\n' +
    '        </dl>\n'+
    '<div class="row align-content-end">\n' +
    '          <div class="col">\n' +
    '            <button id=\'modifyAccountButton\' class="btn btn-outline-primary" data-toggle="modal" data-target="#modifyUserModal">Modify</button>\n' +
    '          </div>\n' +
    '        </div>';
}

// A button for choosing dates of reservations
/*
date: the date to display on the button
unavailable: whether' this date is available or not
 */
function reservationDate(date, unavailable){
  var disabled = "", bg = "", value = formatDate(date);
  if(unavailable){
    disabled = "disabled";
    bg = "bg-secondary text-light";
  }
  return '<button type="button" value="'+value+'" class="list-group-item list-group-item-action '+bg+'" '+disabled+' >'+date.toDateString()+'</button>\n'
}

// Modal for modifying user details
/*
name: User's first name
surname: User's surname
email: User's email
phone: User's telephone
user_id: User's ID
 */
function modifyUserDetailsModal(name,surname,email,phone,user_id){
  return '\n' +
    '<div class="modal fade" id="modifyUserModal" tabindex="-1" role="dialog" aria-labelledby="modifyUserModalLabel" aria-hidden="true">\n' +
    '  <div class="modal-dialog modal-dialog-centered modal-lg" role="document">\n' +
    '    <div class="modal-content">\n' +
    '      <div class="modal-header">\n' +
    '        <h5 class="modal-title" id="modifyUserModalLabel">Modify User Account Details</h5>\n' +
    '        <button type="button" class="close" data-dismiss="modal" aria-label="Close">\n' +
    '          <span aria-hidden="true">&times;</span>\n' +
    '        </button>\n' +
    '      </div>\n' +
    '      <div class="modal-body">\n' +
    '        <div class="container-fluid">\n' +
    '\n' +
    '          <div class="row">\n' +
    '            <div class="col">\n' +
    '              <form id="userUpdateForm">\n' +
    '\n' +
    '                <div class="form-group">\n' +
    '                  <label for="inputName">Name</label>\n' +
    '                  <input type="text" class="form-control" id="inputName" name="firstName" value="'+name+'">\n' +
    '                </div>\n' +
    '                <div class="form-group">\n' +
    '                  <label for="inputSurname">Surname</label>\n' +
    '                  <input type="text" class="form-control" id="inputSurname" name="secondName" value="'+surname+'">\n' +
    '                </div>\n' +
    '                <div class="form-group">\n' +
    '                  <label for="inputPhone">Telephone:</label>\n' +
    '                  <input type="text" class="form-control" id="inputPhone" name="phone" value="'+phone+'">\n' +
    '                </div>\n' +
    '                <div class="form-group">\n' +
    '                  <label for="inputEmail">Email address</label>\n' +
    '                  <input type="email" class="form-control" id="inputEmail"  name="email" value="'+email+'">\n' +
    '                </div>\n' +
    '                <div class="form-group">\n' +
    '                  <input type="hidden" id="userID" value="'+user_id+'">\n' +
    '                </div>\n' +
    '                <button type="submit" class="btn btn-primary">Save Changes</button>'+
    '              </form>\n' +
    '            </div>\n' +
    '          </div>\n' +
    '\n' +
    '        </div>\n' +
    '      </div>\n' +
    '    </div>\n' +
    '  </div>\n' +
    '</div>';
}

// A small card showing a reservation made by this user, and a button to cancel it
/*
tool: Tool name
from: start date of reservation
to: end date of reservation
id: reservation ID
 */
function reservationStatusCard(tool, from, to, id){
  return '<div class="row bg-light text-dark rounded shadow py-2 my-2 align-items-center">\n' +
    '              <div class="col-10">\n' +
    '                <strong>'+tool+'</strong> - from '+from+' to '+to+'\n' +
    '              </div>\n' +
    '              <div class="col-2 text-right">\n' +
    '                <button name="cancelReservationButton" class="btn btn-outline-primary"><i class="fas fa-trash"></i></button>\n' +
    '              </div>\n' +
    '            </div>'
}
