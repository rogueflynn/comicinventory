/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$().ready(function() {
    $("#create_box_form").validate({
        rules: {
           boxName: { required: true }
    },
      
    messages: {
         boxName: {
            required: "<div id=\"create_box_error\"><font color=\"red\" size=\"2\">Please enter a box name</font></div>"
        }
    }
        
    });
       $.validator.addMethod("isnum", function(value) {
                return /^\d+$/.test(value);
        });
        
});


