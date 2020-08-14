// index.html javascript UI controller

// set event handler for the login form
$('#loginForm').submit((event) => {
  event.preventDefault(); // prevent default browser submission
  $('#loginFailAlert').collapse('hide'); // hide the login failure alert if it's open
  let data = $('#loginForm').serialize(); // get login data from form
  // login, and reload the page
  login(data, () => {
    location.reload(true);
  },() => {
    $("#loginForm").trigger('reset'); // if login fails, show alert and reset form
    $('#loginFailAlert').collapse('show');
  })
});

// event handler for tool search
$('#toolSearch').submit((event) => {
  event.preventDefault();
  let name = objectifyForm($('#toolSearch').serializeArray()).name; // get search string
  if(name.trim() !== ""){ // if string isn't empty/whitespace
    let searchString = $('#toolSearch').serialize(); // search for the tool
    searchAvailableTools(searchString, res => {
      $('#tools').empty(); // remove old list from DOM
      fillTools(res); // fill with new tools
    }, authenticateUser);
  }
});

// upon page load, attempt to fill page with all tools
// -- if unauthenticated, authenticate user
getAvailableTools((res) => {
  fillTools(res);

},authenticateUser);

//function triggered upon HTTP 401 response
// triggers login modal
function authenticateUser(){
  console.log("User authentication required");
  $('#loginModal').modal('show');
}

// take a list of tools and fill in the DOM with tool cards
function fillTools(res){
  res.forEach((element, index) => {
    $('#tools').append(toolCard(element.id, element.name, element.price, element.reservations))
  });

  addReservationHandler();

}


// Upon clicking a reservation button, show the list of dates and handle reservation logic
function addReservationHandler(){
  $('[name="openReservation"]').click((event)=> { // event handler on "Reserve" button
    event.preventDefault();
    openButton = $(event.currentTarget);
    openButton.hide(); // hide the reserve button upon being clicked

    var reservationMenu = $(event.currentTarget).parents().eq(2).children('[name="reservationMenu"]');
    var alertPane = $(event.currentTarget).parents().eq(2).find('[name="alertPane"]');
    var id = $(event.currentTarget).next().val();
    var title = reservationMenu.find('[name="selectTitle"]');
    var closeButton = reservationMenu.find('[name="closeReservationMenu"]');
    var reserveButton = reservationMenu.find('[name="confirmReservation"').hide();

    closeButton.click((event) => { // handle click on close button - reset reservation menu
      reservationMenu.collapse('hide');
      reservationMenu.find('[name="dateList"]').empty(); // clear out list of existing reservations
      title.html("Select Start Date");
      openButton.show(); // show reserve button again
    })

    var day = new Date(); // get today's date


    getToolDates(id,(res) => { // get array containing info about availability of tool for next 30 days


      res.forEach((val) => {
        var button = $(reservationDate(day, val)).appendTo(reservationMenu.find('[name="dateList"]')); // add a button for each of the 30 days from above api call
        button.click((event)=>{ // if the date is clicked
          var startDate = $(event.currentTarget).val(); // set startDate of reservation

          title.html("Select End Date"); // now we're looking for the end date of reservation

          button.prevAll().hide(); // hide all previous days - prevent a reservation ending before it starts
          var disabled = false;
          button.nextAll().each((i,el) => { // show all days until the first unavailable (grayed out) one
            if($(el).prop('disabled')){     // -- prevent reservation dates from including times where tool is already reserved by someone else
              disabled = true;
            }

            if(!disabled){
              $(el).off().click((event) => {
                $(event.currentTarget).addClass("active").prevAll().addClass("active"); // turn selected days purple
                var endDate = $(event.currentTarget).val(); // set endDate of reesrvation

                reserveButton.show().click((event) => { // if the confirm reservation button is clicked...
                  reserveTool(id, startDate, endDate,()=>{  // send api call to reserve the date
                    alertPane.append(reservationSuccessfulAlert()); // if all goes well, show alert confirming reservation
                    closeButton.click();
                  },() => {
                    alertPane.append(reservationFailedAlert()); // otherwise show error alert
                    closeButton.click();
                  }, authenticateUser);
                  reserveButton.off(); // stop user from
                });
              })
            }else{
              $(el).hide();
            }
          })

        });



        day.setDate(day.getDate() + 1);
      })

    },authenticateUser);

    reservationMenu.collapse('show');

  });
}

