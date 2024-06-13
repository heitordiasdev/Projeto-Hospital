package br.com.catolicapb.app.servicies;

import br.com.catolicapb.app.entities.CallHistory;
import br.com.catolicapb.app.repositories.CallHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CallHistoryService {

    @Autowired
    private CallHistoryRepository callHistoryRepository;

    @Transactional
    public void logCall(UUID patientId, String patientName, int priority) {
        CallHistory callHistory = new CallHistory();
        callHistory.setPatientId(patientId);
        callHistory.setPatientName(patientName);
        callHistory.setPriority(priority);
        callHistory.setCallTime(LocalDateTime.now());
        callHistoryRepository.save(callHistory);
    }
}
