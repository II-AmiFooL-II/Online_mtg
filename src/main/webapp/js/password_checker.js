function checkPass()
{
    var pass = document.getElementById('password');
    var re_pass = document.getElementById('re_password');
    var message = document.getElementById('confirmMessage');
    var green = "#0CCA20";
    var red = "#CF300E";
    if(pass.value == re_pass.value){
        re_pass.style.backgroundColor = green;
        message.style.color = green;
        message.innerHTML = "Passwords are matching"
    }else{
        re_pass.style.backgroundColor = red;
        message.style.color = red;
        message.innerHTML = "Passwords are not matching"
    }
} 