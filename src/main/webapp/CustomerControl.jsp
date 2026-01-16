<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="Error.jsp" %>
<%@ page import="model.Customer" %>
<%
	Customer cust = (Customer)request.getAttribute("customer");
	@SuppressWarnings("unchecked")
	String[][] customerData = (String[][]) request.getAttribute("customerData");
	String msgFlag = (String)request.getAttribute("msgFlag");
%>
<!DOCTYPE html>
<html lang="ja" class="overflow-y-scroll">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>《顧客管理》 KIDDA-LA 業務システム</title>
	<link rel="icon" href="css/favicon.ico">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/KIDDA-LA.css">
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript">

	function showModalConfirm(title, message, kind) {
		modal = new bootstrap.Modal(document.getElementById('modalConfirm'));
		if(kind == "error") {
			image = "css/Error.png";
			bgColor = "modal-header bg-danger-subtle";
		} else if(kind == "warning") {
			image = "css/Warning.png";
			bgColor = "modal-header bg-warning-subtle";
		} else {
			image = "css/Info.png";
			bgColor = "modal-header bg-success-subtle";
		}
		document.getElementById('modalConfirm_header').setAttribute('class', bgColor);
		document.getElementById('modalConfirm_image').src = image;
		document.getElementById('modalConfirm_title').innerHTML = title;
		document.getElementById('modalConfirm_message').innerHTML = message;
		modal.show();
		document.getElementById('body').style.paddingRight = '0px';
		document.getElementById('footer').style.paddingRight = '0px';
        document.getElementById('modalConfirm').style.paddingRight = '0px';
	}

	function showModalCustConfirm(custId, custName, kana, tel, address) {
		modal = new bootstrap.Modal(document.getElementById('modalCustConfirm'));
		document.getElementById('modalCustConfirm_custId').value = custId;
		document.getElementById('modalCustConfirm_custName').value = custName;
		document.getElementById('modalCustConfirm_kana').value = kana;
		document.getElementById('modalCustConfirm_tel').value = tel;
		document.getElementById('modalCustConfirm_address').value = address;
		modal.show();
		document.getElementById('body').style.paddingRight = '0px';
		document.getElementById('footer').style.paddingRight = '0px';
        document.getElementById('modalCustConfirm').style.paddingRight = '0px';
	}

	function checkCustSubmit() {
		if(document.getElementById('custName').value == ""
			|| document.getElementById('kana').value == ""
			|| document.getElementById('tel').value == ""
			|| document.getElementById('address').value == "") {
		} else {
			custId = document.getElementById('custId').value;
			custName = document.getElementById('custName').value;
			kana = document.getElementById('kana').value;
			tel = document.getElementById('tel').value;
			address = document.getElementById('address').value;
			showModalCustConfirm(custId, custName, kana, tel, address);
		}
		return false;
	}

	function adjustFooter() {
		clientHeight = document.documentElement.clientHeight;
		offsetHeight = document.documentElement.offsetHeight;
		if(clientHeight > offsetHeight) {
			document.getElementById('footer').removeAttribute('class', 'mt-auto');
			document.getElementById('footer').setAttribute('class', 'fixed-bottom');
		} else {
			document.getElementById('footer').removeAttribute('class', 'fixed-bottom');
			document.getElementById('footer').setAttribute('class', 'mt-auto');
		}
	}

    window.onload = function() {
        const forms = document.querySelectorAll('.needs-validation');
        Array.prototype.slice.call(forms).forEach(function(form) {
            form.addEventListener('submit', function(event) {
                if(!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
        adjustFooter();
		<% if(msgFlag != null && msgFlag.equals("modified")) { %>
			showModalConfirm("【顧客情報変更完了】", "顧客情報を更新しました。", "info");
		<% } else if (msgFlag != null && msgFlag.equals("deleted")) { %>
			showModalConfirm("【顧客情報削除完了】", "顧客情報を削除しました。", "info");
		<% } else if (msgFlag != null && msgFlag.equals("created")) { %>
			showModalConfirm("【顧客情報登録完了】", "顧客情報を新規登録しました。", "info");
		<% } %>
	};

	</script>
</head>

<body class="d-flex h-100 text-center text-dark" id="body">
	<div class="d-flex w-100 mx-auto flex-column">
		<header class="mb-5">
			<p class="fs-1 mt-2 mb-1">KIDDA-LA 業務システム</p>
			<p class="fs-3 mt-1 mb-2">《顧客管理》</p>
		</header>

		<main class="container">
		<div class="row">
			<div class="offset-1 col-10 d-flex flex-column">
				<form class="needs-validation" novalidate onSubmit="return checkCustSubmit();">
					<div class="card mb-3">
						<div class="card-header fs-5 bg-warning">顧客情報</div>
						<div class="card-body bg-warning-subtle text-start">
							<div class="row mb-1 <%= cust == null ? "d-none" : "" %>">
								<label for="custId" class="col-2 col-form-label fs-5">ID</label>
								<div class="col-10">
									<input class="form-control-plaintext fs-5" id="custId" name="custId" type="text" placeholder="" value="<%= cust != null ? cust.getCustId() : "0"%>" readonly>
								</div>
							</div>
							<div class="row mb-1">
								<label for="custName" class="col-2 col-form-label fs-5">氏名</label>
								<div class="col-10">
									<input class="form-control fs-5" id="custName" type="text" placeholder="例：山田太郎" value="<%= cust != null ? cust.getCustName() : "" %>" required>
									<div class="invalid-feedback">氏名を入力してください</div>
								</div>
							</div>
							<div class="row mb-1">
								<label for="kana" class="col-2 col-form-label fs-5">氏名カナ</label>
								<div class="col-10">
									<input class="form-control fs-5" id="kana" type="text" placeholder="例：ヤマダタロウ" value="<%= cust != null ? cust.getKana() : "" %>" required>
									<div class="invalid-feedback">氏名カナを入力してください</div>
								</div>
							</div>
							<div class="row mb-1">
								<label for="tel" class="col-2 col-form-label fs-5">電話番号</label>
								<div class="col-10">
									<input class="form-control fs-5" id="tel" type="text" placeholder="例：09012345678" value="<%= cust != null ? cust.getTel() : "" %>" required>
									<div class="invalid-feedback">電話番号を入力してください</div>
								</div>
							</div>
							<div class="row mb-1">
								<label for="address" class="col-2 col-form-label fs-5">住所</label>
								<div class="col-10">
									<input class="form-control fs-5" id="address" type="text" placeholder="例：東京都千代田区神田小川町１－８－５" value="<%= cust != null ? cust.getAddress() : "" %>" required>
									<div class="invalid-feedback">住所を入力してください</div>
								</div>
							</div>
						</div>
					</div>

					<div class="container p-0 mb-5">
						<div class="row justify-content-center">
							<% if (cust == null) { %>
								<div class="col-4">
									<button class="btn btn-warning w-100 btn-lg rounded-pill fs-5" type="submit">新規登録</button>
								</div>
							<% } else { %>
								<div class="col-4">
									<button class="btn btn-warning w-100 btn-lg rounded-pill fs-5" type="submit">顧客情報変更</button>
								</div>
								<div class="col-4">
									<button
										class="btn btn-danger w-100 btn-lg rounded-pill fs-5"
										type="button"
										onclick="location.href='KiddaLaController?command=CustomerControlDisplay'">
										取り消し
									</button>
								</div>
							<% } %>
							<div class="col-4">
								<button class="btn btn-secondary w-100 btn-lg rounded-pill fs-5" type="button" onClick="location.href='MainMenu.jsp'">戻る</button>
							</div>
						</div>
					</div>
				</form>
				<div class="col-12" id="customerData">
				<% if (customerData != null) { %>
				
				    <% if (customerData.length > 0) { %>
				        <!-- 顧客一覧表示 -->
				        <div class="card mb-3">
							<div class="card-header fs-5 bg-success text-white">顧客情報</div>
							<div class="card-body pb-2 bg-success-subtle">
								<table class="table table-bordered fs-5 mb-0" id="customerList">
									<thead class="table-success">
										<tr>
											<th>ID</th>
											<th>氏名</th>
											<th>カナ</th>
											<th>電話番号</th>
											<th>住所</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<% for(String[] customer : customerData) { %>
										<tr>
											<td class="bg-warning-subtle text-end">
												<%= customer[0] %></td>
											<td class="p-0 text-start align-middle">
												<a class="d-block text-decoration-none p-2" 
													href="KiddaLaController?custId=<%= customer[0] %>&command=OrderInputDisplay">
													<%= customer[1] %></a></td>
											<td class="p-0 text-start align-middle">
												<a class="d-block text-decoration-none p-2" 
													href="KiddaLaController?custId=<%= customer[0] %>&command=OrderInputDisplay">
													<%= customer[2] %></a></td>
											<td class="p-0 text-start align-middle">
												<a class="d-block text-decoration-none p-2" 
													href="KiddaLaController?custId=<%= customer[0] %>&command=OrderInputDisplay">
													<%= customer[3] %></a></td>
											<td class="p-0 text-start align-middle">
												<a class="d-block text-decoration-none p-2" 
													href="KiddaLaController?custId=<%= customer[0] %>&command=OrderInputDisplay">
													<%= customer[4] %></a></td>
											<td class="p-0 align-middle">
												<a class="text-decoration-none" 
													href="KiddaLaController?custId=<%= customer[0] %>&command=OrderInputDisplay">
													<button class="btn btn-primary btn-sm">
														<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
														  <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8M1.173 8a13 13 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5s3.879 1.168 5.168 2.457A13 13 0 0 1 14.828 8q-.086.13-.195.288c-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5s-3.879-1.168-5.168-2.457A13 13 0 0 1 1.172 8z"/>
														  <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5M4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0"/>
														</svg>
													</button>
													</a>
												<a class="text-decoration-none" 
													href="KiddaLaController?custId=<%= customer[0] %>&command=CustomerControlDisplay">
													<button class="btn btn-success btn-sm">
														<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
														  <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325"/>
														</svg>
													</button>	
												</a>
												<form action="KiddaLaController" method="post" class="d-inline">
													<input type="hidden" name="command" value="CustomerDelete">
													<input type="hidden" name="custId" value="<%= customer[0] %>">
													<button class="btn btn-danger btn-sm" type="submit"
													        onclick="return confirm('この顧客を削除してもよろしいですか？');">
														<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3" viewBox="0 0 16 16">
														  <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5M11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47M8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5"/>
														</svg>
													</button>
												</form>
											</td>
										</tr>
									<% } %>
									</tbody>
								</table>
							</div>
						</div>
				
				    <% } else { %>
				        <!-- 0件のとき -->
				        <div class="card mb-3">
				            <div class="card-body bg-secondary-subtle fs-5 text-center">
				                顧客情報はありません
				            </div>
				        </div>
				    <% } %>
				
				<% } %>
				</div>
			</div>
		</div>
		</main>

		<footer class="mt-auto" id="footer">
			<p>&copy;Infotech Serve Inc.</p>
		</footer>
	</div>

	<div class="modal fade" id="modalConfirm" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header" id="modalConfirm_header">
					<div class="d-inline-flex">
		                <img src="" width="48px" height="48px" id="modalConfirm_image" alt="画像">
						<p class="modal-title fs-2" id="modalConfirm_title"></p>
					</div>
				</div>
				<div class="modal-body fs-5" id="modalConfirm_message"></div>
				<div class="modal-footer justify-content-center">
					<div class="d-flex flex-column w-100">
						<div class="row justify-content-center">
							<div class="col-6">
								<button type="button" class="btn btn-secondary text-white rounded-pill fs-5 w-100" data-bs-dismiss="modal">戻る</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="modalCustConfirm" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header bg-warning">
					<div class="d-inline-flex">
		                <img src="css/Info.png" width="46px" height="46px" alt="">
						<p class="modal-title fs-2">
						    【顧客情報<%= cust != null ? "変更" : "新規" %>確認】
						</p>
					</div>
				</div>
				<form action="KiddaLaController" method="post">
					<input type="hidden" name="fromPage" value="customerControl" />
					<input type="hidden" name="command" value="<%= cust != null ? "CustomerModify" : "CustomerAdd" %>">

					<div class="modal-body text-start fs-5">
						<div class="row mb-1 <%= cust == null ? "d-none" : "" %>">
							<label for="custId" class="offset-2 col-2 col-form-label fs-5">ID</label>
							<div class="col-6">
								<input class="form-control-plaintext fs-5" name="custId" id="modalCustConfirm_custId" type="text" value="">
							</div>
						</div>
						<div class="row mb-1">
							<label for="custName" class="offset-2 col-2 col-form-label fs-5">氏名</label>
							<div class="col-6">
								<input class="form-control-plaintext fs-5" name="custName" id="modalCustConfirm_custName" type="text" value="">
							</div>
						</div>
						<div class="row mb-1">
							<label for="kana" class="offset-2 col-2 col-form-label fs-5">氏名カナ</label>
							<div class="col-6">
								<input class="form-control-plaintext fs-5" name="kana" id="modalCustConfirm_kana" type="text" value="">
							</div>
						</div>
						<div class="row mb-1">
							<label for="tel" class="offset-2 col-2 col-form-label fs-5">電話番号</label>
							<div class="col-6">
								<input class="form-control-plaintext fs-5" name="tel" id="modalCustConfirm_tel" type="text" value="">
							</div>
						</div>
						<div class="row mb-1">
							<label for="address" class="offset-2 col-2 col-form-label fs-5">住所</label>
							<div class="col-6">
								<input class="form-control-plaintext fs-5" name="address" id="modalCustConfirm_address" type="text" value="">
							</div>
						</div>
					</div>
					<div class="modal-footer justify-content-center">
						<div class="d-flex flex-column w-100">
							<div class="mb-3 fs-5 text-warning-emphasis">この内容でよろしければ、〔確定〕ボタンを押してください。</div>
							<div class="row justify-content-center">
								<div class="col-4">
									<button type="submit" class="btn btn-warning fs-5 rounded-pill w-100">確定</button>
								</div>
								<div class="col-4">
									<button type="button" class="btn btn-secondary fs-5 rounded-pill w-100" data-bs-dismiss="modal">取消</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>