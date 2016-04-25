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
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$().ready(function() {
    $("#upload_photo_form").validate({
        rules: {
           filetoupload: { required: true,
                           isphoto: true}
    },
      
    messages: {
         filetoupload: {
            required: "<br><font color=\"red\" size=\"2\">Comic image is required</font>",
            isphoto: "<br><font color=\"red\" size=\"2\">Valid photo field required</font>"
        }
    }
        
    });
       $.validator.addMethod("isphoto", function(value) {
                return (/\.(gif|jpg|jpeg|tiff|png)$/i).test(value);
        });
        
});






