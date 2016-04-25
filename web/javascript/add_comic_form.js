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
    $("#createForm").validate({
        rules: {
           comicname: { required: true },
           issuenumber: { required: true },
           publisher: { required: true },
           printing: { required: true,
                       isnum: true},
           year: { required: true,
                   isyear: true}
    },
      
    messages: {
         comicname: {
            required: "<br><font color=\"red\" size=\"2\">Please enter the username field</font>"
        },
        issuenumber: {
            required: "<br><font color=\"red\" size=\"2\">Please enter the issue number field</font>"
        },
        publisher: {
            required: "<br><font color=\"red\" size=\"2\">Please enter the publisher field</font>"
        },
        printing: {
           required: "<br><font color=\"red\" size=\"2\">Please enter the printing field</font>",
           isnum: "<br><font color=\"red\" size=\"2\">Value must be a number</font>"
        },
        year: {
            required: "<br><br><font color=\"red\" size=\"2\">Please enter the year field</font>",
            isyear: "<br><br><font color=\"red\" size=\"2\">Please enter a valid year in the form YYYY</font>"
        }
    }
        
    });
       $.validator.addMethod("isnum", function(value) {
                return /^\d+$/.test(value);
        });
        $.validator.addMethod("isyear", function(value) {
                return /^\d{4}$/.test(value);
        });
        
});







