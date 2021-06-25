package com.coderman.countryservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "country")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "countryName")
    private String countryName;
    @Column(name = "countryCapital")
    private String countryCapital;
}
