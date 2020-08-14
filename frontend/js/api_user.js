/*
 * Format of API calls:
 *
 * parameters: [ data ,] callback, (err,) authError
 *
 * data: multiple parameters which are to be sent to the server as data
 * callback: callback function called by jQuery AJAX (can accept data parameter that contains server response)
 * err: standard error handler, optional
 * authError: HTTP 401 callback, should prompt user to login
 *
 * all calls return void
 */

// get the user's details
function getUserDetails(callback, authError){
  $.ajax('/api/user/1',{
    statusCode: {
      401: authError
    },
    success: callback
  })
}


// modify the user's details
// data: object containing updated user firstName, secondName, email, phone
function modifyUserDetails(data, success, authError){
  $.ajax('/api/user/1',{
    method: "PUT",
    data: JSON.stringify({
      "karma":1,
      "firstName":data.firstName,
      "secondName":data.secondName,
      "email":data.email,
      "phone":data.phone,
      "reservations":[],
      "authorities":null,
      "accountNonExpired":true,
      "accountNonLocked":true,
      "credentialsNonExpired":true,}),
    contentType: 'application/json',
    success: success,
    statusCode: {
      401: authError
    }
  })
}

// get a list of all reservations from this user
// id: User's ID
function getUserReservations(id, callback, authError){
  $.ajax('/api/user/'+id+'/reservation',{
    method: "GET",
    success: callback,
    statusCode: {
      401: authError
    }
  })
}
