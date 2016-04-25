/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$().ready(function() {
    $("#create_modal_form").validate({
        rules: {
        username: {
            required: true,
            minlength: 5
        },
        pass: {
            required: true,
            minlength: 8,
            pwcheck: true
        },
        email: {
            required: true,
            email: true
        }
    },
    messages: {
        username: {
            required: "<br><font color=\"red\">Please provide a username</font>",
            minlength: "<br><font color=\"red\">Username must be at least 5 characters</font>"
        },
        pass: {
            required: "<br><font color=\"red\">Please provide a password</font>",
            minlength: "<br><font color=\"red\">Password requires a minimum of<br> 8 characters</font>",
            pwcheck: "<br><font color=\"red\">Password must contain one number and<br> one lowercase letter.<br />  \n\
                      <div id=\"allowed\">Allowed characters: <br>(A-Z a-z 0-9 @ * _ - . !)</div></font>"
        },
        email: {
            required: "<br><font color=\"red\">Please enter your email address</font>",
            email: "<br><font color=\"red\">Please enter a valid email</font>"
        }
    }
        
    });
       $.validator.addMethod("pwcheck", function(value) {
                return /^[A-Za-z0-9\d=!\-@._*]*$/.test(value) // consists of only these
                && /[a-z]/.test(value) // has a lowercase letter
                && /\d/.test(value); // has a digit
        });
});




