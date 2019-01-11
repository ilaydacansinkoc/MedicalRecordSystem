package converters;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import dtos.responses.PatientDetailsDTO;

public class PatientDetailsAddingHandler {
    public PatientDetailsDTO addDetailsToTable(PatientDetailsDTO patientDetailsDTO){
        HttpResponse<PatientDetailsDTO> response = null;
        try {
            response = Unirest.post("http://localhost:8080/add_patient_details").header("Content-Type","application/json").body(patientDetailsDTO).asObject(PatientDetailsDTO.class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return response.getBody();
    }

}
