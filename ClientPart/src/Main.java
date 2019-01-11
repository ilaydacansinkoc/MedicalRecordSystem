import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import layout.LoginPage;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        /*try {
            HttpResponse<JsonNode> jsonResponse
                    = Unirest.post("http://localhost:8080/api/login")
                    .header("accept", "application/json")
                    .body("güzeldadas")
                    .asJson();
            System.out.println(jsonResponse.getBody().toString());
        } catch (UnirestException e) {
            e.printStackTrace();
        }*/

        LoginPage loginPage = new LoginPage();
        loginPage.run();
    }
}
