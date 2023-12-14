package com.example.sbertech2023.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
import java.util.TreeSet;

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

    /**
     * Название района
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Множество микрорайонов
     */
    @OneToMany(mappedBy = "district", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<MicroDistrict> microDistricts;

    /**
     * Обращения
     */
    @OneToMany(mappedBy = "district", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Appeal> appeals;

    public District(String name){
        this.name = name;
        microDistricts = new TreeSet<>();
        appeals = new TreeSet<>();
    }

    /**
     * Добавление микрорайона в множество
     *
     * @param microDistrict сущность микрорайона
     */
    public void addMicroDistrict(MicroDistrict microDistrict){
        microDistrict.setDistrict(this);
        microDistricts.add(microDistrict);
    }

    /**
     * Удаление микрорайона из множества
     *
     * @param microDistrict сущность микрорайона
     */
    public void removeMicroDistrict(MicroDistrict microDistrict){
        microDistrict.setDistrict(null);
        microDistricts.remove(microDistrict);
    }

    /**
     * Добавление обращения
     *
     * @param appeal сущность обращения
     */
    public void addAppeal(Appeal appeal){
        appeal.setDistrict(this);
        appeals.add(appeal);
    }
}
