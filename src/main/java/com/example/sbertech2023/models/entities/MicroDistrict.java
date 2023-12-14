package com.example.sbertech2023.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
import java.util.Set;

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

    /**
     * Название микрорайона
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Сущность района, в котором находится микрорайон
     */
    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    /**
     * Множество обращений
     */
    @OneToMany(mappedBy = "microDistrict")
    private Set<Appeal> appeals;

    public MicroDistrict(String name){
        this.name = name;
    }

    /**
     * Добавление обращения в множество
     *
     * @param appeal сущность обращения
     */
    public void addAppeal(Appeal appeal){
        appeal.setMicroDistrict(this);
        appeals.add(appeal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MicroDistrict that = (MicroDistrict) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
