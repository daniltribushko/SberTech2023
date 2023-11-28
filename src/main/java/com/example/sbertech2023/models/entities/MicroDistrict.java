package com.example.sbertech2023.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Tribushko Danil
 * @since 28.11.2023
 *
 * Микрорайон Екатеринбурга
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "micro-districts")
public class MicroDistrict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    public MicroDistrict(String name){
        this.name = name;
    }
}
