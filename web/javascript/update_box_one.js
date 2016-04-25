/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$().ready(function() {
    $("#update_box_form_one").validate({
        rules: {
           boxName: { required: true }
    },
      
    messages: {
         boxName: {
            required: "<div id=\"update_box_error\"><font color=\"red\" size=\"2\">Please enter a value</font></div>"
        }
    }
        
    });
       $.validator.addMethod("isnum", function(value) {
                return /^\d+$/.test(value);
        });
        
});


