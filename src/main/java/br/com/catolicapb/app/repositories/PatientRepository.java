package br.com.catolicapb.app.repositories;


import br.com.catolicapb.app.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
}
