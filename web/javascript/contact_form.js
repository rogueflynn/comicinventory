/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$().ready(function() {
    $("#contactForm").validate({
        rules: {
           firstname: { required: true },
           lastname: { required: true },
           email: { required: true,
                    email: true },
           subject: { required: true},
           message: { required: true}
    },
      
    messages: {
        firstname: {
            required: "<font color=\"red\" size=\"2\">Please enter required field</font>"
        },
        lastname: {
            required: "<font color=\"red\" size=\"2\">Please enter required field</font>"
        },
        email: {
            required: "<font color=\"red\" size=\"2\">Please enter required field</font>",
            email: "<font color=\"red\" size=\"2\">Please enter a valid email</font>"
        },
        subject: {
            required: "<font color=\"red\" size=\"2\">Please enter required field</font>"
        },
        message: {
            required: "<font color=\"red\" size=\"2\">Please enter required field</font>"
        }
    }
        
    });
       $.validator.addMethod("isnum", function(value) {
                return /^\d+$/.test(value);
        });
        
});








