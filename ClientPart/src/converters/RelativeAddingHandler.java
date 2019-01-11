package converters;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import dtos.requests.RelativeDetailsDTO;
import dtos.requests.RelativeRegisterDTO;
import dtos.responses.PatientCredentialsDTO;
import dtos.responses.RelativeCredentialsDTO;

public class RelativeAddingHandler {

    public RelativeCredentialsDTO addRelativeAndGetCredentials(RelativeDetailsDTO relativeDetailsDTO){
        HttpResponse<RelativeCredentialsDTO> response = null;
        //System.out.println(relativeRegisterDTO.getPatientId()+ relativeRegisterDTO.getFirstName()+" DR ID, LN");

        try {
            response = Unirest.post("http://localhost:8080/relativedetails/getcredentials").header("Content-Type","application/json").body(relativeDetailsDTO).asObject(RelativeCredentialsDTO.class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return response.getBody();
    }
}
