package converters;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import dtos.responses.PatientCredentialsDTO;
import dtos.responses.PatientDetailsDTO;
import models.Nurse;
import org.json.JSONArray;
import org.json.JSONString;

import java.util.ArrayList;
import java.util.List;

public class PatientDetailsHandler {

    private JSONArray responseArray;
    private HttpResponse<JsonNode> response;

    public PatientDetailsHandler(){
        this.setResponse(response);
    }

    public HttpResponse<JsonNode> getPatientDetails(Integer userId){
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.post("http://localhost:8080/patientdetails").header("Content-Type","application/json").body(userId).asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        System.out.println(response.getBody());
        setResponse(response);
        return response;
    }

    public void setResponse(HttpResponse<JsonNode> response){
        this.response = response;
    }

    public HttpResponse<JsonNode> getResponse(){
        return response;
    }

    public void setResponseBodyArray(){
        responseArray = getResponse().getBody().getArray();
    }

    public int getResponseBodySize(){
        return responseArray.length();
    }
    public List<PatientDetailsDTO> getListOfPatientDetails(){
        int size = getResponseBodySize();
        List<PatientDetailsDTO> patientDetailsDTOS = new ArrayList<>();
        for(int i = 0 ; i<size;i++){

            PatientDetailsDTO patientDetailsDTO = null;

            patientDetailsDTO = new PatientDetailsDTO();
            patientDetailsDTO.setPrescription((String) responseArray.getJSONObject(i).get("prescription"));
            patientDetailsDTO.setDiagnostics((String) responseArray.getJSONObject(i).get("diagnostics"));
            patientDetailsDTO.setDoctorId((Integer) responseArray.getJSONObject(i).get("doctorId"));
            patientDetailsDTOS.add(patientDetailsDTO);
        }
        System.out.println(patientDetailsDTOS.size()+" TOTAL RECORDS");


        return patientDetailsDTOS;
    }
}
