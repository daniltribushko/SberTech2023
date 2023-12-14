package com.example.sbertech2023.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Tribushko Danil\
 * @since 14.12.2023
 *
 * Сущность типа нарушения
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "appeal_types")
public class ViolationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Название типа нарушения
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Множество обращений
     */
    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Appeal> appeals;

    public ViolationType(String name){
        this.name = name;
        appeals = new TreeSet<>();
    }

    /**
     * Добавление обращения в множество
     * @param appeal сущность обращения
     */
    public void addAppeal(Appeal appeal){
        appeal.setType(this);
        appeals.add(appeal);
    }
}
