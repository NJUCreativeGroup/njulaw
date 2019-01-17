/**
 * Created by cristph on 2015/10/27.
 */

//document.getElementById('login').addEventListener('mouseover',btnFocus,false);
//document.getElementById('register').addEventListener('mouseover',btnFocus,false);
//
//document.getElementById('login').addEventListener('mouseout',btnBlur,false);
//document.getElementById('register').addEventListener('mouseout',btnBlur,false);

function btnFocus(){
    var id=this.id;
    if(id=='register'){
        this.style.backgroundColor='#32d1ac';
    }else{
        this.style.backgroundColor='#336699';
    }
}

function btnBlur(){
    this.style.backgroundColor='transparent';
}

function addURLParam(url, name, value){
    url+=( url.indexOf("?") == -1 ? "?" : "&" );
    url+=encodeURIComponent(name) + "=" + encodeURIComponent(value);
    return url;
}

function showAj(Wsah){
    open(addURLParam("/wsInfo","Wsah",Wsah));
}

function showPage(sorts,orders,BeginIndex){
    var opts = {
        lines: 13 // The number of lines to draw
        , length: 28 // The length of each line
        , width: 14 // The line thickness
        , radius: 42 // The radius of the inner circle
        , scale: 1 // Scales overall size of the spinner
        , corners: 1 // Corner roundness (0..1)
        , color: '#000' // #rgb or #rrggbb or array of colors
        , opacity: 0.25 // Opacity of the lines
        , rotate: 0 // The rotation offset
        , direction: 1 // 1: clockwise, -1: counterclockwise
        , speed: 1 // Rounds per second
        , trail: 60 // Afterglow percentage
        , fps: 20 // Frames per second when using setTimeout() as a fallback for CSS
        , zIndex: 2e9 // The z-index (defaults to 2000000000)
        , className: 'spinner' // The CSS class to assign to the spinner
        , top: '50%' // Top position relative to parent
        , left: '50%' // Left position relative to parent
        , shadow: false // Whether to render a shadow
        , hwaccel: false // Whether to use hardware acceleration
        , position: 'absolute' // Element positioning
    };
    var target = document.getElementById('AjDiv');
    var spinner = new Spinner(opts).spin(target);
    $.post(
        "/goPage",
        {
            "qwjs":encodeURIComponent($('#cond_qwjs').val()),
            "qwjsInput":encodeURIComponent($('#cond_qwjsInput').val()),
            "ay":encodeURIComponent($('#cond_ay').val()),
            "ah":encodeURIComponent($('#cond_ah').val()),
            "ajmc":encodeURIComponent($('#cond_ajmc').val()),
            "fymc":encodeURIComponent($('#cond_fymc').val()),
            "fycj":encodeURIComponent($('#cond_fycj').val()),
            "ajlx":encodeURIComponent($('#cond_ajlx').val()),
            "spcx":encodeURIComponent($('#cond_spcx').val()),
            "wslx":encodeURIComponent($('#cond_wslx').val()),
            "cprqbegin":encodeURIComponent($('#cond_cprqbegin').val()),
            "cprqend":encodeURIComponent($('#cond_cprqend').val()),
            "cpry":encodeURIComponent($('#cond_cpry').val()),
            "dsr":encodeURIComponent($('#cond_dsr').val()),
            "flyj":encodeURIComponent($('#cond_flyj').val()),
            "cpnf":encodeURIComponent($('#cond_cpnf').val()),
            "SortClass[]":sorts,
            "SortType[]":orders,
            "BeginIndex":BeginIndex
        },
        function(data){
            $('#AjDiv').html(data);
        }
    );
}

function goPage(BeginIndex){
    var current=event.toElement.parentNode;
    $(current.parentNode).find("li").each(function(){
       $(this).removeClass("active");
    });
    $(current).addClass("active");
    var sorts=[];
    var orders=[];
    if($('#isAssociation').val()=='true'){
        sorts.push('association');
        orders.push('desc');
    }else{
        sorts.push('cprq');
        orders.push($('#cprqOrder').val());
    }
    //alert(sorts+"|"+orders+"|"+BeginIndex);
    showPage(sorts,orders,BeginIndex);
    $('#currentPageIndex').val(BeginIndex);
}

