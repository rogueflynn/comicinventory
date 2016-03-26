/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$().ready(function() {
    $("#createForm").validate({
        rules: {
        orgName: {
            required: true,
            minlength: 5
        },
        username: {
            required: true,
            minlength: 5
        },
        pass: {
            required: true,
            minlength: 8,
            pwcheck: true
        },
        confirm_pass: {
            required: true,
            minlength: 8,
            equalTo: "#pass"
        },
        email: {
            required: true,
            email: true
        }
    },
    messages: {
        orgName: {
            required: "<font color=\"red\">Please provide your organizations name</font>",
            minlength: "<font color=\"red\">The organization name must be at least 5 characters</font>"
        },
        username: {
            required: "<font color=\"red\">Please provide a username</font>",
            minlength: "<font color=\"red\">The username must be at least 5 characters</font>"
        },
        pass: {
            required: "<font color=\"red\">Please provide a password</font>",
            minlength: "<font color=\"red\">Please enter at least 8 characters for your password</font>",
            pwcheck: "<font color=\"red\">Password must contain one number and one lowercase letter.<br />  \n\
                      <div id=\"allowed\">Allowed characters: (A-Z a-z 0-9 @ * _ - . !)</div></font>"
        },
        confirm_pass: {
            required: "<font color=\"red\">Please provide a password</font>",
            minlength: "<font color=\"red\">Please enter at least a 8 characters for your password</font>",
            equalTo: "<font color=\"red\">Please enter the same password as above</font>"
        },
        email: {
            required: "<font color=\"red\">Please enter your email address</font>",
            email: "<font color=\"red\">Please enter a valid email</font>"
        }
    }
        
    });
       $.validator.addMethod("pwcheck", function(value) {
                return /^[A-Za-z0-9\d=!\-@._*]*$/.test(value) // consists of only these
                && /[a-z]/.test(value) // has a lowercase letter
                && /\d/.test(value); // has a digit
        });
});

