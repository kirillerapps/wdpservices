package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"answerCodeWhatIsTheProblemWithFireProtection", "answerCodeWhatIsTheProblemWithTheRoof",
        "answerCodeWhatIsTheProblemWithElectricity", "numberOfDamagedPieces",
        "answerCodeWhatIsTheProblemWithTheWallOrWindow", "answerCodeWhatIsTheProblemWithTheHVAC",
        "answerCodeWhatIsTheProblemWithTheGate", "answerTextWhatIsTheGateNumber",
        "answerCodeWhatIsTheProblemWithPlumbingSewerageOrKitchen", "answerCodeWhatIsTheProblemWithTheSewerSystem",
        "answerCodeWhatIsTheProblemAtTheOursideAreaOrFence", "answerCodeWhatIsTheProblemWithTheFloorOrDoor" })

public class OrderDetailsDTO extends OrderOverViewDTO {
    @JsonProperty("answerCodeWhatIsTheProblemWithFireProtection")
    private String answerCodeWhatIsTheProblemWithFireProtection ="";
    @JsonProperty("answerCodeWhatIsTheProblemWithTheRoof")
    private String answerCodeWhatIsTheProblemWithTheRoof ="";
    @JsonProperty("answerCodeWhatIsTheProblemWithElectricity")
    private String answerCodeWhatIsTheProblemWithElectricity ="";
    @JsonProperty("numberOfDamagedPieces")
    private Integer numberOfDamagedPieces= 0;
    @JsonProperty("answerCodeWhatIsTheProblemWithTheWallOrWindow")
    private String answerCodeWhatIsTheProblemWithTheWallOrWindow ="";
    @JsonProperty("answerCodeWhatIsTheProblemWithTheHVAC")
    private String answerCodeWhatIsTheProblemWithTheHVAC ="";
    @JsonProperty("answerCodeWhatIsTheProblemWithTheGate")
    private String answerCodeWhatIsTheProblemWithTheGate ="";
    @JsonProperty("answerTextWhatIsTheGateNumber")
    private String answerTextWhatIsTheGateNumber ="";
    @JsonProperty("answerCodeWhatIsTheProblemWithPlumbingSewerageOrKitchen")
    private String answerCodeWhatIsTheProblemWithPlumbingSewerageOrKitchen ="";
    @JsonProperty("answerCodeWhatIsTheProblemWithTheSewerSystem")
    private String answerCodeWhatIsTheProblemWithTheSewerSystem ="";
    @JsonProperty("answerCodeWhatIsTheProblemAtTheOursideAreaOrFence")
    private String answerCodeWhatIsTheProblemAtTheOursideAreaOrFence ="";
    @JsonProperty("answerCodeWhatIsTheProblemWithTheFloorOrDoor")
    private String answerCodeWhatIsTheProblemWithTheFloorOrDoor ="";

    @JsonProperty("answerCodeWhatIsTheProblemWithFireProtection")
    public String getAnswerCodeWhatIsTheProblemWithFireProtection() {
        return answerCodeWhatIsTheProblemWithFireProtection;
    }

    @JsonProperty("answerCodeWhatIsTheProblemWithFireProtection")
    public void setAnswerCodeWhatIsTheProblemWithFireProtection(String answerCodeWhatIsTheProblemWithFireProtection) {
        this.answerCodeWhatIsTheProblemWithFireProtection = answerCodeWhatIsTheProblemWithFireProtection;
    }

    @JsonProperty("answerCodeWhatIsTheProblemWithTheRoof")
    public String getAnswerCodeWhatIsTheProblemWithTheRoof() {
        return answerCodeWhatIsTheProblemWithTheRoof;
    }

    @JsonProperty("answerCodeWhatIsTheProblemWithTheRoof")
    public void setAnswerCodeWhatIsTheProblemWithTheRoof(String answerCodeWhatIsTheProblemWithTheRoof) {
        this.answerCodeWhatIsTheProblemWithTheRoof = answerCodeWhatIsTheProblemWithTheRoof;
    }

