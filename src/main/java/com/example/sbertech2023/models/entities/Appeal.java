package com.example.sbertech2023.models.entities;

import com.example.sbertech2023.models.enums.AppealStatus;
import com.example.sbertech2023.models.enums.RecordState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Tribushko Danil
 * @since 14.12.2023
 *
 * Сущность обращения
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "appeals")
public class Appeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Заголовок обращения
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * Адрес, на котором было зафиксированно нарушение
     */
    @Column(name = "address", nullable = false)
    private String address;

    /**
     * Текст обращения
     */
    @Column(length = 1000, name = "text", nullable = false)
    private String text;

    /**
     * Статус обращения
     */
    @Column(name = "status", nullable = false)
    private AppealStatus status;

    /**
     * Адрес изображения, на котором зафиксировано нарушение
     */
    @Column(name = "photo")
    private String photo;

    /**
     * Статус состояния обращения
     */
    @Column(name = "recordState", nullable = false)
    private RecordState recordState;

    @Column(name = "date_publish")
    private LocalDateTime datePublish;
    /**
     * Автор обращения
     */
    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    /**
     * Тип нарушения
     */
    @ManyToOne
    @JoinColumn(name = "appeal_type_id")
    private ViolationType type;

    /**
     * Микрорайон, в котором было зафиксированно нарушение
     */
    @ManyToOne
    @JoinColumn(name = "micro_district_id")
    private MicroDistrict microDistrict;

    /**
     * Район, в котором было зафиксированно нарушение
     */
    @ManyToOne
    @JoinColumn(name = "district")
    private District district;

    public Appeal(String title, String text, String address) {
        this.title = title;
        this.text = text;
        this.address = address;
        status = AppealStatus.WAITING;
        recordState = RecordState.ACTIVE;
        datePublish = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appeal appeal = (Appeal) o;
        return Objects.equals(id, appeal.id) &&
                Objects.equals(title, appeal.title) &&
                Objects.equals(text, appeal.text) &&
                status == appeal.status &&
                Objects.equals(author, appeal.author) &&
                Objects.equals(type, appeal.type) &&
                Objects.equals(microDistrict, appeal.microDistrict) &&
                Objects.equals(district, appeal.district);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, status, author, type, microDistrict, district);
    }
}
