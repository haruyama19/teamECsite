<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %>

<!DOCTYPE html>

<html>

<head>

	<meta charset = "UTF-8"/>

	<link rel = "stylesheet" href = "css/style_main.css">
	<link rel = "stylesheet" href = "css/style_productList.css">

	<title>商品一覧画面</title>

</head>

<body>

	<!-- 共通ヘッダーの挿入 -->
	<jsp:include page = "header.jsp" />

	<h1>商品一覧画面</h1>

	<!-- 	商品一覧表示・検索結果表示それぞれで参照するDTOが異なるため、参照すべきDTOを検知 -->
	<div class = "main">

		<s:if test = "productInfoDTOList != null && productInfoDTOList.size() == 0 && errorList.size() == 0">
			<div class = "message">検索結果がありません。</div>
		</s:if>

		<s:elseif test = "errorList != null && errorList.size() != 0">
			<div class = "message">
				<s:iterator value = "errorList">
					<p><s:property /></p>
				</s:iterator>
			</div>
		</s:elseif>

		<s:else>
			<s:iterator value = "productInfoDTOList">
				<div class = "box">
					<a href = '<s:url action = "ProductDetailsAction"/>?productId=<s:property value = "productId" />'>
						<img src = '<s:property value = "imageFilePath"/><s:property value = "imageFileName"/>.jpg' class = "pic"/>
						<span><s:property value = "productName"/></span>
						<span><s:property value = "productNameKana"/></span>
						<span><s:property value = "price"/>円</span>
					</a>
				</div>
			</s:iterator>
		</s:else>

	</div>

</body>

</html>
