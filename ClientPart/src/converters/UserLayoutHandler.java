package converters;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import dtos.requests.UserCredentialsDTO;

public class UserLayoutHandler {

    public int selectUserLayoutByRole(UserCredentialsDTO userCredentialsDTO){
        HttpResponse<Integer> response = null;
        try {
            response = Unirest.post("http://localhost:8080/users").header("Content-Type","application/json").body(userCredentialsDTO).asObject(Integer.class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return response.getBody();
    }

    public int getUserId(UserCredentialsDTO userCredentialsDTO){
        HttpResponse<Integer> response = null;
        try {
            response = Unirest.post("http://localhost:8080/users/userId").header("Content-Type","application/json").body(userCredentialsDTO).asObject(Integer.class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return response.getBody();
    }

    public String getAuthorizedStatus(Integer userId){
        HttpResponse<String> response = null;
        try {
            response = Unirest.post("http://localhost:8080/users/authorized").header("Content-Type","application/json").body(userId).asObject(String.class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return response.getBody();
    }

}
