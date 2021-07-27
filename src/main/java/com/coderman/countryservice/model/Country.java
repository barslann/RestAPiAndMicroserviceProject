package com.coderman.countryservice.model;

import lombok.*;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "country")
public class Country {
    @Id
    private int id;
    @Column(name = "countryName")
    @NonNull
    private  String countryName;
    @Column(name = "countryCapital")
    @NonNull
    private  String countryCapital;
}
