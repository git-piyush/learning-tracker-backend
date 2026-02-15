package com.piyush.InventoryManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "vw_dashboard")
@org.hibernate.annotations.Immutable
public class Dashboard {

    @Id
    private Long id;

    @Column(name="totalquestions")
    private Long totalquestions;

    @Transient
    private Long totalQuestionsAddedByYou;
}
