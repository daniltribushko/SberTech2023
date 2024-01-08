package com.example.sbertech2023.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Tribushko Danil
 * @since 07.01.2024
 *
 * Сущность типа мероприятия
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "event_types")
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "type", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Event> events;

    public EventType(String name){
        this.name = name;
        events = new TreeSet<>(Comparator.comparing(Event::getName));
    }

    public void addEvent(Event event){
        event.setType(this);
        events.add(event);
    }
}
