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
    $("#add_to_box_form").validate({
        rules: {
           box: { valueNotEquals: "default" },
           comics: { required: true }
    },
      
    messages: {
         box: {
            valueNotEquals: "<br><font color=\"red\" size=\"2\">Please select a box</font>"
        },
        comics: {
            required: "<br><font color=\"red\" size=\"2\">Please select a comic</font>"
        }
    }
        
    });
    // add the rule here
    $.validator.addMethod("valueNotEquals", function(value, element, arg){
         return arg != value;
     }, "Value must not equal arg.");
});


