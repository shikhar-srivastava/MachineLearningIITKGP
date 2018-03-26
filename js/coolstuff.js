

//~~~~~~~~~~~~~~~~~~~~~~ Helper function below. For future development~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

function find_file(name) {
      var dir = "uploads/";
       var fileExt = {};
      fileExt[0]=".png";
      fileExt[1]=".jpg";
      fileExt[2]=".jpeg";
    $.ajax({
          url: dir,
            success: function (data) {

                $(data).find("a:contains(\"" +fileExt[0]+"\")").each(function () {
                    var filename = this.href.replace(window.location.host, "").replace("http:///MachineLearningIITKGP/uploads/", "");
                   if(filename.valueOf==name.valueOf)
                   {
                      return filename+fileExt[0];
                    }
                });

              $(data).find("a:contains(\"" +fileExt[1]+"\")").each(function () {
                    var filename = this.href.replace(window.location.host, "").replace("http:///MachineLearningIITKGP/uploads/", "");
                   if(filename.valueOf==name.valueOf)
                   {
                      return filename+fileExt[1];
                   }
                });
              $(data).find("a:contains(\"" +fileExt[2]+"\")").each(function () {
                    var filename = this.href.replace(window.location.host, "").replace("http:///MachineLearningIITKGP/uploads/", "");
                   if(filename.valueOf==name.valueOf)
                   {
                      return filename+fileExt[2];
                   }
                });

              }
          
          });

      return null;
  }


 function removeLastHash (myUrl)
  {
      if (myUrl.charAt(myUrl.length-1) == '#')
      {
          myUrl = myUrl.substring(0, myUrl.length-1);

      }

      return myUrl;
  }




/*~~~~~~~~~~~~~~~~~~~~~~~Fetches Images from URL and creates a 'Slider' script for it as a string.~~~~~~~~*/
    function getImageScript() {
        var img = window.location.toString();
        var indexer= img.indexOf('?');
        if(indexer==-1)return null;
        img = removeLastHash(img.substring(indexer+1));
        var imgs=img.split('||'); 
        var appendStr="";
        for(var i=0;i<imgs.length;i++)
        {
          //var fullname=find_file(imgs[i]);
            appendStr+='<li><img src="uploads/'+imgs[i]+'"><div class="caption center-align"><h5 class="light black-text text-lighten-3 center" ><br><br><br><br>Scroll down to select<br><b>ML Model</b><br><br><br></h5><div class="row center"><span class="tcon-indicator" aria-label="scroll" aria-hidden="true"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" class="tcon-svgchevron" viewBox="0 0 30 36"><path class="a3" d="M0,0l15,16L30,0"></path> <path class="a2"   d="M0,10l15,16l15-16"></path><path class="a1" d="M0,20l15,16l15-16"></path></svg></span></div></div></li>';
        }
          return appendStr;
     }

     function getLoadingScript()
     {
        var img = window.location.toString();
        var indexer= img.indexOf('?');
        if(indexer==-1)return null;
        img = removeLastHash(img.substring(indexer+1));
        var imgs=img.split('||'); 
        var appendStr="";
        for(var i=0;i<imgs.length;i++)
        {
            appendStr+='<li><img src="uploads/'+imgs[i]+'"><div class="caption center-align"><h5 class="light black-text text-lighten-3 center"><br><br><br><br>Scroll down to select<br><b>ML Model</b><br><br><br></h5><div class="row center"><img src="rsc/loader.gif" width="30"></div></div></li>';
        }
          return appendStr;

     }
     //~~~~~~~~~~~~~~~~Returns to Input.html~~~~~~~~~~~~~~~~~~~~~~~~~~~


