package com.br.senac.planey.domain.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "DESPESA")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(precision = 10, scale = 2, name = "VALOR")
    private double valor;

    @Column(name = "DATA")
    private LocalDateTime data;

    @Column(name = "TAG")
    private String tag;
}