    @JsonProperty("answerCodeWhatIsTheProblemWithElectricity")
    public String getAnswerCodeWhatIsTheProblemWithElectricity() {
        return answerCodeWhatIsTheProblemWithElectricity;
    }

    @JsonProperty("answerCodeWhatIsTheProblemWithElectricity")
    public void setAnswerCodeWhatIsTheProblemWithElectricity(String answerCodeWhatIsTheProblemWithElectricity) {
        this.answerCodeWhatIsTheProblemWithElectricity = answerCodeWhatIsTheProblemWithElectricity;
    }

    @JsonProperty("numberOfDamagedPieces")
    public Integer getNumberOfDamagedPieces() {
        return numberOfDamagedPieces;
    }

    @JsonProperty("numberOfDamagedPieces")
    public void setNumberOfDamagedPieces(Integer numberOfDamagedPieces) {
        this.numberOfDamagedPieces = numberOfDamagedPieces;
    }

    @JsonProperty("answerCodeWhatIsTheProblemWithTheWallOrWindow")
    public String getAnswerCodeWhatIsTheProblemWithTheWallOrWindow() {
        return answerCodeWhatIsTheProblemWithTheWallOrWindow;
    }

    @JsonProperty("answerCodeWhatIsTheProblemWithTheWallOrWindow")
    public void setAnswerCodeWhatIsTheProblemWithTheWallOrWindow(String answerCodeWhatIsTheProblemWithTheWallOrWindow) {
        this.answerCodeWhatIsTheProblemWithTheWallOrWindow = answerCodeWhatIsTheProblemWithTheWallOrWindow;
    }

    @JsonProperty("answerCodeWhatIsTheProblemWithTheHVAC")
    public String getAnswerCodeWhatIsTheProblemWithTheHVAC() {
        return answerCodeWhatIsTheProblemWithTheHVAC;
    }

    @JsonProperty("answerCodeWhatIsTheProblemWithTheHVAC")
    public void setAnswerCodeWhatIsTheProblemWithTheHVAC(String answerCodeWhatIsTheProblemWithTheHVAC) {
        this.answerCodeWhatIsTheProblemWithTheHVAC = answerCodeWhatIsTheProblemWithTheHVAC;
    }

    @JsonProperty("answerCodeWhatIsTheProblemWithTheGate")
    public String getAnswerCodeWhatIsTheProblemWithTheGate() {
        return answerCodeWhatIsTheProblemWithTheGate;
    }

    @JsonProperty("answerCodeWhatIsTheProblemWithTheGate")
    public void setAnswerCodeWhatIsTheProblemWithTheGate(String answerCodeWhatIsTheProblemWithTheGate) {
        this.answerCodeWhatIsTheProblemWithTheGate = answerCodeWhatIsTheProblemWithTheGate;
    }

    @JsonProperty("answerTextWhatIsTheGateNumber")
    public String getAnswerTextWhatIsTheGateNumber() {
        return answerTextWhatIsTheGateNumber;
    }

    @JsonProperty("answerTextWhatIsTheGateNumber")
    public void setAnswerTextWhatIsTheGateNumber(String answerTextWhatIsTheGateNumber) {
        this.answerTextWhatIsTheGateNumber = answerTextWhatIsTheGateNumber;
    }

    @JsonProperty("answerCodeWhatIsTheProblemWithPlumbingSewerageOrKitchen")
    public String getAnswerCodeWhatIsTheProblemWithPlumbingSewerageOrKitchen() {
        return answerCodeWhatIsTheProblemWithPlumbingSewerageOrKitchen;
    }

    @JsonProperty("answerCodeWhatIsTheProblemWithPlumbingSewerageOrKitchen")
    public void setAnswerCodeWhatIsTheProblemWithPlumbingSewerageOrKitchen(
            String answerCodeWhatIsTheProblemWithPlumbingSewerageOrKitchen) {
        this.answerCodeWhatIsTheProblemWithPlumbingSewerageOrKitchen = answerCodeWhatIsTheProblemWithPlumbingSewerageOrKitchen;
    }

