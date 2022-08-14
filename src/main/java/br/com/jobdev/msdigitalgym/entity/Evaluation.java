package br.com.jobdev.msdigitalgym.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Evaluation {

    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dateTime = LocalDateTime.now();

    @NotNull
    private double weight;

    @NotNull
    private double height;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    @NotNull
    private Customer customer;

}
