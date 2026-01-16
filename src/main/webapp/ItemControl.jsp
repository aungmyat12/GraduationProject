<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="Error.jsp" %>
<%@ page import="model.Item" %>
<%
	String mode = (String) request.getAttribute("mode");
    Item item = (Item) request.getAttribute("item");
    @SuppressWarnings("unchecked")
    String[][] itemData = (String[][]) request.getAttribute("itemData");
    String errorMsg = (String) request.getAttribute("errorMsg");
    String msgFlag = (String) request.getAttribute("msgFlag");
%>
<!DOCTYPE html>
<html lang="ja" class="overflow-y-scroll">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>《商品管理》 KIDDA-LA 業務システム</title>
<link rel="icon" href="css/favicon.ico">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/KIDDA-LA.css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<script type="text/javascript">
function showModalConfirm(title, message, kind) {
    modal = new bootstrap.Modal(document.getElementById('modalConfirm'));
    let image, bgColor;
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

function showModalItemConfirm(itemId, itemName, size, price) {
    modal = new bootstrap.Modal(document.getElementById('modalItemConfirm'));
    document.getElementById('modalItemConfirm_itemId').value = itemId;
    document.getElementById('modalItemConfirm_itemName').value = itemName;
    document.getElementById('modalItemConfirm_size').value = size;
    document.getElementById('modalItemConfirm_price').value = price;
    modal.show();
    document.getElementById('body').style.paddingRight = '0px';
    document.getElementById('footer').style.paddingRight = '0px';
    document.getElementById('modalItemConfirm').style.paddingRight = '0px';
}

function checkItemSubmit() {
    const itemName = document.getElementById('itemName').value;
    const price = document.getElementById('price').value;
    if(itemName == "" || price == "") {
        return false;
    }
    const itemId = document.getElementById('itemId').value;
    const size = document.getElementById('size').value;
    showModalItemConfirm(itemId, itemName, size, price);
    return false;
}

function adjustFooter() {
    const clientHeight = document.documentElement.clientHeight;
    const offsetHeight = document.documentElement.offsetHeight;
    const footer = document.getElementById('footer');
    if(clientHeight > offsetHeight){
        footer.className = 'fixed-bottom';
    } else {
        footer.className = 'mt-auto';
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
        showModalConfirm("【商品情報変更完了】", "商品情報を更新しました。", "info");
    <% } else if(msgFlag != null && msgFlag.equals("deleted")) { %>
        showModalConfirm("【商品情報削除完了】", "商品情報を削除しました。", "info");
    <% } else if(msgFlag != null && msgFlag.equals("created")) { %>
        showModalConfirm("【商品情報登録完了】", "商品情報を新規登録しました。", "info");
    <% } %>
};
</script>
</head>

<body class="d-flex h-100 text-center text-dark" id="body">
<div class="d-flex w-100 mx-auto flex-column">
<header class="mb-5">
<p class="fs-1 mt-2 mb-1">KIDDA-LA 業務システム</p>
<p class="fs-3 mt-1 mb-2">《商品管理》</p>
</header>

<main class="container">
<div class="row">
<div class="offset-1 col-10 d-flex flex-column">
<% if(errorMsg != null){ %>
    <div class="alert alert-danger fs-5 text-center">
        <%= errorMsg %>
    </div>
<% } %>
<form class="needs-validation" novalidate onsubmit="return checkItemSubmit();">
<div class="card mb-3">
<div class="card-header fs-5 bg-warning">商品情報</div>
<div class="card-body bg-warning-subtle text-start">
    <div class="row mb-1">
        <label for="itemId" class="col-2 col-form-label fs-5">ID</label>
        <div class="col-10">
            <input class="form-control-plaintext fs-5" id="itemId" name="itemId" type="text" value="<%= item != null ? item.getItemId() : "" %>" readonly>
        </div>
    </div>
    <div class="row mb-1">
        <label for="itemName" class="col-2 col-form-label fs-5">商品名</label>
        <div class="col-10">
            <input class="form-control fs-5" name="itemName" id="itemName" type="text" placeholder="例：Tシャツ" value="<%= item != null ? item.getItemName() : "" %>" required>
            <div class="invalid-feedback">商品名を入力してください</div>
        </div>
    </div>
    <div class="row mb-1">
        <label for="size" class="col-2 col-form-label fs-5">サイズ</label>
        <div class="col-10">
            <input class="form-control fs-5" name="size" id="size" type="text" placeholder="例：L" value="<%= (item != null && item.getSize() != null) ? item.getSize() : "" %>">
        </div>
    </div>
    <div class="row mb-1">
	    <label for="price" class="col-2 col-form-label fs-5">価格</label>
	    <div class="col-10">
	        <input
	            type="number"
	            class="form-control fs-5"
	            id="price"
	            name="price"
	            min="1"
	            step="1"
	            value="<%= item != null ? item.getPrice() : "" %>"
	            style="width: 100% !important"
	            required
	        >
	        <div class="invalid-feedback">価格を入力してください</div>
	    </div>
	</div>
</div>
</div>

<div class="container p-0 mb-5">
<div class="row justify-content-center">
    <% if(item == null || "add".equals(mode)) { %>
        <div class="col-4">
            <button class="btn btn-warning w-100 btn-lg rounded-pill fs-5" type="submit">新規登録</button>
        </div>
    <% } else { %>
        <div class="col-4">
            <button class="btn btn-warning w-100 btn-lg rounded-pill fs-5" type="submit">商品情報変更</button>
        </div>
        <div class="col-4">
            <button class="btn btn-danger w-100 btn-lg rounded-pill fs-5" type="button"
            onclick="location.href='KiddaLaController?command=ItemControlDisplay'">取り消し</button>
        </div>
    <% } %>
    <div class="col-4">
        <button class="btn btn-secondary w-100 btn-lg rounded-pill fs-5" type="button" onclick="location.href='MainMenu.jsp'">戻る</button>
    </div>
</div>
</div>
</form>

<!-- 一覧表示 -->
<div class="col-12" id="itemData">
<% if(itemData != null && itemData.length > 0) { %>
<div class="card mb-3">
    <div class="card-header fs-5 bg-success text-white">商品一覧</div>
    <div class="card-body pb-2 bg-success-subtle">
        <table class="table table-bordered fs-5 mb-0" id="itemList">
            <thead class="table-success">
                <tr>
                    <th>ID</th>
                    <th>商品名</th>
                    <th>サイズ</th>
                    <th>価格</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            <% for(String[] i : itemData) { %>
                <tr>
                    <td class="bg-warning-subtle text-middle"><%= i[0] %></td>
                    <td class="p-0 text-start align-middle"><%= i[1] %></td>
                    <td class="p-0 text-middle align-middle"><%= i[2] != null ? i[2] : ""%></td>
                    <td class="p-0 text-end align-middle">
					    <%= String.format("%,d", Integer.parseInt(i[4])) %>
					</td>
                    <td class="p-0 align-middle">
					    <!-- 編集ボタン -->
					    <a href="KiddaLaController?itemId=<%= i[0] %>&command=ItemControlDisplay" class="btn btn-success btn-sm">
					        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
					            <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325"/>
					        </svg>
					    </a>
					
					    <!-- 削除ボタン -->
					    <form action="KiddaLaController" method="post" class="d-inline ms-1">
					        <input type="hidden" name="command" value="ItemDelete">
					        <input type="hidden" name="itemId" value="<%= i[0] %>">
					        <button class="btn btn-danger btn-sm" type="submit"
					            onclick="return confirm('この商品を削除してもよろしいですか？');">
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
<div class="card mb-3">
    <div class="card-body bg-secondary-subtle fs-5 text-center">商品情報はありません</div>
</div>
<% } %>
</div>
</div>
</div>
</main>

<footer class="mt-auto" id="footer">
<p>&copy;Infotech Serve Inc.</p>
</footer>
</div>

<!-- 確認モーダル -->
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

<!-- 商品確認モーダル -->
<div class="modal fade" id="modalItemConfirm" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-hidden="true">
<div class="modal-dialog modal-lg modal-dialog-centered">
<div class="modal-content">
<div class="modal-header bg-warning">
    <div class="d-inline-flex">
        <img src="css/Info.png" width="46px" height="46px" alt="">
        <p class="modal-title fs-2">
            【商品情報<%= (item != null && !"add".equals(mode)) ? "変更" : "新規" %>確認】
        </p>
    </div>
</div>
<form action="KiddaLaController" method="post">
<input type="hidden" name="command" value="<%= (item != null && !"add".equals(mode)) ? "ItemModify" : "ItemAdd" %>">
<div class="modal-body text-start fs-5">
    <div class="row mb-1">
        <label for="itemId" class="offset-2 col-2 col-form-label fs-5">ID</label>
        <div class="col-6">
            <input class="form-control-plaintext fs-5" name="itemId" id="modalItemConfirm_itemId" type="text" value="">
        </div>
    </div>
    <div class="row mb-1">
        <label for="itemName" class="offset-2 col-2 col-form-label fs-5">商品名</label>
        <div class="col-6">
            <input class="form-control-plaintext fs-5" name="itemName" id="modalItemConfirm_itemName" type="text" value="">
        </div>
    </div>
    <div class="row mb-1">
        <label for="size" class="offset-2 col-2 col-form-label fs-5">サイズ</label>
        <div class="col-6">
            <input class="form-control-plaintext fs-5" name="size" id="modalItemConfirm_size" type="text" value="">
        </div>
    </div>
    <div class="row mb-1">
        <label for="price" class="offset-2 col-2 col-form-label fs-5">価格</label>
        <div class="col-6">
            <input class="form-control-plaintext fs-5" name="price" id="modalItemConfirm_price" type="text" value="">
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
