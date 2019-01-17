<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cristph
  Date: 2017/3/30
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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