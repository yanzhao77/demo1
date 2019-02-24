<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/inc/head.jsp" %>
<div class="mbxnav">
	<a href="/main.action">代理商管理</a> \ <a href="/customlist.action">代理商客户管理</a>
</div>
<div class="container">
	
		<form action="/customlist.action" method="post" >
             <div>
	            <label>客户名称:</label>
	            <input type="text" id="cname" name="cname" value="${cname }"/>
	            <input type="submit" value="查询" /> 
              </div>
        </form>
        
        <div class="addCustomDiv">
        <input onclick="javascript:location.href='/addcustom.action';" type="button" value="添加客户" />
        </div>
		<c:if test="${customList != null }">
            <table>
            <thead>
            <tr>
	     	<th>序号</th>
	     	<th>客户名称</th>
	     	<th>法人代表</th>
	     	<th>注册时间</th>
	     	<th>类型</th>
	     	<th>状态</th>
	     	<th>操作</th>
  			</tr>
            </thead>
            <c:forEach items="customList" varStatus="sta">
            	<tr class="tabletr">
            		<td>
            		<c:choose>          		
            		<c:when test="${1 >= page.page }">${sta.index+1 }</c:when>
					<c:otherwise>
				<%-- 	${(page.page-1) * page.pageSize+#sta.index+1 } --%>
					</c:otherwise>
					</c:choose>
            		</td>
                    <td>${customName }</td>
                    <td>${bossName }</td>
                    <td>${regDatetime }</td>
                    <td>${customTypeName }</td>
                    <td>
                    <span id="m_status${sta.index+1 }">
                    <c:choose>
                    <c:when test="${customStatus == 1 }"><font color="green">启用</font></c:when>
                    <c:otherwise><font color="red">停用</font></c:otherwise></c:choose>
                    </span>
                    </td>
                    <td class="funcli">
                    	<ul>
                     		<li><a class="viewCustom" id="${id }">查看</a></li>
                     		<li><a class="modifyCustom" id="${id }" cname="${cname }">修改</a></li>
                     		<li>
                     		<c:choose>
                     		 <c:when test="${customStatus == 1 }">
                     		 <a class="modifyCustomStatus" id="${id }" mStatus="${sta.index+1 }" customStatus="${customStatus }" customName="${customName }"><font color="red">停用</font></a>
                     		 </c:when>
                    		 <c:otherwise>
							 <a class="modifyCustomStatus" id="${id }" mStatus="${sta.index+1 }" customStatus="${customStatus }" customName="${customName }"><font color="green">启用</font></a>
							</c:otherwise>
							</c:choose>
                     		</li>
                     	</ul>
					</td>
                 </tr> 
              </c:forEach>
              </table>
              <div class="pagination pagination-centered">
						  <ul>
							<li><a href="/customlist.action?page.page=1&cname=${cname }" title="首页">首页</a></li>
							<c:if test="${page.prevPages!=null }">
								<c:forEach items="${page.prevPages }" var="num">
									<li><a
										href="/customlist.action?page.page=${num}&cname=${cname}"
										class="number" title="${num }">${num }</a></li>
								</c:forEach>
							</c:if>
							<li class="active">
							  <a href="#"title="${page.page}">page.page </a>
							</li>
							<c:if test="${page.nextPages!=nulls }">
								<c:forEach items="page.nextPages" var="num">
									<li><a href="/customlist.action?page.page=${num}&cname=${cname}" title="${num }">
									${num }</a></li>
								</c:forEach>
							</c:if>
							<li><a href="/customlist.action?page.page=${page.pageCount}&cname=${cname}" title="尾页">尾页</a></li>
						  </ul>
						</div>
       </c:if>
	</div>
<link id='theme' rel='stylesheet' href='/css/customlist.css'/>
<script type="text/javascript" src="/js/customlist.js" charset="UTF-8"></script> 
<jsp:include page="/inc/foot.jsp"></jsp:include>
</body>
</html>

