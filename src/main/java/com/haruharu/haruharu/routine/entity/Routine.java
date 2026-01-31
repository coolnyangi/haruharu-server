package com.haruharu.haruharu.routine.entity;

import com.haruharu.haruharu.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "routines")
public class Routine extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Todo user 엔티티 완성 후 연결하기
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    @Column(nullable = false)
    private boolean isSuccess;

    // 생성자
    public Routine(String title, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isSuccess = false;
    }

    // 도메인 메서드
    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void changeEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void markSuccess() {
        this.isSuccess = true;
    }

    public void markFailure() {
        this.isSuccess = false;
    }
}
