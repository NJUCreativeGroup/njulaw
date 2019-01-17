<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cristph
  Date: 2017/3/24
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>文书搜索结果</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/reset.css" rel="stylesheet">
    <link href="css/base.css" rel="stylesheet">
    <link href="css/zTreeStyle.css" rel="stylesheet">
    <link href="css/search.css" rel="stylesheet">
    <link href="css/cikonss.css" rel="stylesheet"/>
    <%--<link href="css/buttons.css" rel="stylesheet">--%>
</head>


<body>

<div class="home">
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <p style="font-size: large;font-weight: bold;color: white" onclick="location.href='/index'">WSSearch</p>
            </div>
            <%--<div class="col-sm-9 home-links">--%>
                <%--<a href="${pageContext.request.contextPath}/login" id="login">Login</a>--%>
                <%--<a href="${pageContext.request.contextPath}/register" class="home-register" id="register">Sign up</a>--%>
            <%--</div>--%>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 text-center">
                <h1>文书可视化与查询工具</h1>
                <p style="height: 50px"></p>

                <div class="input-group">
                    <span class="input-group-addon btn-lg join-btn dropdown-toggle"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="font-size: large;font-weight: bolder">高级检索</span>
                    <div class="dropdown-menu" style="min-width: 700px;border-radius: 0px;padding:2%;">
                        <div class="row" style="margin-bottom: 2%">
                            <div class="col-sm-11">
                                <span >全文检索:</span>
                                <input type="text" style="width: 70%" id="qwjsInput">
                                <select id="qwjs" class="input-md form-control" style="display: inline;float:right;margin-right:5%;width: 60px;height:27px;font-size: 12px;padding: 0;border-radius: 0">
                                    <option value="qw">全文</option>
                                    <option value="ws">首部</option>
                                    <option value="ajjbqk">事实</option>
                                    <option value="cpfxgc">理由</option>
                                    <option value="pjjg">判决结果</option>
                                    <option value="ww">尾部</option>
                                </select>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 2%">
                            <div class="col-sm-6">
                                <span>案由：</span><input type="text" id="ay">
                            </div>
                            <div class="col-sm-6">
                                <span>裁判年份：</span><input type="text" id="cpnf">
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 2%">
                            <div class="col-sm-6">
                                <span>案件名称：</span><input type="text" id="ajmc">
                            </div>
                            <div class="col-sm-6">
                                <span>案号：</span><input type="text" id="ah">
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 2%">
                            <div class="col-sm-6">
                                <span>法院名称：</span><input type="text" id="fymc">
                            </div>
                            <div class="col-sm-6">
                                <span>法院层级：</span>
                                <select id="fycj" class="input-md form-control" style="display: inline;float: right;margin-right:20%;width: 180px;height:27px;font-size: 12px;padding: 0;border-radius: 0">
                                    <option value="all">全部</option>
                                    <option value="1">最高法院</option>
                                    <option value="2">高级法院</option>
                                    <option value="3">中级法院</option>
                                    <option value="4">基层法院</option>
                                </select>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 2%">
                            <div class="col-sm-6">
                                <span>案件类型：</span>
                                <select id="ajlx" class="input-md form-control" style="display: inline;float: right;margin-right:25%;width: 160px;height:27px;font-size: 12px;padding: 0;border-radius: 0">
                                    <option value="all">全部</option>
                                    <option value="刑事案件">刑事案件</option>
                                    <option value="民事案件">民事案件</option>
                                    <option value="行政案件">行政案件</option>
                                    <option value="赔偿案件">赔偿案件</option>
                                    <option value="执行案件">执行案件</option>
                                </select>
                            </div>
                            <div class="col-sm-6">
                                <span>审判程序：</span>
                                <select id="spcx" class="input-md form-control" style="display: inline;float: right;margin-right:20%;width: 180px;height:27px;font-size: 12px;padding: 0;border-radius: 0">
                                    <option value="all">全部</option>
                                    <option value="一审案件">一审案件</option>
                                    <option value="二审案件">二审案件</option>
                                    <option value="再审案件">再审案件</option>
                                    <option value="再审案件">复核案件</option>
                                    <option value="特别程序案件">特别程序案件</option>
                                    <option value="再审复查与审判监督案">再审复查与审判监督案</option>
                                </select>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 2%">
                            <div class="col-sm-5">
                                <span>文书类型：</span>
                                <select id="wslx" class="input-md form-control" style="display: inline;float: right;margin-right:10%;width: 160px;height:27px;font-size: 12px;padding: 0;border-radius: 0">
                                    <option value="all">全部</option>
                                    <option value="判决书">判决书</option>
                                    <option value="裁判文书">裁判文书 </option>
                                    <option value="调解书">调解书</option>
                                    <option value="决定书">决定书</option>
                                    <option value="通知书">通知书</option>
                                    <%--<option value="批复">批复</option>--%>
                                    <%--<option value="答复">答复</option>--%>
                                    <%--<option value="函">函</option>--%>
                                    <%--<option value="令">令</option>--%>
                                    <%--<option value="其他">其他</option>--%>
                                </select>
                            </div>
                            <div class="col-sm-7">
                                <div class="row">
                                    <div class="col-sm-3" style="padding: 0">
                                        <span>裁判日期：</span>
                                    </div>
                                    <div class="col-sm-4" style="padding: 0">
                                        <input type="date" style="width: 100%" id="cprqbegin">
                                    </div>
                                    <div class="col-sm-4" style="padding: 0">
                                        <input type="date" style="width: 100%" id="cprqend">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 2%">
                            <div class="col-sm-6">
                                <span>裁判人员：</span><input type="text" id="cpry">
                            </div>
                            <div class="col-sm-6">
                                <span>当事人：</span><input type="text" id="dsr">
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 2%">
                            <div class="col-sm-11">
                                <span>法律依据：</span><input type="text" style="width: 70%;" id="flyj">
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 2%">
                            <div class="col-sm-6" ><button id="complexSearch">检索</button></div>
                            <div class="col-sm-6"><button id="resetSearch">重置</button></div>
                        </div>
                    </div>
                    <input type="text" class="form-control input-lg" placeholder="输入案由、关键词、法院、当事人、律师" id="searchInput">
                    <span class="input-group-addon btn-lg join-btn" style="font-size: large;font-weight: bolder" id="searchBtn">搜索</span>
                </div>
                <p style="height: 60px"></p>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-3 treeBox">
                <div id="treeDemo" class="ztree"></div>
            </div>
            <div class="col-sm-9">
                <div class="title-box" id="condBox">检索条件：
                    <c:if test="${qwjsInput!=''}">
                        <span class="label label-primary">
                            <c:if test="${qwjs eq 'qw'}">全文:</c:if>
                            <c:if test="${qwjs eq 'ws'}">文首:</c:if>
                            <c:if test="${qwjs eq 'ajjbqk'}">事实:</c:if>
                            <c:if test="${qwjs eq 'cpfxgc'}">理由:</c:if>
                            <c:if test="${qwjs eq 'pjjg'}">判决结果:</c:if>
                            <c:if test="${qwjs eq 'ww'}">文尾:</c:if>
                        ${qwjsInput} <span class="glyphicon glyphicon-remove scondition" aria-hidden="true" onclick="removeLabel('qwjsInput')"></span></span>
                    </c:if>
                    <c:if test="${ay!=''}">
                        <span class="label label-primary">案由:${ay} <span class="glyphicon glyphicon-remove scondition" aria-hidden="true" onclick="removeLabel('ay')"></span></span>
                    </c:if>
                    <c:if test="${ah!=''}">
                        <span class="label label-primary">案号:${ah} <span class="glyphicon glyphicon-remove scondition" aria-hidden="true" onclick="removeLabel('ah')"></span></span>
                    </c:if>
                    <c:if test="${ajmc!=''}">
                        <span class="label label-primary">案件名称:${ajmc} <span class="glyphicon glyphicon-remove scondition" aria-hidden="true" onclick="removeLabel('ajmc')"></span></span>
                    </c:if>
                    <c:if test="${fymc!=''}">
                        <span class="label label-primary">法院名称:${fymc} <span class="glyphicon glyphicon-remove scondition" aria-hidden="true" onclick="removeLabel('fymc')"></span></span>
                    </c:if>
                    <c:if test="${fycj!=''}">
                        <c:if test="${fycj==0}">
                            <span class="label label-primary">法院层级:全部法院 <span class="glyphicon glyphicon-remove scondition" aria-hidden="true" onclick="removeLabel('fycj')"></span></span>
                        </c:if>
                        <c:if test="${fycj==1}">
                            <span class="label label-primary">法院层级:最高法院 <span class="glyphicon glyphicon-remove scondition" aria-hidden="true" onclick="removeLabel('fycj')"></span></span>
                        </c:if>
                        <c:if test="${fycj==2}">
                            <span class="label label-primary">法院层级:高级法院 <span class="glyphicon glyphicon-remove scondition" aria-hidden="true" onclick="removeLabel('fycj')"></span></span>
                        </c:if>
                        <c:if test="${fycj==3}">
                            <span class="label label-primary">法院层级:中级法院 <span class="glyphicon glyphicon-remove scondition" aria-hidden="true" onclick="removeLabel('fycj')"></span></span>
                        </c:if>
                        <c:if test="${fycj==4}">
                            <span class="label label-primary">法院层级:低级法院 <span class="glyphicon glyphicon-remove scondition" aria-hidden="true" onclick="removeLabel('fycj')"></span></span>
                        </c:if>
                        <c:if test="${fycj==5}">
                            <span class="label label-primary">法院层级:基层法院 <span class="glyphicon glyphicon-remove scondition" aria-hidden="true" onclick="removeLabel('fycj')"></span></span>
                        </c:if>
                        <%--<span class="label label-primary">法院层级:${fycj} <span class="glyphicon glyphicon-remove scondition" aria-hidden="true" onclick="removeLabel('fycj')"></span></span>--%>
                    </c:if>
                    <c:if test="${ajlx!=''}">
                        <span class="label label-primary">案件类型:${ajlx} <span class="glyphicon glyphicon-remove scondition" aria-hidden="true" onclick="removeLabel('ajlx')"></span></span>
                    </c:if>
                    <c:if test="${spcx!=''}">
                        <span class="label label-primary">审判程序:${spcx} <span class="glyphicon glyphicon-remove scondition" aria-hidden="true" onclick="removeLabel('spcx')"></span></span>
                    </c:if>
                    <c:if test="${wslx!=''}">
                        <span class="label label-primary">文书类型:${wslx} <span class="glyphicon glyphicon-remove scondition" aria-hidden="true" onclick="removeLabel('wslx')"></span></span>
                    </c:if>
                    <c:if test="${cpry!=''}">
                        <span class="label label-primary">裁判人员:${cpry} <span class="glyphicon glyphicon-remove scondition" aria-hidden="true" onclick="removeLabel('cpry')"></span></span>
                    </c:if>
                    <c:if test="${dsr!=''}">
                        <span class="label label-primary">当事人:${dsr} <span class="glyphicon glyphicon-remove scondition" aria-hidden="true" onclick="removeLabel('dsr')"></span></span>
                    </c:if>
                    <%--<c:if test="${lvsuo!=''}">--%>
                        <%--<span class="label label-primary">律所:${lvsuo} <span class="glyphicon glyphicon-remove scondition" aria-hidden="true" onclick="removeLabel('lvsuo')"></span></span>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${lvshi!=''}">--%>
                        <%--<span class="label label-primary">律师:${lvshi} <span class="glyphicon glyphicon-remove scondition" aria-hidden="true" onclick="removeLabel('lvshi')"></span></span>--%>
                    <%--</c:if>--%>
                    <c:if test="${flyj!=''}">
                        <span class="label label-primary">法律依据:${flyj} <span class="glyphicon glyphicon-remove scondition" aria-hidden="true" onclick="removeLabel('flyj')"></span></span>
                    </c:if>
                    <c:if test="${cpnf!=''}">
                        <span class="label label-primary" >裁判年份:${cpnf} <span class="glyphicon glyphicon-remove scondition" aria-hidden="true" onclick="removeLabel('cpnf')"></span></span>
                    </c:if>
                </div>
                <div id="searchConditions">
                    <input type="hidden" id="viewName" value="">
                    <input type="hidden" id="cond_qwjs" value="${qwjs}">
                    <input type="hidden" id="cond_qwjsInput" value="${qwjsInput}">
                    <input type="hidden" id="cond_ay" value="${ay}">
                    <input type="hidden" id="cond_ah" value="${ah}">
                    <input type="hidden" id="cond_ajmc" value="${ajmc}">
                    <input type="hidden" id="cond_fymc" value="${fymc}">
                    <input type="hidden" id="cond_fycj" value="${fycj}">
                    <input type="hidden" id="cond_ajlx" value="${ajlx}">
                    <input type="hidden" id="cond_spcx" value="${spcx}">
                    <input type="hidden" id="cond_wslx" value="${wslx}">
                    <input type="hidden" id="cond_cprqbegin" value="${cprqbegin}">
                    <input type="hidden" id="cond_cprqend" value="${cprqend}">
                    <input type="hidden" id="cond_cpry" value="${cpry}">
                    <input type="hidden" id="cond_dsr" value="${dsr}">
                    <%--<input type="hidden" id="cond_lvsuo" value="${lvsuo}">--%>
                    <%--<input type="hidden" id="cond_lvshi" value="${lvshi}">--%>
                    <input type="hidden" id="cond_flyj" value="${flyj}">
                    <input type="hidden" id="cond_cpnf" value="${cpnf}">

                    <input type="hidden" id="SortClass" value="${SortClass}">
                    <input type="hidden" id="SortType" value="${SortType}">
                </div>
                <div class="row" style="margin: 2% 0 2% 0">
                    <input type="hidden" id="isAssociation" value="true">
                    <input type="hidden" id="cprqOrder" value="desc">
                    <span class="sortBtn" onclick="changeSortOrder('association')" id="associationColor">关联度 <span class="glyphicon glyphicon-arrow-down" aria-hidden="true" id="associationArrow"></span></span>
                    <span class="sortBtn" onclick="changeSortOrder('cprq')" id="cprqColor" style="background-color: grey">裁判日期 <span class="glyphicon glyphicon-arrow-down" aria-hidden="true" id="cprqArrow"></span></span>
                    <span class="sortBtn" onclick="changeSortOrder('cprq')"style="background-color: #56806b">文书大小<span class="glyphicon glyphicon-arrow-down" aria-hidden="true" ></span></span>

                    <span style="font-weight: bolder;color: white">共得到<span id="AJC">${AjCount}</span>条记录</span><span style="width: 100px;height: 2px"> </span>

                    <input type="checkbox" style="margin:5px 0 0;border:1px solid #006600;" id="downloadAll">
                    <span style="border:1px solid #006600;border-radius: 2px;background-color: whitesmoke;color: black;cursor: pointer;padding: 0.5%" onclick="downloadXml()">批量下载xml</span>
                    <span style="border:1px solid #006600;border-radius: 2px;background-color: whitesmoke;color: black;cursor: pointer;padding: 0.5%" onclick="downloadDoc()">批量下载doc</span>
                </div>
                <div>
                    <div id="AjDiv">
                        <c:forEach items="${list}" var="item">
                        <div class="AJ">
                            <input type="checkbox" value="${item.xmlPath}" style="margin:5px 0 0;border:1px solid #006600;">
                            <div class="row AJBiaoTi" onclick="showAj('${item.ah}')">
                                    ${item.spcx} ${item.wsmc}
                            </div>
                            <div class="row AJCiBiaoTi" onclick="showAj('${item.ah}')">
                                    ${item.fymc} &nbsp;&nbsp;&nbsp;&nbsp; ${item.ah} &nbsp;&nbsp;&nbsp;&nbsp; ${item.cprq}
                            </div>
                            <div style="margin: 3% 0 3% 2%">
                                ${item.matchText}
                            </div>
                            <div style="padding: 0 0 20px 0;">
                                <span class="glyphicon glyphicon-save scondition" aria-hidden="true" onclick="downloadSingleXML('${item._id}')" style="float: right;margin-right: 80px;color: #b806f9">XML</span>
                                <span class="glyphicon glyphicon-save scondition" aria-hidden="true" onclick="downloadSingleDOC('${item._id}')" style="float: right;margin-right: 80px;color: #1143fe">DOC</span>
                            </div>
                            <input type="hidden" value="${item.ah}">
                            <input type="hidden" value="${item.xmlPath}" id="xml${item._id}">
                            <%--<input type="hidden" value="${item.docPath}" id="doc${item.docName}">--%>
                        </div>
                        </c:forEach>
                    </div>
                    <div class="row" style="margin: 2% 0">
                        <span style="color: white;font-size: larger;font-weight: bolder;margin-bottom: 20px">共<span id="PN">${maxPageNum}</span>页</span>&nbsp;

                        <span style="color: white;font-size: larger;font-weight: bolder;margin-bottom: 20px">下载前<span > <input id="downloadPageCount" type="text" style=" color:black;margin:5px 0 0;border:1px solid #006600;width:150px;" ></span> 至<span > <input id="downloadPageCountEnd" type="text" style=" color:black;margin:5px 0 0;border:1px solid #006600;width:150px;" ></span> 页</span>


                        <span style="border:1px solid #006600;border-radius: 2px;background-color: whitesmoke;color: black;cursor: pointer;padding: 0.5%" onclick="downloadSome('doc')">下载doc版</span>
                        <span style="border:1px solid #006600;border-radius: 2px;background-color: whitesmoke;color: black;cursor: pointer;padding: 0.5%" onclick="downloadSome('xml')">下载xml版</span>



                        <span style="border:1px solid #006600;border-radius: 2px;background-color: whitesmoke;color: black;cursor: pointer;padding: 0.5%" onclick="downloadAll('doc')">下载所有doc</span>
                        <span style="border:1px solid #006600;border-radius: 2px;background-color: whitesmoke;color: black;cursor: pointer;padding: 0.5%" onclick="downloadAll('xml')">下载所有xml</span>
                    </div>
                    <nav aria-label="Page navigation">
                        <input type="hidden" id="maxPageNum" value="${maxPageNum}">
                        <input type="hidden" id="currentPageIndex" value="1">
                        <input type="hidden" id="firstPageIndex" value="1">
                        <input type="hidden" id="endPageIndex" value="5">
                        <ul class="pagination" id="PP">
                            <li>
                                <a href="javascript:void(0)" aria-label="Previous" onclick="goPrevious()">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <c:choose>
                                <c:when test="${maxPageNum>5}">
                                    <c:forEach var="i" begin="1" end="5" step="1">
                                        <c:choose>
                                            <c:when test="${i==1}">
                                                <li id="page${i}" class="active"><a href="javascript:void(0)" onclick="goPage('${i}')">${i}</a></li>
                                            </c:when>
                                            <c:otherwise>
                                                <li id="page${i}" class=""><a href="javascript:void(0)" onclick="goPage('${i}')">${i}</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="i" begin="1" end="${maxPageNum}" step="1">
                                        <c:choose>
                                            <c:when test="${i==1}">
                                                <li id="page${i}" class="active"><a href="javascript:void(0)" onclick="goPage('${i}')">${i}</a></li>
                                            </c:when>
                                            <c:otherwise>
                                                <li id="page${i}" class=""><a href="javascript:void(0)" onclick="goPage('${i}')">${i}</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                            <li>
                                <a href="javascript:void(0)" aria-label="Next" onclick="goNext()">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>

                </div>
            </div>
        </div>
    </div>
