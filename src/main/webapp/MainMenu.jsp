<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ja" class="h-100 overflow-y-scroll">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>《メインメニュー》 KIDDA-LA 業務システム</title>
<link rel="icon" href="css/favicon.ico">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/KIDDA-LA.css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>

<body class="d-flex h-100 text-center text-dark">
	<div class="position-fixed top-0 end-0 m-3">
		<form action="KiddaLaController" method="post" class="d-inline">
			<input type="hidden" name="command" value="LoginDisplay">
			<button class="btn btn-primary me-2">ログイン</button>
		</form>
	
		<form action="KiddaLaController" method="post" class="d-inline">
			<input type="hidden" name="command" value="RegisterDisplay">
			<button class="btn btn-warning">新規登録</button>
		</form>
	</div>
	<div class="d-flex w-100 mx-auto flex-column">
		<header class="mb-auto">
			<p class="fs-1 mt-2 mb-1">KIDDA-LA 業務システム</p>
			<p class="fs-3 mt-1 mb-2">《メインメニュー》</p>
		</header>

		<main class="container">
		<div class="d-flex flex-column w-100">
			<div class="row mb-5 justify-content-center">
				<div class="col-4 justify-content-center">
					<button class="btn btn-success rounded-pill btn-lg fs-3 m-1 text-white w-100 mb-4" type="submit" form="order_fm">01　注文管理</button>
					<button class="btn btn-primary rounded-pill btn-lg fs-3 m-1 text-white w-100 mb-4" type="submit" form="customer_fm">02　顧客管理</button>
					<button class="btn btn-dark rounded-pill btn-lg fs-3 m-1 text-white w-100" type="submit" form="product_fm">03　商品管理</button>
				</div>
			</div>
		</div>
		</main>

		<footer class="mt-auto" id="ftr">
			<p>&copy;Infotech Serve Inc.</p>
		</footer>
	</div>
	<form action="KiddaLaController" method="post" id="order_fm">
		<input type="hidden" name="command" value="CustomerSearchDisplay">
	</form>
	<form action="KiddaLaController" method="post" id="customer_fm">
		<input type="hidden" name="command" value="CustomerControlDisplay">
	</form>
	<form action="KiddaLaController" method="post" id="product_fm">
		<input type="hidden" name="command" value="ItemControlDisplay">
	</form>
</body>
</html>