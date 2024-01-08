package com.example.sbertech2023.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Trbishko Danil
 * @since 07.01.2024
 *
 * Сущность мероприятия
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @Column(name = "max_count_participant", nullable = false)
    private Integer maxCountParticipant;

    @Column(name = "address", nullable = false)
    private String address;

    @ManyToOne
    private User creator;

    @ManyToMany
    private List<User> participants;

    @ManyToOne
    private EventType type;

    public Event(String name, LocalDateTime startDate, String description, Integer maxCountParticipant, String address){
        this.name = name;
        this.startDate = startDate;
        this.description = description;
        this.maxCountParticipant = maxCountParticipant;
        this.address = address;
    }
}
