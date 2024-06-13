package br.com.catolicapb.app.servicies;

import br.com.catolicapb.app.dto.PatientDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CallService {

    @Autowired
    private CallHistoryService callHistoryService;

    @RabbitListener(queues = "patient.queue")
    public void handlePatientCall(byte[] messageBytes) {
        try {
            String patientJson = new String(messageBytes);

            ObjectMapper objectMapper = new ObjectMapper();
            PatientDTO patientDTO = objectMapper.readValue(patientJson, PatientDTO.class);

            System.out.println("Chamando paciente: " + patientDTO.getName() + " com prioridade: " + patientDTO.getPriority());
            callHistoryService.logCall(patientDTO.getId(), patientDTO.getName(), patientDTO.getPriority());
        } catch (Exception e) {
            throw new RuntimeException("Failed to process message", e);
        }
    }
}
