package converters;

import com.mashape.unirest.http.Headers;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import dtos.requests.PatientRegisterDTO;
import dtos.responses.PatientCredentialsDTO;
import dtos.responses.PatientDetailsDTO;
import layout.DoctorMenu;
import layout.PatientDetailsMenu;
import models.Patient;
import org.apache.http.entity.mime.Header;
import org.json.JSONArray;

import javax.print.Doc;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DoctorMenuHandler {

    private JSONArray responseArray;
    private Integer doctorId;

    // REQUEST - RESPONSE PART

    public DoctorMenuHandler(){
        responseArray = null;
    }

    public HttpResponse<JsonNode> getPatientsResponse(){
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.post("http://localhost:8080/patients/bydoctor").header("Content-Type","application/json").body(this.getDoctorId()).asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

    public void setDoctorId(Integer doctorId){
        this.doctorId=doctorId;
    }

    public Integer getDoctorId(){
        return doctorId;
    }

    public PatientCredentialsDTO addPatientAndGetCredentials(PatientRegisterDTO patientRegisterDTO){
        HttpResponse<PatientCredentialsDTO> response = null;
        System.out.println(patientRegisterDTO.getDoctorId()+ patientRegisterDTO.getFirstName()+" DR ID, LN");
        try {
            response = Unirest.post("http://localhost:8080/patients").header("Content-Type","application/json").body(patientRegisterDTO).asObject(PatientCredentialsDTO.class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return response.getBody();
    }


    public void setResponseBodyArray(){
        responseArray = getPatientsResponse().getBody().getArray();
    }

    public int getResponseBodySize(){
        return responseArray.length();
    }

    public List<Patient> getListOfPatients(){
        int size = getResponseBodySize();
        List<Patient> patientList = new ArrayList<>();
        for(int i = 0 ; i<size;i++){

            Patient patient = null;

            patient = new Patient((Integer)responseArray.getJSONObject(i).get("id"),
                    (String) responseArray.getJSONObject(i).get("firstName"),
                    (String) responseArray.getJSONObject(i).get("lastName"),
                    responseArray.getJSONObject(i).get("birthDate").toString());

            patientList.add(patient);
        }

        return patientList;
    }

    public JSONArray getResponseArray() {
        return responseArray;
    }

    public void setResponseArray(JSONArray responseArray) {
        this.responseArray = responseArray;
    }

    public String setPatientAuthorized(Integer patientId){
        HttpResponse<String> jsonResponse = null;
        try {
            jsonResponse = Unirest.post("http://localhost:8080/users/authorize").header("Content-Type","application/json").body(patientId).asObject(String.class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return jsonResponse.getBody();
    }
}
