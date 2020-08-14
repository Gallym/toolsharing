
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

function authenticateUser(){
  console.log("User authentication required");
  $('#loginModal').modal('show');
}