    @JsonProperty("answerCodeWhatIsTheProblemWithTheSewerSystem")
    public String getAnswerCodeWhatIsTheProblemWithTheSewerSystem() {
        return answerCodeWhatIsTheProblemWithTheSewerSystem;
    }

    @JsonProperty("answerCodeWhatIsTheProblemWithTheSewerSystem")
    public void setAnswerCodeWhatIsTheProblemWithTheSewerSystem(String answerCodeWhatIsTheProblemWithTheSewerSystem) {
        this.answerCodeWhatIsTheProblemWithTheSewerSystem = answerCodeWhatIsTheProblemWithTheSewerSystem;
    }

    @JsonProperty("answerCodeWhatIsTheProblemAtTheOursideAreaOrFence")
    public String getAnswerCodeWhatIsTheProblemAtTheOursideAreaOrFence() {
        return answerCodeWhatIsTheProblemAtTheOursideAreaOrFence;
    }

    @JsonProperty("answerCodeWhatIsTheProblemAtTheOursideAreaOrFence")
    public void setAnswerCodeWhatIsTheProblemAtTheOursideAreaOrFence(
            String answerCodeWhatIsTheProblemAtTheOursideAreaOrFence) {
        this.answerCodeWhatIsTheProblemAtTheOursideAreaOrFence = answerCodeWhatIsTheProblemAtTheOursideAreaOrFence;
    }

    @JsonProperty("answerCodeWhatIsTheProblemWithTheFloorOrDoor")
    public String getAnswerCodeWhatIsTheProblemWithTheFloorOrDoor() {
        return answerCodeWhatIsTheProblemWithTheFloorOrDoor;
    }

    @JsonProperty("answerCodeWhatIsTheProblemWithTheFloorOrDoor")
    public void setAnswerCodeWhatIsTheProblemWithTheFloorOrDoor(String answerCodeWhatIsTheProblemWithTheFloorOrDoor) {
        this.answerCodeWhatIsTheProblemWithTheFloorOrDoor = answerCodeWhatIsTheProblemWithTheFloorOrDoor;
    }

}

//     private List<Answer> answersList = new ArrayList<>();

//     public List<Answer> getAnswers() {
//         return answersList;
//     }

//     public void setAnswers(List<Answer> answers) {
//         this.answersList = answers;
//     }

// public class Answer {
//     @JsonProperty("answerOptionCode")
//     private String answerOptionCode;
//     @JsonProperty("answerOptionName")
//     private String answerOptionName;
//     @JsonProperty("questionCode")
//     private String questionCode;
//     @JsonProperty("questionName")
//     private String questionName;

//    @JsonProperty("questionnaireCode")
//     private String questionnaireCode;
//     @JsonProperty("questionnaireName")
//     private String questionnaireName;

//  public String getAnswerOptionCode() {
//         return answerOptionCode;
//     }

//     public void setAnswerOptionCode(String answerOptionCode) {
//         this.answerOptionCode = answerOptionCode;
//     }

//     public String getAnswerOptionName() {
//         return answerOptionName;
//     }

//     public void setAnswerOptionName(String answerOptionName) {
//         this.answerOptionName = answerOptionName;
//     }

//     public String getQuestionCode() {
//         return questionCode;
//     }

//     public void setQuestionCode(String questionCode) {
//         this.questionCode = questionCode;
//     }

//     public String getQuestionName() {
//         return questionName;
//     }

//     public void setQuestionName(String questionName) {
//         this.questionName = questionName;
//     }

//     public String getQuestionnaireCode() {
//         return questionnaireCode;
//     }

//     public void setQuestionnaireCode(String questionnaireCode) {
//         this.questionnaireCode = questionnaireCode;
//     }

//     public String getQuestionnaireName() {
//         return questionnaireName;
//     }

//     public void setQuestionnaireName(String questionnaireName) {
//         this.questionnaireName = questionnaireName;
//     }
// }

