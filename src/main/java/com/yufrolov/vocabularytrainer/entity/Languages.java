package com.yufrolov.vocabularytrainer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "languages")
public class Languages {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "language")
    private List<Words> words;
}
