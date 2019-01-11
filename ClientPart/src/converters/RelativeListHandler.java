package converters;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import dtos.requests.RelativeDetailsDTO;
import dtos.responses.PatientDetailsDTO;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class RelativeListHandler {
    private JSONArray responseArray;
    private HttpResponse<JsonNode> response;

    public RelativeListHandler(){
        this.setResponse(response);
    }

    public void setResponse(HttpResponse<JsonNode> response){
        this.response = response;
    }

    public HttpResponse<JsonNode> getResponse(){
        return response;
    }

    public HttpResponse<JsonNode> getRelativeDetails(Integer userId){
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.post("http://localhost:8080/relativedetails").header("Content-Type","application/json").body(userId).asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        System.out.println(response.getBody());
        setResponse(response);
        return response;
    }

    public void setResponseBodyArray(){
        responseArray = getResponse().getBody().getArray();
    }

    public int getResponseBodySize(){
        return responseArray.length();
    }
    public List<RelativeDetailsDTO> getListOfRelativeDetails(){
        int size = getResponseBodySize();
        List<RelativeDetailsDTO> relativeDetailsDTOS = new ArrayList<>();
        for(int i = 0 ; i<size;i++){

            RelativeDetailsDTO relativeDetailsDTO = null;

            relativeDetailsDTO = new RelativeDetailsDTO();
            relativeDetailsDTO.setRelativeFirstName((String) responseArray.getJSONObject(i).get("relativeFirstName"));
            relativeDetailsDTO.setRelativeLastName((String) responseArray.getJSONObject(i).get("relativeLastName"));
            relativeDetailsDTO.setPatientId((Integer) responseArray.getJSONObject(i).get("patientId"));
            relativeDetailsDTOS.add(relativeDetailsDTO);
        }
        System.out.println(relativeDetailsDTOS.size()+" TOTAL RECORDS");


        return relativeDetailsDTOS;
    }

    public String setRelativeAuthorized(Integer relativeId){
        HttpResponse<String> jsonResponse = null;
        try {
            jsonResponse = Unirest.post("http://localhost:8080/relativedetails/authorize").header("Content-Type","application/json").body(relativeId).asObject(String.class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return jsonResponse.getBody();
    }
}
