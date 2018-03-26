function open_popup(form) {
    window.open('', 'formpopup', 'width=500,height=600,resizeable,scrollbars,status=0,titlebar=0');
    form.target = 'formpopup';
}

$(document).ready(function() {
      $('.modal-trigger').leanModal({
       dismissible: true,
       opacity: .5,
       in_duration: 200,
       out_duration: 200,
       starting_top: '10%'
    });
  Materialize.toast("Welcome !",1000,'rounded');
});


var getCookie = function(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) == 0) {
          var len1 = name.length;
          var len2 = c.length;
          if(c.charAt(len1) == '"') len1++;
          if(c.charAt(len2-1) == '"') len2--;
          return c.substring(len1,len2);  //remove those pesky quotaions
        }
    }
    return "";
}

var getAppend = function(cname,strs) {
    var name = cname + "=";
    var ca = strs.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) == 0) {
          var len1 = name.length;
          var len2 = c.length;
          if(c.charAt(len1) == '"') len1++;
          if(c.charAt(len2-1) == '"') len2--;
          return c.substring(len1,len2);  //remove those pesky quotaions
        }
    }
    return "";
}
