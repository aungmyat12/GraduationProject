/**
 * クラス名：	KiddaLaController
 * 概要　　：	KIDDA-LA業務システムを制御する。
 * 作成者名：	丸山
 * 作成日　：	20XX/06/20
 * 修正者名：
 * 修正日　：
 */
package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CustomerAddAction;
import action.CustomerControlAction;
import action.CustomerDeleteAction;
import action.CustomerInputDisplayAction;
import action.CustomerModifyAction;
import action.CustomerSearchAction;
import action.DeliveryCompleteAction;
import action.DeliveryConfirmAction;
import action.GoogleLoginAction;
import action.ItemAddAction;
import action.ItemControlAction;
import action.ItemDeleteAction;
import action.ItemInputDisplayAction;
import action.ItemMenuDisplayAction;
import action.ItemModifyAction;
import action.LoginAction;
import action.OrderInputDisplayAction;
import action.OrderRegisterAction;
import action.RegisterAction;
import model.Customer;
import model.GoogleUser;
import model.Item;
import model.OrderControlUtility;
import model.OrderDetail;
import model.User;
import service.GoogleOAuthService;

@WebServlet("/KiddaLaController")
public class KiddaLaController extends HttpServlet {
	// GETリクエスト処理
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doPostメソッドの呼出し
		doPost(request, response);
	}

	// POSTリクエスト処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエスト情報に対する文字コードの設定する
		request.setCharacterEncoding("UTF-8");
		// 各画面から送信されるリクエストパラメータ"command"の値を取得する
		String command = request.getParameter("command");
		// リクエストパラメータ"command"の値がない場合
		if (command == null || command.isEmpty()) {
			// 値を"MainMenu"にする
			command = "MainMenu";
		}
		// 次画面用の変数
		String nextPage = null;
		// セッションオブジェクト格納用変数
		HttpSession session = null;
		// リクエストパラメータ"command"の値に対応した処理を実行する
		switch (command) {
		case "MainMenu":
			// 次画面に"MainMenu.jsp"を設定する
			nextPage = "MainMenu.jsp";
			break;
		case "RegisterDisplay":
			// 次画面に Register.jsp を設定
			nextPage = "Register.jsp";
			break;
			
		case "Register":
			try {
				// 次画面
				nextPage = "Login.jsp";

				// リクエストパラメータ取得
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				String password = request.getParameter("password");

				// User オブジェクト生成
				User user = new User(name, email, password);

				// RegisterAction 実行
				RegisterAction registerAction = new RegisterAction();
				int result = registerAction.execute(user);

				session = request.getSession();
				if (result == 1) {
				    request.setAttribute("msgFlag", "registered");
				    nextPage = "Login.jsp";  // forward OK
				} else {
				    request.setAttribute("errorMsg",
				        result == -1 ? "このメールアドレスはすでに登録されています。" : "登録に失敗しました。");
				    request.setAttribute("userInput", user);  // requestスコープに入れる
		            nextPage = "KiddaLaController?command=RegisterDisplay";
				}
			} catch (Exception e) {
				request.setAttribute("errorMsg", e.getMessage());
				nextPage = "Error.jsp";
			}
			break;
		case "LoginDisplay":
		    // ログイン画面表示
		    nextPage = "Login.jsp";
		    break;
		case "Login":
		    try {
		        // 入力値取得
		        String email = request.getParameter("email");
		        String password = request.getParameter("password");

		        // User オブジェクト生成
		        User user = new User();
		        user.setEmail(email);
		        user.setPassword(password);

		        // LoginAction 実行
		        LoginAction loginAction = new LoginAction();
		        User loginUser = loginAction.execute(user);

		        if (loginUser != null) {
		            // ログイン成功
		            session = request.getSession();
		            session.setAttribute("loginUser", loginUser);

		            // メインメニューへ
		            nextPage = "MainMenu.jsp";
		        } else {
		            // ログイン失敗
		            request.setAttribute("errorMsg", "メールアドレスまたはパスワードが正しくありません。");
		            request.setAttribute("userInput", user);
		            nextPage = "KiddaLaController?command=LoginDisplay";
		        }

		    } catch (Exception e) {
		        request.setAttribute("errorMsg", e.getMessage());
		        nextPage = "Error.jsp";
		    }
		    break;
		case "GoogleLogin":
		    // Google OAuth の認証URLへリダイレクト
			String clientId = System.getenv("GOOGLE_CLIENT_ID");
			String redirectUri = System.getenv("GOOGLE_REDIRECT_URI");

		    String googleAuthUrl =
		        "https://accounts.google.com/o/oauth2/v2/auth"
		        + "?response_type=code"
		        + "&client_id=" + clientId
		        + "&scope=openid%20email%20profile"
		        + "&redirect_uri=" + redirectUri;

		    response.sendRedirect(googleAuthUrl);
		    return;
		case "GoogleCallback":
		    try {
		        // Googleから返ってくるのは code
		        String code = request.getParameter("code");

		        GoogleOAuthService service = new GoogleOAuthService();
		        GoogleUser googleUser = service.getUserInfo(code);

		        GoogleLoginAction action = new GoogleLoginAction();
		        User user = action.execute(
		            googleUser.getEmail(),
		            googleUser.getName()
		        );

		        session = request.getSession();
		        session.setAttribute("loginUser", user);

		        nextPage = "MainMenu.jsp";

		    } catch (Exception e) {
		        e.printStackTrace();
		        request.setAttribute("errorMsg", "Googleログインに失敗しました");
		        nextPage = "Login.jsp";
		    }
		    break;




		case "Logout":
            session = request.getSession();
		    session.invalidate();
		    nextPage = "MainMenu.jsp";
		    break;
		
		case "CustomerSearchDisplay":
			// 次画面に"CustomerSearch.jsp"を設定する
			nextPage = "CustomerSearch.jsp";
			// セッションオブジェクトを取得する（ない場合はnullが返る）
			session = request.getSession(false);
			// セッションオブジェクトがある場合
			if (session != null) {
				// セッションオブジェクトを破棄する
				session.invalidate();
			}
			break;
		case "CustomerSearch":
			try {
				// 次画面に"CustomerSearch.jsp"を設定する
				nextPage = "CustomerSearch.jsp";
				// リクエストパラメータ"tel"の値を取得する
				String paramTel = request.getParameter("tel");
				// リクエストパラメータ"name"の値を取得する
				String paramName = request.getParameter("name");
				// リクエストパラメータの値を配列にする
				String[] data = { paramTel, paramName };
				// CustomerSearchActionクラスのインスタンスを生成する
				CustomerSearchAction customerSearchAction = new CustomerSearchAction();
				// executeメソッドに配列にしたリクエストパラメータを渡し，結果として顧客情報の二次元配列を受け取る
				String[][] customerData = customerSearchAction.execute(data);
				// セッションオブジェクトを取得する（ない場合は生成する）
				session = request.getSession();
				// 顧客情報の二次元配列があり要素数が０ではない場合
				if (customerData != null && customerData.length != 0) {
					// セッションオブジェクト（スコープ）に顧客情報の二次元配列を登録する
					session.setAttribute("customerData", customerData);
				// 顧客情報がないか要素数が０の場合
				} else {
					// セッションオブジェクト（スコープ）の顧客情報の二次元配列を削除する
					session.removeAttribute("customerData");
					// リクエストオブジェクト（スコープ）にエラーメッセージを登録する
					request.setAttribute(
							"errorMsg", "一致する情報は見つかりませんでした。");
				}
			} catch (Exception e) {
				// リクエストオブジェクト（スコープ）にエラーメッセージを登録する
				request.setAttribute(
						"errorMsg", e.getMessage());
				// 例外をキャッチした場合は次画面に"Error.jsp"を設定する
				nextPage = "Error.jsp";
			}
			break;
		case "OrderInputDisplay":
			try {
				// 次画面に"OrderInput.jsp"を設定する
				nextPage = "OrderInput.jsp";
				// リクエストパラメータ"custId"の値を取得する
				String custId = request.getParameter("custId");
				// セッションオブジェクトを取得する（ない場合は生成する）
				session = request.getSession();
				// OrderInputDisplayActionクラスのインスタンスを生成する
				OrderInputDisplayAction orderInputDisplayAction = new OrderInputDisplayAction();
				// executeメソッドの取得したリクエストパラメータを渡し，結果として顧客情報を受け取る
				Customer customer = orderInputDisplayAction.execute(custId);
				// セッションオブジェクト（スコープ）に顧客情報を登録する
				session.setAttribute("customer", customer);
				// ItemMenuDisplayActionクラスのインスタンスを生成する
				ItemMenuDisplayAction itemMenuDisplayAction = new ItemMenuDisplayAction();
				// executeメソッドを呼び出し，結果として商品情報の二次元配列を受け取る
				String[][] itemData = itemMenuDisplayAction.execute();
				// セッションオブジェクト（スコープ）に商品情報の二次元配列を登録する
				session.setAttribute("itemData", itemData);
			} catch (Exception e) {
				// リクエストオブジェクト（スコープ）にエラーメッセージを登録する
				request.setAttribute(
						"errorMsg", e.getMessage());
				// 例外をキャッチした場合は次画面に"Error.jsp"を設定する
				nextPage = "Error.jsp";
			}
			break;
		case "OrderRegister":
			try {
				// 次画面に"DeliveryConfirm.jsp"を設定する
				nextPage = "DeliveryConfirm.jsp";
				// セッションオブジェクトを取得する
				session = request.getSession();
				// セッションスコープから顧客情報を取得する
				Customer customer = (Customer)session.getAttribute("customer");
				// セッションスコープから商品情報の二次元配列を取得する
				String[][] itemData = (String[][])session.getAttribute("itemData");
				// リクエストパラメータ"quantity"の値を配列で取得する
				String[] aryQuantity = request.getParameterValues("quantity");
				// 注文明細を格納するするためにArrayListクラスのインスタンスを生成する
				ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
				// 商品情報の二次元配列の行数分繰り返す
				for(int i = 0; i < itemData.length; i++) {
					// 配列要素aryQuantity[i]の値（数量）をint型に変換する
					int quantity = Integer.parseInt(aryQuantity[i]);
					// int型に変換した値（数量）が１以上の場合
					if(quantity >= 1) {
						// 配列要素itemData[i][4]の値（価格）の","を""に置き換える
						String strPrice = itemData[i][4].replaceAll(",", "");
						// 変数strPriceの値（価格）をint型に変換する
						int price = Integer.parseInt(strPrice);
						// Itemクラスのインスタンスを生成する
						Item item = new Item(itemData[i][0], itemData[i][1], itemData[i][2], price);
						// 現在の日付を取得する
						String orderDate = OrderControlUtility.getDate();
						// OrderDetailクラスのインスタンスを生成する
						OrderDetail orderDetail = 
								new OrderDetail(0, customer, item, orderDate, quantity, null, 1);
						// ArrayListオブジェクトにOrderDetailオブジェクトを追加する
						orderDetailList.add(orderDetail);
					}
				}
				// ArrayListオブジェクトの要素数が１以上の場合
				if(orderDetailList.size() >= 1) {
					// OrderRegisterActionクラスのインスタンスを生成する
					OrderRegisterAction orderRegisterAction = new OrderRegisterAction();
					// executeメソッドに注文明細を格納したArrayListオブジェクトを渡し，結果として注文明細を格納したArrayListオブジェクトを受け取る
					orderDetailList = orderRegisterAction.execute(orderDetailList);
					// リクエストオブジェクト（スコープ）に注文明細を格納したArrayListオブジェクトを登録する
					request.setAttribute("orderDetailList", orderDetailList);
					// リクエストオブジェクト（スコープ）にメッセージフラグを登録する
					request.setAttribute("msgFlag", "registered");
				// ArrayListオブジェクトの要素数が１以上でない場合
				} else {
					// リクエストオブジェクト（スコープ）にエラーメッセージを登録する
					request.setAttribute("errorMsg", "商品数量を入力してください。");
					// 次画面に"OrderInput.jsp"を設定する
					nextPage = "OrderInput.jsp";
				}
			}catch(Exception e) {
				// リクエストオブジェクト（スコープ）にエラーメッセージを登録する
				request.setAttribute(
						"errorMsg", e.getMessage());
				// 例外をキャッチした場合は次画面に"Error.jsp"を設定する
				nextPage = "Error.jsp";
			}
			break;
		case "DeliveryConfirm":
			try {
				// 次画面に"DeliveryConfirm.jsp"を設定する
				nextPage = "DeliveryConfirm.jsp";
				// セッションオブジェクトを取得する
				session = request.getSession();
				// セッションスコープから顧客情報を取得する
				Customer customer = (Customer)session.getAttribute("customer");
				// 顧客情報から顧客IDを取得する
				int custId = customer.getCustId();
				// DeliveryConfirmActionクラスのインスタンスを生成する
				DeliveryConfirmAction deliveryConfirmAction = new DeliveryConfirmAction();
				// executeメソッドに顧客IDを渡し，結果として注文明細を格納したArrayListオブジェクトを受け取る
				ArrayList<OrderDetail> orderDetailList = deliveryConfirmAction.execute(custId);
				// ArrayListオブジェクトの要素数が１以上の場合
				if(orderDetailList.size() >= 1) {
					// リクエストオブジェクト（スコープ）に注文明細を格納したArrayListオブジェクトを登録する
					request.setAttribute("orderDetailList", orderDetailList);
					// リクエストオブジェクト（スコープ）にメッセージフラグを登録する
					request.setAttribute("msgFlag", "confirm");
				// ArrayListオブジェクトの要素数が１以上でない場合
				} else {
					// リクエストオブジェクト（スコープ）にメッセージフラグを登録する
					request.setAttribute("msgFlag", "noList");
					// 次画面に"OrderInput.jsp"を設定する
					nextPage = "OrderInput.jsp";
				}
			}catch(Exception e) {
				// リクエストオブジェクト（スコープ）にエラーメッセージを登録する
				request.setAttribute(
						"errorMsg", e.getMessage());
				// 例外をキャッチした場合は次画面に"Error.jsp"を設定する
				nextPage = "Error.jsp";
			}
			break;
		case "DeliveryComplete":
			try {
				// 次画面に"DeliveryConfirm.jsp"を設定する
				nextPage = "DeliveryConfirm.jsp";
				// セッションオブジェクトを取得する
				session = request.getSession();
				// セッションスコープから顧客情報を取得する
				Customer customer = (Customer)session.getAttribute("customer");
				// 顧客情報から顧客IDを取得する
				int custId = customer.getCustId();
				// DeliveryCompleteActionクラスのインスタンスを生成する
				DeliveryCompleteAction deliveryCompleteAction = new DeliveryCompleteAction();
				// executeメソッドに顧客IDを渡し，結果としてint型の値（登録した行数：１以上）を取得する
				int result = deliveryCompleteAction.execute(custId);
				// 変数resultの値が１以上の場合
				if(result >= 1) {
					// リクエストオブジェクト（スコープ）にメッセージフラグを登録する
					request.setAttribute("msgFlag", "completed");
				// 変数resultの値が１以上でない場合
				} else {
					// リクエストオブジェクト（スコープ）にエラーメッセージを登録する
					request.setAttribute("errorMsg", "配達完了処理に失敗しました！");
				}
			}catch(Exception e) {
				// リクエストオブジェクト（スコープ）にエラーメッセージを登録する
				request.setAttribute(
						"errorMsg", e.getMessage());
				// 例外をキャッチした場合は次画面に"Error.jsp"を設定する
				nextPage = "Error.jsp";
			}
			break;

		case "CustomerControlDisplay":
			try {
				// 次画面に「CustomerControl.jsp」を設定
				nextPage = "CustomerControl.jsp";

				// リクエストから顧客IDを取得
				String custId = request.getParameter("custId");

				// 顧客IDが指定されている場合（編集ボタン押下時）
				if (custId != null) {
					// 顧客情報取得用Actionを生成
					CustomerInputDisplayAction customerInputDisplayAction =
							new CustomerInputDisplayAction();

					// 顧客IDを渡して顧客情報を取得
					Customer customer = customerInputDisplayAction.execute(custId);

					// 顧客情報をリクエストスコープに設定
					request.setAttribute("customer", customer);
				}

				// 顧客一覧取得用Actionを生成
				CustomerControlAction customerControlAction =
						new CustomerControlAction();

				// 全顧客情報を取得
				String[][] customerData = customerControlAction.execute();

				// 顧客一覧をリクエストスコープに設定
				request.setAttribute("customerData", customerData);

				// セッションからメッセージフラグを取得
				session = request.getSession();
				if (session != null) {
					String msgFlag = (String) session.getAttribute("msgFlag");
					if (msgFlag != null) {
						// JSP表示用にリクエストへ移動
						request.setAttribute("msgFlag", msgFlag);
						// 表示後に削除（再表示防止）
						session.removeAttribute("msgFlag");
					}
				}
			} catch (Exception e) {
				// エラーメッセージを設定
				request.setAttribute("errorMsg", e.getMessage());
				// エラー画面へ遷移
				nextPage = "Error.jsp";
			}
			break;
		case "ItemControlDisplay":
		    try {
		        nextPage = "ItemControl.jsp";

		        String itemId = request.getParameter("itemId");
		        String modeParam = request.getParameter("mode");

		        if (itemId != null && "edit".equals(modeParam)) {
		            // 編集モード
		            ItemInputDisplayAction action =
		                    new ItemInputDisplayAction();

		            Item item = action.execute(itemId);

		            request.setAttribute("item", item);
		            request.setAttribute("mode", "edit"); // ★追加
		        } else {
		            // 新規登録モード
		            request.setAttribute("mode", "add"); // ★追加
		        }

		        ItemControlAction itemControlAction =
		                new ItemControlAction();

		        String[][] itemData = itemControlAction.execute();
		        request.setAttribute("itemData", itemData);

		        session = request.getSession();
		        if (session != null) {
		            String msgFlag = (String) session.getAttribute("msgFlag");
		            if (msgFlag != null) {
		                request.setAttribute("msgFlag", msgFlag);
		                session.removeAttribute("msgFlag");
		            }
		        }

		    } catch (Exception e) {
		        request.setAttribute("errorMsg", e.getMessage());
		        nextPage = "Error.jsp";
		    }
		    break;
		case "ItemAdd":
		    try {
		        // 入力値取得
		        String itemId   = request.getParameter("itemId");
		        String itemName = request.getParameter("itemName");
		        String size     = request.getParameter("size");
		        int price       = Integer.parseInt(request.getParameter("price"));

		        int userId = 1;

		        Item item = new Item(itemId, itemName, size, price, userId);

		        ItemAddAction action = new ItemAddAction();
		        int result = action.execute(item);
		        if (result == 1) {
		            session = request.getSession();
		            session.setAttribute("msgFlag", "created");

		            // 成功時のみ redirect
		            response.sendRedirect(
		                "KiddaLaController?command=ItemControlDisplay");
		            return;

		        } else if (result == -1) {
		            request.setAttribute("errorMsg", "この商品IDはすでに登録されています。");
		            request.setAttribute("item", item);
		            request.setAttribute("mode", "add");

		            // ★ 商品一覧も必ずセット
		            ItemControlAction itemControlAction = new ItemControlAction();
		            String[][] itemData = itemControlAction.execute();
		            request.setAttribute("itemData", itemData);

		            nextPage = "ItemControl.jsp"; // forward
		        }

		    } catch (Exception e) {
		        request.setAttribute("errorMsg", e.getMessage());
		        nextPage = "Error.jsp";
		    }
		    break;
		case "ItemModify":
		    try {
		        String itemId = request.getParameter("itemId");
		        String itemName = request.getParameter("itemName");
		        String size = request.getParameter("size");
		        int price = Integer.parseInt(request.getParameter("price"));

		        session = request.getSession();

		        Item item = new Item(itemId, itemName, size, price);

		        ItemModifyAction action = new ItemModifyAction();
		        int result = action.execute(item);

		        if (result == 1) {
		            session.setAttribute("msgFlag", "modified");

		            nextPage = "KiddaLaController?command=ItemControlDisplay";

		        }  else {
		        	request.setAttribute("errorMsg", "商品情報の更新に失敗しました。");
		            request.setAttribute("item", item);
		            request.setAttribute("mode", "edit");

		            // ★ 商品一覧を必ず再セット
		            ItemControlAction itemControlAction = new ItemControlAction();
		            String[][] itemData = itemControlAction.execute();
		            request.setAttribute("itemData", itemData);

		            nextPage = "ItemControl.jsp"; // forward
		        }

		    } catch (Exception e) {
		        request.setAttribute("errorMsg", e.getMessage());
		        nextPage = "Error.jsp";
		    }
		    break;
		case "ItemDelete":
		    try {
		        // 遷移先は商品管理画面
		        nextPage = "ItemControl.jsp";

		        // 削除対象の商品IDを取得
		        String itemId = request.getParameter("itemId");

		        // 商品削除Action生成
		        ItemDeleteAction action = new ItemDeleteAction();

		        // 削除実行
		        int result = action.execute(itemId);

		        session = request.getSession();
		        if (result == 1) {
		            // 削除成功
		            session.setAttribute("msgFlag", "deleted");

		            // PRGパターン
		            response.sendRedirect(
		                "KiddaLaController?command=ItemControlDisplay");
		            return;
		        } else {
		            // 削除失敗
		            request.setAttribute("errorMsg", "商品削除に失敗しました。");
		        }

		    } catch (Exception e) {
		        request.setAttribute("errorMsg", e.getMessage());
		        nextPage = "Error.jsp";
		    }
		    break;

		case "CustomerAdd":
			try {
				// 遷移先は顧客管理画面
				nextPage = "CustomerControl.jsp";

				// 入力値を取得
				String custName = request.getParameter("custName");
				String kana = request.getParameter("kana");
				String tel = request.getParameter("tel");
				String address = request.getParameter("address");

				// 顧客オブジェクト生成（新規登録のためIDは0）
				Customer customer = new Customer(0, custName, kana, tel, address);

				// 顧客追加Actionを生成
				CustomerAddAction customerAddAction =
						new CustomerAddAction();

				// 登録処理を実行
				int result = customerAddAction.execute(customer);

				if (result == 1) {
					// 登録成功メッセージをセッションに設定
					session = request.getSession();
					session.setAttribute("msgFlag", "created");

					// PRGパターンで一覧画面へリダイレクト
					response.sendRedirect(
						"KiddaLaController?command=CustomerControlDisplay");
					return;
				}

			} catch (Exception e) {
				// エラーメッセージを設定
				request.setAttribute("errorMsg", e.getMessage());
				// エラー画面へ遷移
				nextPage = "Error.jsp";
			}
			break;

		case "CustomerDelete":
			try {
				// 遷移先は顧客管理画面
				nextPage = "CustomerControl.jsp";

				// 削除対象の顧客IDを取得
				int custId = Integer.parseInt(request.getParameter("custId"));

				// 顧客削除Actionを生成
				CustomerDeleteAction action =
						new CustomerDeleteAction();

				// 削除処理を実行
				int result = action.execute(custId);

				session = request.getSession();
				if (result == 1) {
					// 削除成功メッセージをセッションに設定
					session.setAttribute("msgFlag", "deleted");

					// PRGパターンで一覧画面へリダイレクト
					response.sendRedirect(
						"KiddaLaController?command=CustomerControlDisplay");
					return;
				} else {
					// 削除失敗時のメッセージ
					request.setAttribute("errorMsg", "削除に失敗しました。");
				}

			} catch (Exception e) {
				// エラーメッセージを設定
				request.setAttribute("errorMsg", e.getMessage());
				// エラー画面へ遷移
				nextPage = "Error.jsp";
			}
			break;

		case "CustomerModify":
			try {
				// どの画面から遷移してきたかを判別するためのパラメータ
				String fromPage = request.getParameter("fromPage");
				// リクエストパラメータ"custId"の値を取得する
				int custId = Integer.parseInt(request.getParameter("custId"));
				// リクエストパラメータ"custName"の値を取得する
				String custName = request.getParameter("custName");
				// リクエストパラメータ"kana"の値を取得する
				String kana = request.getParameter("kana");
				// リクエストパラメータ"tel"の値を取得する
				String tel = request.getParameter("tel");
				// リクエストパラメータ"address"の値を取得する
				String address = request.getParameter("address");
				// セッションオブジェクトを取得する
				session = request.getSession();
				// 新しいCustomerクラスのインスタンス（顧客情報）を生成する
				Customer newCustomer = new Customer(custId, custName, kana, tel, address);
				// CustomerModifyActionクラスのインスタンスを生成する
				CustomerModifyAction customerModifyAction = new CustomerModifyAction();
				// executeメソッドに新しいCustomerクラスのインスタンス（顧客情報）を渡し，結果としてint型の値（更新した行数：１）を取得する
				int count = customerModifyAction.execute(newCustomer);
				if(count == 1) {
					if ("customerControl".equals(fromPage)) {

						// 一覧画面で完了メッセージを表示するため、
						// msgFlagをセッションに保存（redirect後も保持される）
						session.setAttribute("msgFlag", "modified");

						// PRGパターン（Post → Redirect → Get）を適用し、
						// 一覧再表示用のコマンドへリダイレクト
						response.sendRedirect(
							"KiddaLaController?command=CustomerControlDisplay");

						// sendRedirect後は処理を続行してはいけないためreturn
						return;

					// ------------------------------
					// 注文入力画面から遷移してきた場合
					// ------------------------------
					} else {

						// 注文入力画面で更新後の顧客情報を使用するため、
						// セッションに顧客情報を保存
						session.setAttribute("customer", newCustomer);

						// forward遷移のため、表示用メッセージはrequestに保存
						request.setAttribute("msgFlag", "modified");

						// 次画面に注文入力画面を設定
						nextPage = "OrderInput.jsp";
					}
				}
			} catch(Exception e) {
				// リクエストオブジェクト（スコープ）にエラーメッセージを登録する
				request.setAttribute(
						"errorMsg", e.getMessage());
				// 例外をキャッチした場合は次画面に"Error.jsp"を設定する
				nextPage = "Error.jsp";
			}
			break;
		}
		// 次のページへの転送
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}