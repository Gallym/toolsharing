// get the user's details
getUserDetails(res => {
  // add the user details card
  $('#userDetailsCard').append(userDetails(res.firstName, res.secondName, res.email, res.phone));
  // add the modify user modal
  $('body').append(modifyUserDetailsModal(res.firstName, res.secondName, res.email, res.phone, res.id));

  // event callback for user data update
  $('#userUpdateForm').submit(event => {
    event.preventDefault();
    let f= objectifyForm($('#userUpdateForm').serializeArray());
    modifyUserDetails(f, () => {
      location.reload(true); // reload upon successful user data update
    }, authenticateUser);
  })
}, authenticateUser); // if user is not logged in, show login modal

getUserReservations(1, (data) => { // get a list of user's reservations
  var resList = $('#reservationListContainer');

  data.forEach((el, i) => { // for each reservation in the list

    var item = $(reservationStatusCard(el.toolName, el.dateOfReservation, el.dateOfReturn)).appendTo(resList); // create a new card with reservation details
    item.find('[name="cancelReservationButton"]').click((event) => { // add a handler to the cancel button, to call the cancelReservation api call
      cancelReservation(el.toolId, () => {
        item.hide();
      },authenticateUser)
    })

  })
}, authenticateUser);
