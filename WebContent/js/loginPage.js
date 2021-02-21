/**
 * 
 */


function showHidePassword() {
  var x = document.getElementById("password");
  if (x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password";
  }
}

function validation(){
	var form = document.getElementById("form");
	var email = document.getElementById('email').value;
	var text = document.getElementById("text");
	var pattern =  /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	
	if(pattern.test(email)){
		form.classList.add("valid");
		form.classList.remove("invalid");
		text.innerHTML = "Your email in Valid";
		text.style.color = "#006400";
		document.getElementById("login_btn").disabled = false;
	} else {
		form.classList.remove("valid");
		form.classList.add("invalid");
		text.innerHTML = "Please enter valid email";
		text.style.color = "#ff0000";
	}
}