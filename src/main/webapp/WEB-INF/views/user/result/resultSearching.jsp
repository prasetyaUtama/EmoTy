<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<link rel="stylesheet"
	href="<c:url value="/resourcesUtils/css/user/hasil_pencarian.css"/>"
	media="screen">




	<div id="panels">
		<div class="panel">
			<c:if test="${not empty images}">
				<c:forEach var="image" items="${images}">
					<img src="data:image/png;base64,${image.getImage()}" alt="my image"
						/>
				</c:forEach>
			</c:if>
		</div>
		<!-- /.panel -->

	</div>
