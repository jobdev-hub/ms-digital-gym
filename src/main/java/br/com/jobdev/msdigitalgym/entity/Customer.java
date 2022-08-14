package br.com.jobdev.msdigitalgym.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @GeneratedValue(generator = "UUIDGenerator")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @NotNull
    private String name;

    @Column(unique = true)
    @CPF
    private String cpf;

    @NotNull
    private String district;

    @NotNull
    private LocalDate birthDate;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Evaluation> evaluations = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "signature_id")
    private Signature signature;
}
