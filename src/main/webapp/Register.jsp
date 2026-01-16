<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="Error.jsp" %>
<%@ page import="model.User" %>
<%
	User userInput = (User) request.getAttribute("userInput");
	String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html lang="ja" class="overflow-y-scroll">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>《新規登録》 KIDDA-LA 業務システム</title>

	<link rel="icon" href="css/favicon.ico">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/KIDDA-LA.css">
	<script src="js/bootstrap.min.js"></script>
</head>

<body class="d-flex h-100 text-center text-dark">
	<div class="d-flex w-100 mx-auto flex-column">

		<header class="mb-5">
			<p class="fs-1 mt-2 mb-1">KIDDA-LA 業務システム</p>
			<p class="fs-3 mt-1 mb-2">《新規登録》</p>
		</header>

		<main class="container">
			<div class="row">
				<div class="offset-1 col-10 d-flex flex-column">
					
					<% if(errorMsg != null){ %>
					    <div class="alert alert-danger fs-5 text-center">
					        <%= errorMsg %>
					    </div>
					<% } %>
					<form action="KiddaLaController" method="post"
					      class="needs-validation" novalidate>
	
						<input type="hidden" name="command" value="Register">
						<div class="card mb-4">
							<div class="card-header fs-5 bg-warning">
								ユーザー登録
							</div>

							<div class="card-body bg-warning-subtle text-start">

								<div class="row mb-2">
									<label class="col-3 col-form-label fs-5">名前</label>
									<div class="col-9">
										<input type="text" name="name"
										       class="form-control fs-5" placeholder="例：山田太郎" value="<%= userInput != null ? userInput.getName() : "" %>" required>
										<div class="invalid-feedback">
											名前を入力してください
										</div>
									</div>
								</div>

								<div class="row mb-2">
									<label class="col-3 col-form-label fs-5">Gmail</label>
									<div class="col-9">
										<input type="email" name="email"
										       class="form-control fs-5"
										       placeholder="example@gmail.com" value="<%= userInput != null ? userInput.getEmail() : "" %>" required>
										<div class="invalid-feedback">
											Gmailを入力してください
										</div>
									</div>
								</div>

								<div class="row mb-2">
									<label class="col-3 col-form-label fs-5">パスワード</label>
									<div class="col-9">
										<input type="password" name="password"
										       id="password"
										       class="form-control fs-5" required>
										<div class="invalid-feedback">
											パスワードを入力してください
										</div>
									</div>
								</div>

								<div class="row mb-2">
									<label class="col-3 col-form-label fs-5">確認</label>
									<div class="col-9">
										<input type="password" name="passwordConfirm"
										       id="passwordConfirm"
										       class="form-control fs-5" required>
										<div class="invalid-feedback">
											パスワードが一致しません
										</div>
									</div>
								</div>

							</div>
						</div>

						<div class="container p-0 mb-5">
							<div class="row justify-content-center">
								<div class="col-4">
									<button type="submit"
									        class="btn btn-primary w-100 btn-lg rounded-pill fs-5">
										登録
									</button>
								</div>
								<div class="col-4">
									<button type="button"
									        class="btn btn-secondary w-100 btn-lg rounded-pill fs-5"
									        onclick="history.back()">
										戻る
									</button>
								</div>
							</div>
						</div>

					</form>

				</div>
			</div>
		</main>

		<footer class="mt-auto">
			<p>&copy;Infotech Serve Inc.</p>
		</footer>

	</div>

	<script>
		(() => {
			'use strict'
			const forms = document.querySelectorAll('.needs-validation')
		
			Array.from(forms).forEach(form => {
				form.addEventListener('submit', event => {
		
					const email    = document.querySelector('input[name="email"]')
					const password = document.getElementById('password')
					const confirm  = document.getElementById('passwordConfirm')
		
					const gmailRegex = /^[a-zA-Z0-9._%+-]+@gmail\.com$/
		
					if (!gmailRegex.test(email.value)) {
						email.setCustomValidity('invalid')
					} else {
						email.setCustomValidity('')
					}
		
					if (password.value !== confirm.value) {
						confirm.setCustomValidity('invalid')
					} else {
						confirm.setCustomValidity('')
					}
		
					if (!form.checkValidity()) {
						event.preventDefault()
						event.stopPropagation()
					}
		
					form.classList.add('was-validated')
				}, false)
			})
		})()
	</script>

</body>
</html>
