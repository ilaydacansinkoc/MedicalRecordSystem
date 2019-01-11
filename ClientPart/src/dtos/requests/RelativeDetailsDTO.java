package dtos.requests;

public class RelativeDetailsDTO {
    private int patientId;
    private String relativeFirstName;
    private String relativeLastName;

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getRelativeFirstName() {
        return relativeFirstName;
    }

    public void setRelativeFirstName(String relativeFirstName) {
        this.relativeFirstName = relativeFirstName;
    }

    public String getRelativeLastName() {
        return relativeLastName;
    }

    public void setRelativeLastName(String relativeLastName) {
        this.relativeLastName = relativeLastName;
    }
}
