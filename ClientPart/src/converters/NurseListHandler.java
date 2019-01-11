package converters;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import models.Nurse;
import models.Patient;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class NurseListHandler {

    private JSONArray responseArray;

    public HttpResponse<JsonNode> getNursesResponse(){
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.get("http://localhost:8080/nurses").asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

    public void setResponseBodyArray(){
        responseArray = getNursesResponse().getBody().getArray();
    }

    public int getResponseBodySize(){
        return responseArray.length();
    }

    public List<Nurse> getListOfNurses(){
        int size = getResponseBodySize();
        List<Nurse> nurses = new ArrayList<>();
        for(int i = 0 ; i<size;i++){

            Nurse nurse = null;

            nurse = new Nurse((Integer)responseArray.getJSONObject(i).get("id"),
                    (String) responseArray.getJSONObject(i).get("firstName"),
                    (String) responseArray.getJSONObject(i).get("lastName"),
                    responseArray.getJSONObject(i).get("birthDate").toString());

            nurses.add(nurse);
        }

        return nurses;
    }

    public String setNurseAuthorized(Integer nurseId){
        HttpResponse<String> jsonResponse = null;
        try {
            jsonResponse = Unirest.post("http://localhost:8080/nurses/authorize").header("Content-Type","application/json").body(nurseId).asObject(String.class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return jsonResponse.getBody();
    }
}
