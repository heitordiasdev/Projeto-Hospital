package br.com.catolicapb.app.servicies;

import br.com.catolicapb.app.dto.PatientDTO;
import br.com.catolicapb.app.entities.Patient;
import br.com.catolicapb.app.repositories.PatientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Transactional
    public PatientDTO registerPatient(Patient patient) {
        Patient savedPatient = patientRepository.save(patient);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String patientJson = objectMapper.writeValueAsString(new PatientDTO(savedPatient));

            System.out.println("Mensagem JSON: " + patientJson);

            Message message = MessageBuilder.withBody(patientJson.getBytes())
                    .setContentType("application/json")
                    .build();

            rabbitTemplate.send("patient.exchange", "patient.new", message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert PatientDTO to JSON", e);
        }
        return new PatientDTO(savedPatient);
    }



    @Transactional
    public PatientDTO updatePriority(UUID id, int priority) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        patient.setPriority(priority);
        Patient updatedPatient = patientRepository.save(patient);
        rabbitTemplate.convertAndSend("patient.exchange", "patient.updated", new PatientDTO(updatedPatient));
        return new PatientDTO(updatedPatient);
    }

    @Transactional(readOnly = true)
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream().map(PatientDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public PatientDTO getPatient(UUID id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        return new PatientDTO(patient);
    }

    @Transactional
    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
}
