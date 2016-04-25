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
    $("#login_form").validate({
        rules: {
           username: { required: true },
           password: { required: true }
    },
      
    messages: {
         username: {
            required: "<br><font color=\"red\" size=\"2\">Please enter the username field</font>"
        },
        password: {
            required: "<br><font color=\"red\" size=\"2\">Please enter the password field</font>"
        }
    }
        
    });
       $.validator.addMethod("isnum", function(value) {
                return /^\d+$/.test(value);
        });
        
});





