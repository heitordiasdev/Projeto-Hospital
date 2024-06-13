package br.com.catolicapb.app.controllers;

import br.com.catolicapb.app.dto.PatientDTO;
import br.com.catolicapb.app.entities.Patient;
import br.com.catolicapb.app.servicies.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<PatientDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    @PostMapping
    public PatientDTO registerPatient(@RequestBody Patient patient) {
        return patientService.registerPatient(patient);
    }

    @PutMapping("/{id}/priority")
    public PatientDTO updatePriority(@PathVariable UUID id, @RequestParam int priority) {
        return patientService.updatePriority(id, priority);
    }

    @GetMapping("/{id}")
    public PatientDTO getPatient(@PathVariable UUID id) {
        return patientService.getPatient(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok().build();
    }
}