function goPrevious(){
    var maxPageNum=$('#maxPageNum').val();
    var currentPageIndex=$('#currentPageIndex').val();
    if(currentPageIndex==1){
        return;
    }else{
        var firstPageIndex=$('#firstPageIndex').val();
        if(currentPageIndex==firstPageIndex){
            var str="";
            var previous=parseInt(currentPageIndex)-1;
            str+="<li><a href=\"javascript:void(0)\" aria-label=\"Previous\" onclick=\"goPrevious()\"><span aria-hidden=\"true\">&laquo;</span></a></li>";
            if(previous-4>=1){
                for(var i=previous-4;i<=previous;i++){
                    str+="<li id=\"page"+i+"\" class=\"\"><a href=\"javascript:void(0)\" onclick=\"goPage('"+i+"')\">"+i+"</a></li>";
                }
            }else{
                for(var i=1;i<=previous;i++){
                    str+="<li id=\"page"+i+"\" class=\"\"><a href=\"javascript:void(0)\" onclick=\"goPage('"+i+"')\">"+i+"</a></li>";
                }
            }
            str+="<li><a href=\"javascript:void(0)\" aria-label=\"Next\" onclick=\"goNext()\"><span aria-hidden=\"true\">&raquo;</span></a></li>";
            $('#PP').html(str);
            $('#page'+previous).addClass('active');
            $('#endPageIndex').val(previous);
            if(previous-4>=1){
                $('#firstPageIndex').val(previous-4);
            }else{
                $('#firstPageIndexPageIndex').val(1);
            }
            $('#currentPageIndex').val(previous);
            goPage(previous);
        }else{
            $('#page'+currentPageIndex).removeClass('active');
            var previous=parseInt(currentPageIndex)-1;
            $('#page'+previous).addClass('active');
            $('#currentPageIndex').val(previous);
            goPage(previous);
        }
    }
}

function goNext(){
    var maxPageNum=$('#maxPageNum').val();
    var currentPageIndex=$('#currentPageIndex').val();
    if(currentPageIndex==maxPageNum){
        return;
    }else{
        var endPageIndex=$('#endPageIndex').val();
        if(currentPageIndex==endPageIndex){
            var str="";
            var next=parseInt(currentPageIndex)+1;
            str+="<li><a href=\"javascript:void(0)\" aria-label=\"Previous\" onclick=\"goPrevious()\"><span aria-hidden=\"true\">&laquo;</span></a></li>";
            if(next+4<=maxPageNum){
                for(var i=next;i<next+5;i++){
                    str+="<li id=\"page"+i+"\" class=\"\"><a href=\"javascript:void(0)\" onclick=\"goPage('"+i+"')\">"+i+"</a></li>";
                }
            }else{
                for(var i=next;i<=maxPageNum;i++){
                    str+="<li id=\"page"+i+"\" class=\"\"><a href=\"javascript:void(0)\" onclick=\"goPage('"+i+"')\">"+i+"</a></li>";
                }
            }
            str+="<li><a href=\"javascript:void(0)\" aria-label=\"Next\" onclick=\"goNext()\"><span aria-hidden=\"true\">&raquo;</span></a></li>";
            $('#PP').html(str);
            $('#page'+next).addClass('active');
            $('#firstPageIndex').val(next);
            if(next+4<=maxPageNum){
                $('#endPageIndex').val(next+4);
            }else{
                $('#endPageIndex').val(maxPageNum);
            }
            $('#currentPageIndex').val(next);
            goPage(next);
        }else{
            $('#page'+currentPageIndex).removeClass('active');
            var next=parseInt(currentPageIndex)+1;
            $('#page'+next).addClass('active');
            $('#currentPageIndex').val(next);
            goPage(next);
        }
    }
}

