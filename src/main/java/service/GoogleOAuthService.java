package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.GoogleUser;

public class GoogleOAuthService {

    // ★ Google Cloud Console で取得した値に変更
	private static final String CLIENT_ID =
		    System.getenv("GOOGLE_CLIENT_ID");

		private static final String CLIENT_SECRET =
		    System.getenv("GOOGLE_CLIENT_SECRET");

		private static final String REDIRECT_URI =
		    System.getenv("GOOGLE_REDIRECT_URI");

    /**
     * code → token → userinfo
     */
    public GoogleUser getUserInfo(String code) throws Exception {

        // ① code → access_token
        String tokenResponse = getAccessToken(code);
        JsonObject tokenJson =
            JsonParser.parseString(tokenResponse).getAsJsonObject();

        String accessToken = tokenJson.get("access_token").getAsString();

        // ② access_token → userinfo
        return getGoogleUser(accessToken);
    }

    // ===============================
    // code → access_token
    // ===============================
    private String getAccessToken(String code) throws Exception {
    	if (CLIENT_ID == null || CLIENT_SECRET == null || REDIRECT_URI == null) {
    	    throw new IllegalStateException(
    	        "Google OAuth の環境変数が設定されていません");
    	}
        URL url = new URL("https://oauth2.googleapis.com/token");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");

        String params =
            "code=" + code +
            "&client_id=" + CLIENT_ID +
            "&client_secret=" + CLIENT_SECRET +
            "&redirect_uri=" + REDIRECT_URI +
            "&grant_type=authorization_code";

        try (OutputStream os = conn.getOutputStream()) {
            os.write(params.getBytes("UTF-8"));
        }

        BufferedReader br = new BufferedReader(
            new InputStreamReader(conn.getInputStream(), "UTF-8")
        );

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        return sb.toString();
    }

    // ===============================
    // access_token → userinfo
    // ===============================
    private GoogleUser getGoogleUser(String accessToken) throws Exception {

        URL url = new URL(
            "https://www.googleapis.com/oauth2/v2/userinfo"
        );
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty(
            "Authorization", "Bearer " + accessToken
        );

        BufferedReader br = new BufferedReader(
            new InputStreamReader(conn.getInputStream(), "UTF-8")
        );

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        JsonObject json =
            JsonParser.parseString(sb.toString()).getAsJsonObject();

        GoogleUser user = new GoogleUser();
        user.setEmail(json.get("email").getAsString());
        user.setName(json.get("name").getAsString());

        return user;
    }
}
