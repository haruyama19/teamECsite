<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %>

<!DOCTYPE html>

<html>

<head>

	<meta charset = "UTF-8"/>

	<link rel = "stylesheet" href = "css/style_main.css">
	<link rel = "stylesheet" href = "css/style_productDetails.css">

	<title>商品詳細画面</title>

</head>
<body>
<!-- 		共通ヘッダーの挿入 -->
	<jsp:include page = "header.jsp" />

	<h1>商品詳細画面</h1>

	<s:if test = "productInfoDTO != null">

	<img src = '<s:property value="productInfoDTO.imageFilePath"/><s:property value = "productInfoDTO.imageFileName"/>.jpg' class = "pic"/>

		<form action = "AddCartAction" method = "post">
			<table class = "table">
				<tr>
					<th><label>商品名</label></th>
					<td><s:property value="productInfoDTO.productName"/></td>
				</tr>

				<tr>
					<th><label>商品名ふりがな</label></th>
					<td><s:property value = "productInfoDTO.productNameKana"/></td>
				</tr>

				<tr>
					<th><label>値段</label></th>
					<td><s:property value = "productInfoDTO.price"/>円</td>
				</tr>

				<tr>
					<th><label>購入個数</label></th>
					<td><select name = "productCount">
							<option value = "1" selected = "selected">1</option>
							<option value = "2">2</option>
							<option value = "3">3</option>
							<option value = "4">4</option>
							<option value = "5">5</option>
						</select>
						個
					</td>
				</tr>

				<tr>
					<th><label>発売会社名</label></th>
					<td><s:property value = "productInfoDTO.releaseCompany"/></td>
				</tr>

				<tr>
					<th><label>発売年月日</label></th>
					<td><s:property value = "productInfoDTO.releaseDate"/></td>
				</tr>

				<tr>
					<th><label>商品詳細情報</label></th>
					<td><s:property value = "productInfoDTO.productDescription"/></td>
				</tr>
			</table>

			<div class = "button">
				<s:hidden name = "productId" value = "%{productInfoDTO.productId}"/>
				<s:submit value = "カートに追加"/>
			</div>
		</form>

<!-- 関連商品 -->

		<div>
		<s:if test = "relatedProductList != null && relatedProductList.size() > 0">

		<h2>関連商品</h2>
			<s:iterator value = "relatedProductList"><div class = "related">
				<a href = '<s:url action = "ProductDetailsAction">
					<s:param name = "productId" value="%{productId}"/></s:url>'>
					<img src = '<s:property value = "imageFilePath"/><s:property value = "imageFileName"/>.jpg' class = "pic2"/>
					<s:property value = "productName"/>
				</a></div>
			</s:iterator>
		</s:if>
		</div>

	</s:if>

	<s:else>
		<div class = "message">商品の詳細情報がありません。</div>
	</s:else>

</body>

</html>