$('#complexSearch').click(function(){
    var qwjsInput=$('#qwjsInput').val();
    var qwjs=$('#qwjs option:selected').val();
    var ay=$('#ay').val();
    var ah=$('#ah').val();
    var ajmc=$('#ajmc').val();
    var fymc=$('#fymc').val();
    var fycj=$('#fycj option:selected').val()=='all' ? '' : $('#fycj option:selected').val();
    var ajlx=$('#ajlx option:selected').val()=='all' ? '': $('#ajlx option:selected').val();
    var spcx=$('#spcx option:selected').val()=='all' ? '': $('#spcx option:selected').val();
    var wslx=$('#wslx option:selected').val()=='all' ? '': $('#wslx option:selected').val();
    var cprqbegin=$('#cprqbegin').val();
    var cprqend=$('#cprqend').val();
    var cpry=$('#cpry').val();
    var dsr=$('#dsr').val();
    //var lvsuo=$('#lvsuo').val();
    //var lvshi=$('#lvshi').val();
    var flyj=$('#flyj').val();
    var cpnf=$('#cpnf').val();
    console.log("qwjsInput:"+qwjsInput+";qwjs:"+qwjs+";ay:"+ay+";ah:"+ah+";ajmc:"+ajmc+";fymc:"+fymc+";fycj:"+fycj+";ajlx:"+ajlx
        +";spcx:"+spcx+";wslx:"+wslx+";cprqbegin:"+cprqbegin+";cprqend:"+cprqend+";cpry:"+cpry+";dsr:"+dsr+";flyj:"+flyj+";cpnf:"+cpnf);

    var url="/complexSearch";
    url=addURLParam(url,"qwjs",qwjs);
    url=addURLParam(url,"qwjsInput",qwjsInput);
    url=addURLParam(url,"ay",ay);
    url=addURLParam(url,"ah",ah);
    url=addURLParam(url,"ajmc",ajmc);
    url=addURLParam(url,"fymc",fymc);
    url=addURLParam(url,"fycj",fycj);
    url=addURLParam(url,"ajlx",ajlx);
    url=addURLParam(url,"spcx",spcx);
    url=addURLParam(url,"wslx",wslx);
    url=addURLParam(url,"cprqbegin",cprqbegin);
    url=addURLParam(url,"cprqend",cprqend);
    url=addURLParam(url,"cpry",cpry);
    url=addURLParam(url,"dsr",dsr);
    //url=addURLParam(url,"lvsuo",lvsuo);
    //url=addURLParam(url,"lvshi",lvshi);
    url=addURLParam(url,"flyj",flyj);
    url=addURLParam(url,"cpnf",cpnf);

    location.href=url;
});

function downloadXml(){
    var ajs=document.getElementsByClassName("AJ");
    var paths="";
    for(var i=0;i<ajs.length;i++){
        if(ajs[i].firstElementChild.checked){
            var path=$(ajs[i].firstElementChild).val();
            if($.trim(path).length>0){
                //console.log("add:"+path);
                paths+=($.trim(path)+"|");
            }
        }
    }
    alert(ajs.length);

    console.log("xmlpaths:"+paths);
    if(paths.length>0){
        location.href=(addURLParam("/downloadZip","paths",paths));
    }else{
        alert("PLEASE Select The Files to DOWNLAOD!");
    }
}


function downloadDoc(){
    var ajs=document.getElementsByClassName("AJ");
    var paths="";
    for(var i=0;i<ajs.length;i++){
        if(ajs[i].firstElementChild.checked){
            var path=$(ajs[i].lastElementChild).val();
            if($.trim(path).length>0){
                //console.log("add:"+getDocPath($.trim(path)));
                paths+=(getDocPath($.trim(path))+"|");
            }
        }
    }
    console.log("docpaths:"+paths);
    if(paths.length>0){
        location.href=(addURLParam("/downloadZip","paths",paths));
    }else{
        alert("PLEASE Select The Files to DOWNLAOD!");
    }

}

function downloadSingleXML(fileName){
    var path=document.getElementById("xml"+fileName).value;
    var url=addURLParam("/singleDownload","path",path);
    var realFileName=path.substr(path.lastIndexOf("\\")+1);
    //alert(realFileName);
    url=addURLParam(url,"fileName",realFileName);
    console.log("download url:"+url);
    location.href=url;
}

function getDocPath(path){
    var temp=path.split("\\");
    temp[temp.length-2]="doc";
    temp[temp.length-1]=temp[temp.length-1].replace("xml","doc");
    path="";
    for(var i=0;i< temp.length;i++){
        //console.log(temp[i]);
        path+=(temp[i]+"\\");
    }
    path=path.substr(0,path.length-1);
    return path;
}


