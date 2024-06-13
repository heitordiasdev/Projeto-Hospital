package br.com.catolicapb.app.dto;

import br.com.catolicapb.app.entities.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private UUID id;
    private String name;
    private int priority;
    private String conditionDescription;

    public PatientDTO(Patient patient) {
        this.id = patient.getId();
        this.name = patient.getName();
        this.priority = patient.getPriority();
        this.conditionDescription = patient.getConditionDescription();
    }
}
