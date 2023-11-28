package com.example.sbertech2023.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Tribushko Danil
 * @since 28.11.2023
 *
 * Район Екатеринбурга
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "districts")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "district", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<MicroDistrict> microDistricts;

    public District(String name){
        this.name = name;
        microDistricts = new HashSet<>();
    }

    public void addMicroDistrict(MicroDistrict microDistrict){
        microDistrict.setDistrict(this);
        microDistricts.add(microDistrict);
    }

    public void removeMicroDistrict(MicroDistrict microDistrict){
        microDistrict.setDistrict(null);
        microDistricts.remove(microDistrict);
    }
}
