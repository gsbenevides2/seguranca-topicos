package seguranca.comum;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.google.gson.Gson;

import java.util.HashMap;
import java.lang.StringBuffer;
import java.io.InputStreamReader;

public class RecaptchaValidator {
    private static String secretToken = "6Lc7TuooAAAAAF18Nkc8QH4Xn_uFzp6qK_m5CQm8";

    public static boolean validate(String responseToken) {
        try {
            // Prepare Parameters
            Map<String, String> parameters = new HashMap<>();
            parameters.put("secret", secretToken);
            parameters.put("response", responseToken);

            // Prepare Request
            URL url = new URL("https://www.google.com/recaptcha/api/siteverify");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            // Add Parameters to Request
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
            out.flush();
            out.close();

            // Make Request
            con.connect();

            // Read Status Code
            int status = con.getResponseCode();

            if (status != HttpURLConnection.HTTP_OK)
                return false;

            // Read Reesponse
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            String response = content.toString();

            // Disconect
            con.disconnect();

            // Check response is json
            String contentType = con.getHeaderField("Content-Type");
            if (contentType.contains("application/json") == false)
                return false;
            Gson gson = new Gson();
            RecaptchaResponse recaptchaResponse = gson.fromJson(response, RecaptchaResponse.class);
            return recaptchaResponse.getSuccess();
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao fazer a requisição");
            System.out.println(e);
            return false;
        }

    }
}