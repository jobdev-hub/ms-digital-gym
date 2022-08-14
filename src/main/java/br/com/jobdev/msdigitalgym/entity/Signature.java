package br.com.jobdev.msdigitalgym.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Signature {

    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createAt = LocalDateTime.now();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonInclude(NON_NULL)
    private LocalDateTime updateAt = null;

    private Boolean active = true;

}