function downloadSingleDOC(fileName){
    var path=document.getElementById("xml"+fileName).value;
    path=getDocPath(path);
    console.log("docpath:"+path);
    var url=addURLParam("/singleDownload","path",path);
    var realFileName=path.substr(path.lastIndexOf("\\")+1);
    url=addURLParam(url,"fileName",realFileName);
    console.log("download url:"+url);
    location.href=url;
}

function downloadAll(type){
    var url="/downloadAll";
    url=addURLParam(url,"qwjs",$('#cond_qwjs').val());
    url=addURLParam(url,"qwjsInput",$('#cond_qwjsInput').val());
    url=addURLParam(url,"ay",$('#cond_ay').val());
    url=addURLParam(url,"ah",$('#cond_ah').val());
    url=addURLParam(url,"ajmc",$('#cond_ajmc').val());
    url=addURLParam(url,"fymc",$('#cond_fymc').val());
    url=addURLParam(url,"fycj",$('#cond_fycj').val());
    url=addURLParam(url,"ajlx",$('#cond_ajlx').val());
    url=addURLParam(url,"spcx",$('#cond_spcx').val());
    url=addURLParam(url,"wslx",$('#cond_wslx').val());
    url=addURLParam(url,"cprqbegin",$('#cond_cprqbegin').val());
    url=addURLParam(url,"cprqend",$('#cond_cprqend').val());
    url=addURLParam(url,"cpry",$('#cond_cpry').val());
    url=addURLParam(url,"dsr",$('#cond_dsr').val());
    url=addURLParam(url,"flyj",$('#cond_flyj').val());
    url=addURLParam(url,"cpnf",$('#cond_cpnf').val());
    url=addURLParam(url,"type",type);
    location.href=url;
}
//add

function downloadSome(type){
    var url="/downloadSome";
    url=addURLParam(url,"qwjs",$('#cond_qwjs').val());
    url=addURLParam(url,"qwjsInput",$('#cond_qwjsInput').val());
    url=addURLParam(url,"ay",$('#cond_ay').val());
    url=addURLParam(url,"ah",$('#cond_ah').val());
    url=addURLParam(url,"ajmc",$('#cond_ajmc').val());
    url=addURLParam(url,"fymc",$('#cond_fymc').val());
    url=addURLParam(url,"fycj",$('#cond_fycj').val());
    url=addURLParam(url,"ajlx",$('#cond_ajlx').val());
    url=addURLParam(url,"spcx",$('#cond_spcx').val());
    url=addURLParam(url,"wslx",$('#cond_wslx').val());
    url=addURLParam(url,"cprqbegin",$('#cond_cprqbegin').val());
    url=addURLParam(url,"cprqend",$('#cond_cprqend').val());
    url=addURLParam(url,"cpry",$('#cond_cpry').val());
    url=addURLParam(url,"dsr",$('#cond_dsr').val());
    url=addURLParam(url,"flyj",$('#cond_flyj').val());
    url=addURLParam(url,"cpnf",$('#cond_cpnf').val());
    url=addURLParam(url,"type",type);

    var pageCount=$('#downloadPageCount').val();
    var pageCountEnd=$('#downloadPageCountEnd').val();
   // var start=0;
    var start=(pageCount-1)*5+1;
    var end=pageCountEnd*5;
    // var end=(pageCountEnd-pageCount+1)*5;
    url=addURLParam(url,"start",start);
    url=addURLParam(url,"end",end);
    alert(start);
    alert(url);
    location.href=url;
}



//add





$('#downloadAll').click(function(){
    var ajs=document.getElementsByClassName("AJ");
    for(var i=0;i<ajs.length;i++){
        if($(ajs[i].firstElementChild).is(':checked')){
            $(ajs[i].firstElementChild).removeAttr("checked");
        }else{
            $(ajs[i].firstElementChild).prop("checked",true);
        }
    }
});

//add

$('#downloadSome').click(function(){
    var ajs=document.getElementsByClassName("AJ");
    for(var i=0;i<ajs.length;i++){
        if($(ajs[i].firstElementChild).is(':checked')){
            $(ajs[i].firstElementChild).removeAttr("checked");
        }else{
            $(ajs[i].firstElementChild).prop("checked",true);
        }
    }
});


