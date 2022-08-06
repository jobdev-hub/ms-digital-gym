package br.com.jobdev.msdigitalgym.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    private UUID id;

    private String name;

    @Column(unique = true)
    private String cpf;

    private String district;

    private LocalDate birthDate;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PhysicalEvaluation> assessmentList = new ArrayList<>();

}
