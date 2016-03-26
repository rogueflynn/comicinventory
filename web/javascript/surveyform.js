/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$().ready(function() {
    $("#surveyForm").validate({
        rules: {
            yearsActive: {
            required: true,
            isnum: true
        },
        services: {
            required: true,
            minlength: 1
        },
        population: {
            required: true,
            minlength: 1
        },
        area: {
            required: true,
            minlength: 1
        },
        federal: {
            required: true,
            isnum: true
        },
        state: {
            required: true,
            isnum: true
        },
        county: {
            required: true,
            isnum: true
        },
        foundations: {
            required: true,
            isnum: true
        },
        corp: {
            required: true,
            isnum: true
        },
        donations: {
            required: true,
            isnum: true
        },
        fundraising: {
            required: true,
            isnum: true
        },
        
        vAns: {required: true},
        bAns: {required: true},
        cAns: {required: true},
        sAns: {required: true},
        collab: {required: true},
        gAns: {required: true},
        challengeOne: {required: true},
        challengeTwo: {required: true},
        challengeThree: {required: true},
        aidservices: {
            required: true,
            minlength: 1
        },
        official: {
            required: true,
            minlength: 1
        },
        netAns: {required: true},
        netFeeAns: {required: true},
        pay: {required: true},
        meeting: {
            required: true,
            minlength: 1
        },
        quartAns: {required: true},
        retreatAns: {required: true},
        contactAns: {required: true},
        facebook: {
            required: true,
            url: true
        },
        twitter: {
            required: true,
            url: true
        },
        linkedin: {
            required: true,
            url: true
        },
        googlePlus: {
            required: true,
            url: true
        }
    },
      errorPlacement : function(error, element) {
         error.appendTo('#invalid-' + element.attr('id'));
    },
    messages: {
        yearsActive: {
            required: "<font color=\"red\" size=\"2\">Please enter this field</font>",
            isnum: "<font color=\"red\" size=\"2\">Please enter a number</font>"
        },
        services: {
           required: "<font color=\"red\" size=\"2\">Please check one of the service boxes.</font>"
        },
        population: {
           required: "<font color=\"red\" size=\"2\">Please check one of the population boxes.</font>"
        },
         area: {
           required: "<font color=\"red\" size=\"2\">Please check one of the area boxes.</font>"
        },
        federal: {
           required: "<font color=\"red\" size=\"2\">Enter federal</font>",
           isnum: "<font color=\"red\" size=\"2\">Enter a number</font>"
        },
        state: {
           required: "<font color=\"red\" size=\"2\">Enter state</font>",
           isnum: "<font color=\"red\" size=\"2\">Enter a number</font>"
        },
        county: {
           required: "<font color=\"red\" size=\"2\">Enter county</font>",
           isnum: "<font color=\"red\" size=\"2\">Enter a number</font>"
        },
        foundations: {
           required: "<font color=\"red\" size=\"2\">Enter foundation</font>",
           isnum: "<font color=\"red\" size=\"2\">Enter a number</font>"
        },
        corp: {
           required: "<font color=\"red\" size=\"2\">Enter corporation</font>",
           isnum: "<font color=\"red\" size=\"2\">Enter a number</font>"
        },
        donations: {
           required: "<font color=\"red\" size=\"2\">Enter donations</font>",
           isnum: "<font color=\"red\" size=\"2\">Enter a number</font>"
        },
        fundraising: {
           required: "<font color=\"red\" size=\"2\">Enter fundraising</font>",
           isnum: "<font color=\"red\" size=\"2\">Enter a number</font>"
        },
        vAns: { required: "<font color=\"red\" size=\"2\">Required field</font>" },
        bAns: { required: "<font color=\"red\" size=\"2\">Required field</font>" },
        cAns: { required: "<font color=\"red\" size=\"2\">Required field</font>" },
        sAns: { required: "<font color=\"red\" size=\"2\">Required field</font>" },
        collab: { required: "<font color=\"red\" size=\"2\">Required field</font>" },
        gAns: { required: "<font color=\"red\" size=\"2\">Required field</font>" },
        challengeOne: { required: "<font color=\"red\" size=\"2\">Required field</font>" },
        challengeTwo: { required: "<font color=\"red\" size=\"2\">Required field</font>" },
        challengeThree: { required: "<font color=\"red\" size=\"2\">Required field</font>" },
        aidservices: {
           required: "<font color=\"red\" size=\"2\">Please check one of the service boxes.</font>"
        },
        official: {
           required: "<font color=\"red\" size=\"2\">Please check one of the official boxes.</font>"
        },
        netAns: { required: "<font color=\"red\" size=\"2\">Required field</font>" },
        netFeeAns: { required: "<font color=\"red\" size=\"2\">Required field</font>" },
        pay: { required: "<font color=\"red\" size=\"2\">Required field</font>" },
        meeting: {
           required: "<font color=\"red\" size=\"2\">Please check one of the meeting boxes.</font>"
        },
        quartAns: { required: "<font color=\"red\" size=\"2\">Required field</font>" },
        retreatAns: { required: "<font color=\"red\" size=\"2\">Required field</font>" },
        contactAns: { required: "<font color=\"red\" size=\"2\">Required field</font>" },
        facebook: { 
            url: "<font color=\"red\" size=\"2\">Please enter a valid url</font>",
            required: "<font color=\"red\" size=\"2\">Please enter a valid url</font>"
          },
        twitter: { 
            url: "<font color=\"red\" size=\"2\">Please enter a valid url</font>",
            required: "<font color=\"red\" size=\"2\">Please enter a valid url</font>"
          },
        linkedin: { 
            url: "<font color=\"red\" size=\"2\">Please enter a valid url</font>",
            required: "<font color=\"red\" size=\"2\">Please enter a valid url</font>"
          },
        googlePlus: { 
            url: "<font color=\"red\" size=\"2\">Please enter a valid url</font>",
            required: "<font color=\"red\" size=\"2\">Please enter a valid url</font>"
          }
    }
        
    });
       $.validator.addMethod("isnum", function(value) {
                return /^\d+$/.test(value);
        });
        
});