function removeLabel(key){
    $('#cond_'+key).val('');
   if(event.toElement.parentNode.parentNode.childNodes.length==3){
        //alert("in");
        location.href="/index";
        return;
    }
    //alert("out");
    $(event.toElement.parentNode).remove();

    var qwjsInput=$('#qwjsInput').val();
    var qwjs=$('#qwjs option:selected').val();

    var url="/complexSearch";
    url=addURLParam(url,"qwjs",$('#cond_qwjs').val());
    url=addURLParam(url,"qwjsInput",$('#cond_qwjsInput').val());
    url=addURLParam(url,"ay",$('#cond_ay').val());
    url=addURLParam(url,"ah",$('#cond_ah').val());
    url=addURLParam(url,"ajmc",$('#cond_ajmc').val());
    url=addURLParam(url,"fymc",$('#cond_fymc').val());
    url=addURLParam(url,"fycj",$('#cond_fycj').val());
    url=addURLParam(url,"ajlx",$('#cond_ajlx').val());
    url=addURLParam(url,"spcx",$('#cond_spcx').val());
    url=addURLParam(url,"wslx",$('#cond_wslx').val());
    url=addURLParam(url,"cprqbegin",$('#cond_cprqbegin').val());
    url=addURLParam(url,"cprqend",$('#cond_cprqend').val());
    url=addURLParam(url,"cpry",$('#cond_cpry').val());
    url=addURLParam(url,"dsr",$('#cond_dsr').val());
    url=addURLParam(url,"flyj",$('#cond_flyj').val());
    url=addURLParam(url,"cpnf",$('#cond_cpnf').val());
    location.href=url;
}

function resetNum(){
    $.post(
        "/getNum",
        {
            "qwjs":encodeURIComponent($('#cond_qwjs').val()),
            "qwjsInput":encodeURIComponent($('#cond_qwjsInput').val()),
            "ay":encodeURIComponent($('#cond_ay').val()),
            "ah":encodeURIComponent($('#cond_ah').val()),
            "ajmc":encodeURIComponent($('#cond_ajmc').val()),
            "fymc":encodeURIComponent($('#cond_fymc').val()),
            "fycj":encodeURIComponent($('#cond_fycj').val()),
            "ajlx":encodeURIComponent($('#cond_ajlx').val()),
            "spcx":encodeURIComponent($('#cond_spcx').val()),
            "wslx":encodeURIComponent($('#cond_wslx').val()),
            "cprqbegin":encodeURIComponent($('#cond_cprqbegin').val()),
            "cprqend":encodeURIComponent($('#cond_cprqend').val()),
            "cpry":encodeURIComponent($('#cond_cpry').val()),
            "dsr":encodeURIComponent($('#cond_dsr').val()),
            //"lvsuo":encodeURIComponent($('#cond_lvsuo').val()),
            //"lvshi":encodeURIComponent($('#cond_lvshi').val()),
            "flyj":encodeURIComponent($('#cond_flyj').val()),
            "cpnf":encodeURIComponent($('#cond_cpnf').val())
        },
        function(data){
            var nums=data.split(';');
            $('#AJC').html(nums[0]);
            $('#PN').html(nums[1]);
        }
    );
}

function changeSortOrder(sortClass){

    if(sortClass=='cprq'){
        //alert('change');
        var current=$('#'+sortClass+'Arrow');
        if(current.hasClass('glyphicon-arrow-up')){
            current.removeClass('glyphicon-arrow-up');
            current.addClass('glyphicon-arrow-down');
            $('#'+sortClass+'Order').val('desc');
        }else{
            current.removeClass('glyphicon-arrow-down');
            current.addClass('glyphicon-arrow-up');
            $('#'+sortClass+'Order').val('asc');
        }

        document.getElementById('associationColor').style.backgroundColor="grey";
        document.getElementById('cprqColor').style.backgroundColor="#f98910";
        //alert("post");
        var sorts=[];
        var orders=[];
        sorts.push('cprq');
        orders.push($('#cprqOrder').val());
        $('#isAssociation').val('false');
        showPage(sorts,orders,1);
    }else{
        document.getElementById('associationColor').style.backgroundColor="#f98910";
        document.getElementById('cprqColor').style.backgroundColor="grey";
        //alert('still');
        var sorts=[];
        var orders=[];
        sorts.push('association');
        orders.push('desc');
        $('#isAssociation').val('true');
        showPage(sorts,orders,1);
    }
}