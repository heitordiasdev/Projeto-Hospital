package br.com.catolicapb.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "call_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID patientId;
    private String patientName;
    private int priority;
    private LocalDateTime callTime;
}
