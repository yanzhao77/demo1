<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/inc/head.jsp"%>
<div class="mbxnav">
	<a href="/main.action">代理商管理</a> \ <a href="/keywordmanage.action">关键词申请管理</a>
</div>
<div class="container">


	<div class="searchuserdiv ope">
		<ul>
			<li>
				<form action="/keywordmanage.action" method="post">
					关键词：<input type="text" value="${keywords.keywords }"
						name="keywords.keywords" /> <input type="submit" value="查询" />
				</form>
			</li>
		</ul>
	</div>


	<table>
		<thead>
			<tr>
				<th>序号</th>
				<th>关键词</th>
				<th>客户名称</th>
				<th>代理商</th>
				<th>申请年限</th>
				<th>申请日期</th>
				<th>到期日期</th>
				<th>申请到期状态</th>
				<th>审核状态</th>
				<th>使用状态</th>
				<th>APP开通状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${page.items!=null }">
				<c:forEach items="${page.items }" varStatus="adIndex" var="keyword">
					<tr class="tabletr">
						<td><c:choose>

								<c:when test="${1 >= page.page }">${adIndex.index+1 }</c:when>
								<c:otherwise>
			<%-- ${(page.page-1) * page.pageSize+#adIndex.index+1 } --%>
			</c:otherwise>
							</c:choose></td>
						<td>${keyword.keywords }</td>
						<td>${keyword.customName }</td>
						<td>${keyword.agentName }</td>
						<td>${keyword.serviceYears }</td>
						<td>${keyword.regDatetime }</td>
						<td>${keyword.regPassDatetime }</td>
						<td>
						<c:choose>
								<c:when test="${keyword.isPass == 0 }">
									<font color="green">未过期</font>
								</c:when>
								<c:when test="${keyword.isPass == 1 }">
									<font color="red">预注册过期</font>
								</c:when>
								<c:when test="${keyword.isPass == 2 }">
									<font color="red">过期</font>
								</c:when>
							</c:choose></td>
						
						<td><c:choose>
								<c:when test="${keyword.checkStatus == 0 }">
									<font color="green">已申请</font>
								</c:when>
								<c:when test="${keyword.checkStatus == 1 }">
									<font color="green">审核中</font>
								</c:when>
								<c:when test="${keyword.checkStatus == 2 }">
									<font color="green">已通过</font>
								</c:when>
								<c:when test="${keyword.checkStatus == 3 }">
									<font color="red">未通过</font>
								</c:when>
							</c:choose></td>
								
						<td><c:choose>
								<c:when test="${keyword.isUse == 0 }">
									<font color="red">未使用</font>
								</c:when>
								<c:when test="${keyword.isUse == 1 }">
									<font color="green">已使用</font>
								</c:when>
							</c:choose></td>
							 
						<td><c:choose>
								<c:when test="${keyword.openApp != 1 }">
									<font color="red">未开通</font>
								</c:when>
								<c:when test="${keyword.openApp == 1 }">
									<font color="green">已开通</font>
								</c:when>
							</c:choose></td>
							
						<%--  <td><c:choose>
								<c:when test="${keyword.isUse == 1 } ">
									<c:when test="${keyword.checkStatus == 2 }">
										<c:when test="${keyword.openApp != 1 }">
											<a class="openapp" kid="${keyword.id }"
												keyword="${keyword.keywords }">开通APP</a>
										</c:when>

										<a class="xufei" kid="${keyword.id }"
											keyword="${keyword.keywords }">续费</a>
									</c:when>
									
									<c:when test="${keyword.isPass == 2 }">
										<a class="xufei" kid="${keyword.id }"
											keyword="${keyword.keywords }">续费</a>
										<a class="deletekeyword" kid="${keyword.id }"
											keyword="${keyword.keywords }">删除</a>
									</c:when>
									<c:when test="#keyword.checkStatus == 3">
										<a class="deletekeyword"
											kid="${keyword.id }"
											keyword="${keyword.keywords }">删除</a>
									</c:when>
									<c:otherwise>
										<font color="#ccc">无操作</font>
									</c:otherwise></c:when>
									
							</c:choose>  --%>
							 
							<%--  <c:choose>
								<c:otherwise>
									<font color="#ccc">无操作</font>
								</c:otherwise>
							</c:choose></td> --%>
							<td>
				<c:if test="${keyword.isUse == 1 } ">
				<c:if test="${keyword.checkStatus == 2 }">
				<c:if test="${keyword.openApp != 1 }">
				<a class="openapp" kid="${keyword.id }" 
				keyword="${keyword.keywords }">开通APP</a>
				</c:if>
					
					<a class="xufei" kid="${keyword.id }"
					 keyword="${keyword.keywords }">续费</a>
				</c:if>
				<c:if test="${keyword.isUse == 2 } ">
				 <a class="xufei" kid="${keyword.id }>"
					 keyword="${keyword.keywords }">续费</a>
					<a class="deletekeyword"  kid="${keyword.id }" 
					keyword="${keyword.keywords }>">删除</a>
				</c:if>
				<c:if test="${keyword.isUse == 3 } ">
					<a class="deletekeyword" kid="${keyword.id }"
					 keyword="${keyword.keywords }">删除</a>
			  </c:if>
				<c:if test="${keyword.isUse != 3 } &&${keyword.isUse != 2 }&&${keyword.isUse != 1 }">
					<font color="#ccc">无操作</font>
				</c:if>
				</c:if>
				
				
				
			</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>

	<div class="pagination pagination-centered">
		<ul>
			<li><a
				href="/keywordmanage.action?page.page=1&keywords.keywords=${keywords.keywords }"
				title="首页">首页</a></li>
			<c:if test="${page.prevPages!=null }">
				<c:forEach items="${page.prevPages }" var="num">
					<li><a
						href="/keywordmanage.action?page.page=${num}&keywords.keywords=${keywords.keywords}"
						class="number" title="${num }">${num }</a></li>
				</c:forEach>
			</c:if>
			<li class="active"><a href="#"
				title="${page.page }">${page.page }</a></li>
			<c:if test="page.nextPages!=null">
				<c:forEach items="page.nextPages" var="num">
					<li><a
						href="/keywordmanage.action?page.page=${num}&keywords.keywords=${keywords.keywords}"
						title="${num }"> ${num }
					</a></li>
				</c:forEach>
			</c:if>
			<li><a
				href="/keywordmanage.action?page.page=${page.pageCount}&keywords.keywords=${keywords.keywords}"
				title="尾页">尾页</a></li>
		</ul>
	</div>
</div>
<link id='theme' rel='stylesheet' href='/css/keywordmanage.css' />
<script type="text/javascript" src="/js/keywordmanage.js"
	charset="UTF-8"></script>
<jsp:include page="/inc/foot.jsp"></jsp:include>
</body>
</html>