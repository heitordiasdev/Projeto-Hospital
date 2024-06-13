package br.com.catolicapb.app.repositories;


import br.com.catolicapb.app.entities.CallHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CallHistoryRepository extends JpaRepository<CallHistory, UUID> {
}
