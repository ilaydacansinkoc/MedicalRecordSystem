package converters;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import dtos.requests.DoctorRegisterDTO;
import dtos.requests.NurseRegisterDTO;
import dtos.responses.DoctorCredentialsDTO;
import dtos.responses.NurseCredentialsDTO;
import dtos.responses.PatientCredentialsDTO;
import models.Doctor;
import models.Nurse;
import org.json.JSONArray;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class AdminMenuHandler {

    private JSONArray responseArrayForDoctors;
    private JSONArray responseArrayForNurses;

    public HttpResponse<JsonNode> getDoctorsResponse(){
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.get("http://localhost:8080/admin/doctors").header("Content-Type","application/json").asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

    public void setResponseBodyArrayForDoctors(){
        responseArrayForDoctors = getDoctorsResponse().getBody().getArray();
    }

    public int getResponseBodySizeForDoctors(){
        return responseArrayForDoctors.length();
    }

    public List<Doctor> getListOfDoctors(){
        int size = getResponseBodySizeForDoctors();
        List<Doctor> doctors = new ArrayList<>();
        for(int i = 0 ; i<size;i++){

            Doctor doctor = null;

            doctor = new Doctor((Integer)responseArrayForDoctors.getJSONObject(i).get("id"),
                    (String) responseArrayForDoctors.getJSONObject(i).get("firstName"),
                    (String) responseArrayForDoctors.getJSONObject(i).get("lastName"));

            doctors.add(doctor);
        }

        return doctors;
    }

    // NURSE PART

    public HttpResponse<JsonNode> getNursesResponse(){
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.get("http://localhost:8080/admin/nurses").header("Content-Type","application/json").asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

    public void setResponseBodyArrayForNurses(){
        responseArrayForNurses = getNursesResponse().getBody().getArray();
    }

    public int getResponseBodySizeForNurses(){
        return responseArrayForNurses.length();
    }

    public List<Nurse> getListOfNurses(){
        int size = getResponseBodySizeForNurses();
        List<Nurse> nurses = new ArrayList<>();
        for(int i = 0 ; i<size;i++){

            Nurse nurse = null;

            nurse = new Nurse((Integer)responseArrayForNurses.getJSONObject(i).get("id"),
                    (String) responseArrayForNurses.getJSONObject(i).get("firstName"),
                    (String) responseArrayForNurses.getJSONObject(i).get("lastName"),
                    (String) responseArrayForNurses.getJSONObject(i).get("birthDate"));

            nurses.add(nurse);
        }

        return nurses;
    }

    public DoctorCredentialsDTO addDoctorAndGetCredentials(DoctorRegisterDTO doctorRegisterDTO){
        HttpResponse<DoctorCredentialsDTO> response = null;
        try {
            response = Unirest.post("http://localhost:8080/admin/add_doctor").header("Content-Type","application/json").body(doctorRegisterDTO).asObject(DoctorCredentialsDTO.class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return response.getBody();
    }

    public NurseCredentialsDTO addNurseAndGetCredentials(NurseRegisterDTO nurseRegisterDTO){
        HttpResponse<NurseCredentialsDTO> response = null;
        try {
            response = Unirest.post("http://localhost:8080/admin/add_nurse").header("Content-Type","application/json").body(nurseRegisterDTO).asObject(NurseCredentialsDTO.class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return response.getBody();
    }

    public String setDoctorAuthorized(Integer doctorId){
        HttpResponse<String> jsonResponse = null;
        try {
            jsonResponse = Unirest.post("http://localhost:8080/admin/authorize_doctor").header("Content-Type","application/json").body(doctorId).asObject(String.class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return jsonResponse.getBody();
    }

    public String setNurseAuthorized(Integer nurseId){
        HttpResponse<String> jsonResponse = null;
        try {
            jsonResponse = Unirest.post("http://localhost:8080/admin/authorize_nurse").header("Content-Type","application/json").body(nurseId).asObject(String.class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return jsonResponse.getBody();
    }
}
