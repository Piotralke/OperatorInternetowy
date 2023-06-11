package pl.psk.upc.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "notices")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long notice_id;

    @Column(name = "uuid")
    UUID uuid;

    @Column(name = "notice_date")
    ZonedDateTime noticeDate;

    @Column(name = "description")
    String description;

    @Column(name = "is_clicked")
    boolean isClicked;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    ClientAccountEntity clientAccountEntity;

}
