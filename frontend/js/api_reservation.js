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


// cancel a reservation by ID
// id: User's ID
function cancelReservation(id, callback, authError){
  $.ajax('/api/reservation/cancel/'+id,{
    method: "PUT",
    success: callback,
    statusCode: {
      401: authError
    }
  })
}
