package com.br.senac.planey.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "DESPESA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(precision = 10, scale = 2, name = "VALOR")
    private Double valor;

    private LocalDateTime data;

    @Column(name = "TAG")
    private String tag;

    @ManyToOne
    @JoinColumn(referencedColumnName = "USERID")
    private Usuario usuario;
}
