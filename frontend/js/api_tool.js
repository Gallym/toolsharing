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


// get a list of all available tools
function getAvailableTools(callback, authError){
  $.ajax('/api/tool',{
    statusCode: {
      401: authError
    },
    success: callback
  })
}


// get an array containing tool availability for the next 30 days
// -- server returns an array of 30 bools that map to the availability of the tool on the date (today+index)
// id: tool's ID
function getToolDates(id, callback, authError) {
  $.ajax('/api/tool/' + id + '/dates', {
    method: "GET",
    success: callback,
    statusCode: {
      401: authError
    }
  })
}

// reserve a tool by ID
// id: tool ID
// dateFrom: start date of reservation
// dateTo: end date of reservation
function reserveTool(id, dateFrom, dateTo, callback, err, auth){
  $.ajax('/api/reservation/',{

    success: callback,
    contentType: 'application/json',
    method: 'POST',
    data: JSON.stringify({
      toolId: Number(id),
      toolName: "",
      userId:1,
      dateOfReservation: dateFrom,
      dateOfReturn: dateTo
    }),
    statusCode: {
      401: auth,
      400: err
    },

  });

  //callback();
}


// search the available tools based on a search string
// searchString: string to search by
function searchAvailableTools(searchString ,callback, authError){
  $.ajax('/api/tool/search?'+searchString,{
    statusCode: {
      401: authError
    },
    success: callback
  })
}
