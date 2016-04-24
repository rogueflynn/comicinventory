$(document).ready(function() {
  // hide all of the elements with a class of 'toggle'
  $('.toggle').hide();
 
  $('a.toggleLink').click(function() {
   	$('span', this).toggle(); 
  });

  // clicking the a.toggleLink will show the child li's
  $('a.toggleLink').click(function() {
    $(this).parent().next('.toggle').toggle('fast');
  });
});