</div>

<footer class="footer">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 text-center">
                <a href="/">Home</a>
                <a href="/terms" target="_blank">Terms</a>
                <a href="/privacy" target="_blank">Privacy</a>
                <a href="https://twitter.com/wiplohq" target="_blank" rel="nofollow">Twitter</a>
                <a href="mailto:team@wiplo.com?subject=Hi Wiplo Team!" rel="nofollow">Contact</a>
                <small>Architect @ Cristph</small>
            </div>
        </div>
    </div>
</footer>



<script src="js/jquery.min.js"></script>
<script src="js/spin.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/search.js" type="text/javascript" charset="GBK"></script>
<script src="js/jquery.ztree.all.min.js"></script>
<script>

    $('#qwjs').click(function(e){
        e.stopPropagation();
    });

    $('#ay').click(function(e){
        e.stopPropagation();
    });

    $('#treeDemo').click(function(e){
        e.stopPropagation();
    });

    $('#fycj').click(function(e){
        e.stopPropagation();
    });

    $('#wslx').click(function(e){
        e.stopPropagation();
    });

    $('#ajlx').click(function(e){
        e.stopPropagation();
    });

    $('#spcx').click(function(e){
        e.stopPropagation();
    });

    var setting = {
        view: {
            showIcon: showIconForTree,
            addDiyDom: addDiyDom
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback:{
            onClick:zTreeOnClick,
            beforeExpand: beforeExpand
        }
    };

    var nodes =[
//        { id:1, pId:0, name:"按关键字筛选",  isParent:true, open:true},
        { id:2, pId:0, name:"按案由筛选", isParent:true, open:true},
        { id:3, pId:0, name:"按法院层级筛选", isParent:true, open:true},
        { id:4, pId:0, name:"按裁判年份筛选", isParent:true, open:true},
        { id:5, pId:0, name:"按审判程序筛选", isParent:true, open:true},
        { id:6, pId:0, name:"按文书类型筛选", isParent:true, open:true}
    ];

    function showIconForTree(treeId, treeNode) {
        return !treeNode.isParent;
    };


    function addDiyDom(treeId, treeNode) {
        var spaceWidth = 5;
        var switchObj = $("#" + treeNode.tId + "_switch"),
                icoObj = $("#" + treeNode.tId + "_ico");
        switchObj.remove();
        icoObj.before(switchObj);

        if (treeNode.level > 1) {
            var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
            switchObj.before(spaceStr);
        }
        var spantxt=$("#" + treeNode.tId + "_span").html();
        if(spantxt.length>12){
            spantxt=spantxt.substring(0,13)+"...";
            $("#" + treeNode.tId + "_span").html(spantxt);
        }
    }


    function zTreeOnClick(event, treeId, treeNode){
        var parent=treeNode.getParentNode();
        if(treeNode.level==1){
            if(parent.name=='按裁判年份筛选'){
                var cpnf=treeNode.name.split('年')[0];
                addLabel('裁判年份', cpnf, 'cpnf');
                addSearchCondition('cpnf',cpnf);
            }else if(parent.name=='按关键字筛选'){

            }else if(parent.name=='按法院层级筛选'){
                var fy=treeNode.name.split('(')[0];
                var fycj=0;
                if(fy=='最高法院'){
                    fycj=1;
                }else if(fy=='高级法院'){
                    fycj=2;
                }else if(fy=='中级法院'){
                    fycj=3;
                }else if(fy=='基层法院'){
                    fycj=4;
                }
                addLabel('法院层级', fy, 'fycj');
                addSearchCondition('fycj',fycj);
            }else if(parent.name=='按审判程序筛选'){
                var spcx=treeNode.name.split('(')[0];
                addLabel('审判程序', spcx, 'spcx');
                addSearchCondition('spcx',spcx);
            }else if(parent.name=='按文书类型筛选'){
                var wslx=treeNode.name.split('(')[0];
                addLabel('文书类型', wslx, 'wslx');
                addSearchCondition('wslx',wslx);
            }else if(parent.name=='按案由筛选'){
                var ay=treeNode.name.split('(')[0];
                addLabel('案由',ay,'ay');
                addSearchCondition('ay',ay);
            }
        }else if(treeNode.level>=2){
            if(treeNode.getParentNode().getParentNode().name=='按法院层级筛选'){
                addLabel('法院名称', treeNode.name.split('(')[0], 'fymc');
                addSearchCondition('fymc',treeNode.name.split('(')[0]);
            }else if(treeNode.getParentNode().getParentNode().name=='按案由筛选'){
                var ay=treeNode.name.split('(')[0];
                addLabel('案由',ay,'ay');
                addSearchCondition('ay',ay);
            }
        }
    }

    function beforeExpand(treeId, treeNode) {
//        console.log('enode:'+treeNode.children);
//        console.log("[  beforeExpand ]&nbsp;&nbsp;&nbsp;&nbsp;" + treeNode.name );
        parent=treeNode.getParentNode();
//        if(parent!=null){
        if(treeNode.level==1){
            var whereValue=treeNode.name.split('(')[0];
            var parentId=parent.id;
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            if(parent.name=='按案由筛选'){
//                console.log('do with ay');
//                console.log('node:'+treeNode.children);
                if(treeNode.children==null||treeNode.children.length==0){
                    var t=$.post(
                            "/groupStatistics",
                            {
                                "groupName": "EJAYMC",
                                "viewName": $('#viewName').val(),
                                "whereName": "YJAYMC",
                                "whereValue": encodeURIComponent(whereValue)
                            },
                            function(map){
                                var i=1;
                                for(var key in map){
                                    var value=map[key];
                                    var entry={id:parentId*100+i, pId:treeNode.id, name:key+"("+value+")", isParent:true};
                                    zTree.addNodes(treeNode,entry);
//                                    console.log(parentId*100+i);
//                                    console.log(treeNode.id);
//                                    console.log(key+"("+value+")");
                                    i++;
                                }
                            }
                    );
                    t.done(function(){
                        zTree.updateNode(treeNode);
//                        console.log("up")
                    });
                }

            }else if(parent.name=='按法院层级筛选'){
//                console.log('do with fycj');
//                console.log('node:'+treeNode.children);
                if(treeNode.children==null||treeNode.children.length==0){
                    var current=treeNode.name.split('(')[0];
                    var cj=0;
                    var groupName='';
                    if(current=='法院不明'){
                        cj=0;
                    }else if(current=='最高法院'){
                        cj=1;
                        groupName="GYMC";
                    }else if(current=='高级法院'){
                        cj=2;
                        groupName="GYMC";
                    }else if(current=='中级法院'){
                        cj=3;
                        groupName="ZYMC";
                    }else if(current=='基层法院'){
                        cj=4;
                        groupName="JCYMC";
                    }
                    var s=$.post(
                            "/groupStatistics",
                            {
                                "groupName": groupName,
                                "viewName": $('#viewName').val(),
                                "whereName": "FYCJ",
                                "whereValue": cj
                            },
                            function(map){
                                var i=1;
                                for(var key in map){
                                    var value=map[key];
                                    var entry={id:parentId*100+i, pId:treeNode.id, name:key+"("+value+")", isParent:true};
                                    zTree.addNodes(treeNode,entry);
//                                    console.log(parentId*100+i);
//                                    console.log(treeNode.id);
//                                    console.log(key+"("+value+")");
                                    i++;
                                }
                            }
                    );
                    s.done(function(){
                        zTree.updateNode(treeNode);
//                        console.log("up")
                    });
                }
            }
        }else if(treeNode.level==2){
//            alert("level2");
            var whereValue=treeNode.name.split('(')[0];
            var parentId=parent.id;
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            if(treeNode.name.indexOf('法院')<0){
//                console.log('do with ay');
//                console.log('node:'+treeNode.children);
                if(treeNode.children==null||treeNode.children.length==0){
                    var t=$.post(
                            "/groupStatistics",
                            {
                                "groupName": "SJAYMC",
                                "viewName": $('#viewName').val(),
                                "whereName": "EJAYMC",
                                "whereValue": encodeURIComponent(whereValue)
                            },
                            function(map){
                                var i=1;
                                for(var key in map){
                                    var value=map[key];
                                    var entry={id:parentId*1000+i, pId:treeNode.id, name:key+"("+value+")", isParent:true};
                                    zTree.addNodes(treeNode,entry);
//                                    console.log(parentId*1000+i);
//                                    console.log(treeNode.id);
//                                    console.log(key+"("+value+")");
                                    i++;
                                }
                            }
                    );
                    t.done(function(){
                        zTree.updateNode(treeNode);
//                        console.log("up")
                    });
                }

            }else {
//                console.log('do with fycj');
//                console.log('node:'+treeNode.children);
                if(treeNode.children==null||treeNode.children.length==0){
                    var current=parent.name.split('(')[0];
                    var groupName='';
                    var whereName="";
                    if(current=='法院不明'){

                    }else if(current=='最高法院'){
                        groupName="ZYMC";
                        whereName="GYMC";
                    }else if(current=='高级法院'){
                        groupName="ZYMC";
                        whereName="GYMC";
                    }else if(current=='中级法院'){
                        groupName="JCYMC";
                        whereName="ZYMC";
                    }else if(current=='基层法院'){
                        groupName="";
                        whereName="";
                    }
                    var s=$.post(
                            "/groupStatistics",
                            {
                                "groupName": groupName,
                                "viewName": $('#viewName').val(),
                                "whereName": whereName,
                                "whereValue": encodeURIComponent(treeNode.name.split('(')[0])
                            },
                            function(map){
                                var i=1;
                                for(var key in map){
                                    var value=map[key];
                                    var entry={id:parentId*1000+i, pId:treeNode.id, name:key+"("+value+")", isParent:true};
                                    zTree.addNodes(treeNode,entry);
//                                    console.log(parentId*1000+i);
//                                    console.log(treeNode.id);
//                                    console.log(key+"("+value+")");
                                    i++;
                                }
                            }
                    );
                    s.done(function(){
                        zTree.updateNode(treeNode);
//                        console.log("up")
                    });
                }
            }
        }else if(treeNode.level==3){
//            alert("level3");
            var whereValue=treeNode.name.split('(')[0];
            var parentId=parent.id;
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            if(treeNode.name.indexOf('法院')<0){
//                console.log('do with ay');
//                console.log('node:'+treeNode.children);
                if(treeNode.children==null||treeNode.children.length==0){
                    var t=$.post(
                            "/groupStatistics",
                            {
                                "groupName": "SiJAYMC",
                                "viewName": $('#viewName').val(),
                                "whereName": "SJAYMC",
                                "whereValue": encodeURIComponent(whereValue)
                            },
                            function(map){
                                var i=1;
                                for(var key in map){
                                    var value=map[key];
                                    var entry={id:parentId*10000+i, pId:treeNode.id, name:key+"("+value+")", isParent:true};
                                    zTree.addNodes(treeNode,entry);
//                                    console.log(parentId*10000+i);
//                                    console.log(treeNode.id);
//                                    console.log(key+"("+value+")");
                                    i++;
                                }
                            }
                    );
                    t.done(function(){
                        zTree.updateNode(treeNode);
//                        console.log("up")
                    });
                }

            }else {
//                console.log('do with fycj');
//                console.log('node:'+treeNode.children);
                if(treeNode.children==null||treeNode.children.length==0){
                    var current=parent.getParentNode().name.split('(')[0];
                    var groupName='';
                    var whereName="";
                    if(current=='法院不明'){

                    }else if(current=='最高法院'){
                        groupName="JCYMC";
                        whereName="ZYMC";
                    }else if(current=='高级法院'){
                        groupName="JCYMC";
                        whereName="ZYMC";
                    }else if(current=='中级法院'){
                        groupName="";
                        whereName="";
                    }else if(current=='基层法院'){
                        groupName="";
                        whereName="";
                    }
                    var s=$.post(
                            "/groupStatistics",
                            {
                                "groupName": groupName,
                                "viewName": $('#viewName').val(),
                                "whereName": whereName,
                                "whereValue": encodeURIComponent(treeNode.name.split('(')[0])
                            },
                            function(map){
                                var i=1;
                                for(var key in map){
                                    var value=map[key];
                                    var entry={id:parentId*10000+i, pId:treeNode.id, name:key+"("+value+")", isParent:true};
                                    zTree.addNodes(treeNode,entry);
//                                    console.log(parentId*10000+i);
//                                    console.log(treeNode.id);
//                                    console.log(key+"("+value+")");
                                    i++;
                                }
                            }
                    );
                    s.done(function(){
                        zTree.updateNode(treeNode);
//                        console.log("up")
                    });
                }
            }
        }
        return (treeNode.expand !== false);
    }

    function addLabel(conditionName, conditionValue, conditionEngName){
        var span=document.createElement('span');
        span.classList.add('label');
        span.classList.add('label-primary');
        span.innerHTML=conditionName+":"+conditionValue+" <span class=\"glyphicon glyphicon-remove scondition\" aria-hidden=\"true\" onclick=\"removeLabel('"+conditionEngName+"')\"></span>";
        document.getElementById('condBox').appendChild(span);
    }


    function addSearchCondition(conditionName, conditionValue){
        $('#cond_'+conditionName).val(conditionValue);
//        var sorts=[];
//        var orders=[];
//        sorts.push('fycj');
//        orders.push($('#fycjOrder').val());
//        sorts.push('cprq');
//        orders.push($('#cprqOrder').val());
//        sorts.push('spcx');
//        orders.push($('#spcxOrder').val());
//        showPage(sorts,orders,1);
//        resetNum();

        var qwjs=$('#cond_qwjs').val();
        var qwjsInput=$('#cond_qwjsInput').val();
        var ay=$('#cond_ay').val();
        var ah=$('#cond_ah').val();
        var ajmc=$('#cond_ajmc').val();
        var fymc=$('#cond_fymc').val();
        var fycj=$('#cond_fycj').val();
        var ajlx=$('#cond_ajlx').val();
        var spcx=$('#cond_spcx').val();
        var wslx=$('#cond_wslx').val();
        var cprqbegin=$('#cond_cprqbegin').val();
        var cprqend=$('#cond_cprqend').val();
        var cpry=$('#cond_cpry').val();
        var dsr=$('#cond_dsr').val();
        var flyj=$('#cond_flyj').val();
        var cpnf=$('#cond_cpnf').val();

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
        url=addURLParam(url,"flyj",flyj);
        url=addURLParam(url,"cpnf",cpnf);
        location.href=url;
//        updateView();
    }

    function updateView(){
        var p1=new Promise(function (resolve){
            $.post(
                    "/groupStatistics",
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
                        "groupName": "AY",
                        "whereName": "",
                        "whereValue": ""
                    },
                    function(map){
                        var i=1;
                        for(var key in map){
                            var value=map[key];
//                            var entry={id:2*10+i, pId:2, name:key+"("+value+")", isParent:true};
                            if(key==''){
                                var entry={id:2*10+i, pId:2, name:"案由不明("+value+")"};
                                nodes.push(entry);
                            }else{
                                var entry={id:2*10+i, pId:2, name:key+"("+value+")"};
                                nodes.push(entry);
                            }
//                            var entry={id:2*10+i, pId:2, name:key+"("+value+")"};
//                            nodes.push(entry);
                            i++;
                        }
                        resolve('p1 done');
                    }
            );
        });

        var p2=new Promise(function (resolve){
            $.post(
                    "/groupStatistics",
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
                        "groupName": "FYCJ",
                        "whereName": "",
                        "whereValue": ""
                    },
                    function(map){
                        var i=1;
                        var fycjArray=["法院不明","最高法院","高级法院","中级法院","基层法院"];
                        for(var key in map){
                            var value=map[key];
//                            var entry={id:3*10+i, pId:3, name:fycjArray[key]+"("+value+")", isParent:true};
                            var entry={id:3*10+i, pId:3, name:fycjArray[key]+"("+value+")"};
                            nodes.push(entry);
                            i++;
                        }
                        resolve("p2 done");
                    }
            );
        });

        var p3=new Promise(function (resolve){
            $.post(
                    "/groupStatistics",
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
                        "groupName": "CPNF",
                        "whereName": "",
                        "whereValue": ""
                    },
                    function(map){
                        var i=1;
                        for(var key in map){
                            var value=map[key];
                            var entry={id:4*10+i, pId:4, name:key+"年("+value+")"};
                            nodes.push(entry);
                            i++;
                        }
                        resolve('p3 done');
                    }
            );
        });

        var p4=new Promise(function (resolve){
            $.post(
                    "/groupStatistics",
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
                        "groupName": "SPCX",
                        "whereName": "",
                        "whereValue": ""
                    },
                    function(map){
                        var i=1;
                        for(var key in map){
                            var value=map[key];
                            var entry={id:5*10+i, pId:5, name:key+"("+value+")"};
                            nodes.push(entry);
                            i++;
                        }
                        resolve('p4 done');
                    }
            );
        });

        var p5=new Promise(function (resolve){
            $.post(
                    "/groupStatistics",
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
                        "groupName": "WSLX",
                        "whereName": "",
                        "whereValue": ""
                    },
                    function(map){
                        var i=1;
                        for(var key in map){
                            var value=map[key];
                            var entry={id:6*10+i, pId:6, name:key+"("+value+")"};
                            nodes.push(entry);
                            i++;
                        }
                        resolve('p5 done');
                    }
            );
        });

        Promise.all([p1,p2,p3,p4,p5]).then(function(result){
            $.fn.zTree.init($("#treeDemo"), setting, nodes);
        });
    }

    $(function(){
        $.fn.zTree.init($("#treeDemo"), setting, nodes);
        updateView();
    });

    $('#searchBtn').click(function(){
        var qwjsInput="";var qwjs="";var ay="";var ah="";
        var ajmc="";var fymc="";var fycj="";var ajlx="";
        var spcx="";var wslx="";var cprqbegin="";var cprqend="";
        var cpry="";var dsr="";var flyj="";var cpnf="";

        var input=$('#searchInput').val();
        input= $.trim(input);
        var c=input.charAt(input.length-1);
        if(c==','||c=='，'||c==';'||c=='；'){
            input=input.substr(0,input.length-1);
        }
        var array=input.split(/[;,；，]/);
        for(var i=0;i<array.length;i++){
            console.log(array[i]+"====");
            if(array[i].length>0){
                var tupe=array[i].split(/[:：]/);
                if(tupe[0]=="全文"){
                    qwjs="qw";
                    qwjsInput=tupe[1];
                }else if(tupe[0]=="文首"){
                    qwjs="ws";
                    qwjsInput=tupe[1];
                }else if(tupe[0]=="事实"){
                    qwjs="ajjbqk";
                    qwjsInput=tupe[1];
                }else if(tupe[0]=="理由"){
                    qwjs="cpfxgc";
                    qwjsInput=tupe[1];
                }else if(tupe[0]=="判决结果"){
                    qwjs="pjjg";
                    qwjsInput=tupe[1];
                }else if(tupe[0]=="文尾"){
                    qwjs="ww";
                    qwjsInput=tupe[1];
                }
                else if(tupe[0]=="案由"){
                    ay=tupe[1];
                }else if(tupe[0]=="案件名称"){
                    ajmc=tupe[1];
                }else if(tupe[0]=="案号"){
                    ah=tupe[1];
                }else if(tupe[0]=="法院名称"){
                    fymc=tupe[1];
                }else if(tupe[0]=="法院层级"){
                    fycj=tupe[1];
                }else if(tupe[0]=="案件类型"){
                    ajlx=tupe[1];
                }else if(tupe[0]=="审判程序"){
                    spcx=tupe[1];
                }else if(tupe[0]=="文书类型"){
                    wslx=tupe[1];
                }else if(tupe[0]=="裁判人员"){
                    cpry=tupe[1];
                }else if(tupe[0]=="当事人"){
                    dsr=tupe[1];
                }else if(tupe[0]=="裁判年份"){
                    cpnf=tupe[1];
                }else if(tupe[0]=="法律依据"){
                    flyj=tupe[1];
                }
            }else{
                alert("检测到输入错误！请重新输入");
                return;
            }
        }

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
        url=addURLParam(url,"flyj",flyj);
        url=addURLParam(url,"cpnf",cpnf);
        location.href=url;
    });

    $(document).keyup(function(event){
        if(event.keyCode==13){
            var qwjsInput="";var qwjs="";var ay="";var ah="";
            var ajmc="";var fymc="";var fycj="";var ajlx="";
            var spcx="";var wslx="";var cprqbegin="";var cprqend="";
            var cpry="";var dsr="";var flyj="";var cpnf="";

            var input=$('#searchInput').val();
            input= $.trim(input);
            var c=input.charAt(input.length-1);
            if(c==','||c=='，'||c==';'||c=='；'){
                input=input.substr(0,input.length-1);
            }
            var array=input.split(/[;,；，]/);
            for(var i=0;i<array.length;i++){
                console.log(array[i]+"====");
                if(array[i].length>0){
                    var tupe=array[i].split(/[:：]/);
                    if(tupe[0]=="全文"){
                        qwjs="qw";
                        qwjsInput=tupe[1];
                    }else if(tupe[0]=="文首"){
                        qwjs="ws";
                        qwjsInput=tupe[1];
                    }else if(tupe[0]=="事实"){
                        qwjs="ajjbqk";
                        qwjsInput=tupe[1];
                    }else if(tupe[0]=="理由"){
                        qwjs="cpfxgc";
                        qwjsInput=tupe[1];
                    }else if(tupe[0]=="判决结果"){
                        qwjs="pjjg";
                        qwjsInput=tupe[1];
                    }else if(tupe[0]=="文尾"){
                        qwjs="ww";
                        qwjsInput=tupe[1];
                    }
                    else if(tupe[0]=="案由"){
                        ay=tupe[1];
                    }else if(tupe[0]=="案件名称"){
                        ajmc=tupe[1];
                    }else if(tupe[0]=="案号"){
                        ah=tupe[1];
                    }else if(tupe[0]=="法院名称"){
                        fymc=tupe[1];
                    }else if(tupe[0]=="法院层级"){
                        fycj=tupe[1];
                    }else if(tupe[0]=="案件类型"){
                        ajlx=tupe[1];
                    }else if(tupe[0]=="审判程序"){
                        spcx=tupe[1];
                    }else if(tupe[0]=="文书类型"){
                        wslx=tupe[1];
                    }else if(tupe[0]=="裁判人员"){
                        cpry=tupe[1];
                    }else if(tupe[0]=="当事人"){
                        dsr=tupe[1];
                    }else if(tupe[0]=="裁判年份"){
                        cpnf=tupe[1];
                    }else if(tupe[0]=="法律依据"){
                        flyj=tupe[1];
                    }
                }else{
                    alert("检测到输入错误！请重新输入");
                    return;
                }
            }

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
            url=addURLParam(url,"flyj",flyj);
            url=addURLParam(url,"cpnf",cpnf);
            location.href=url;
        }
    });

</script>

</body>
