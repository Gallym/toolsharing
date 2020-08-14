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


// log the user in, if successful, a cookie is given
// otherwise run the authError callback
// ! important ! - Use URLEncoding, not JSON
// data: username and password
function login(data , success, authError){
  $.ajax('/api/login',{
    method: "POST",
    data: data,
    success: success,
    statusCode: {
      401: authError
    }
  })
}




