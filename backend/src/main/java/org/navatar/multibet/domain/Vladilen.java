package org.navatar.multibet.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;


@Entity
@Table
@Data
public class Vladilen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Long id;

    @JsonView(Views.IdName.class)
    private String name;
    @JsonView(Views.IdName.class)
    private String lastname;
    @JsonView(Views.IdName.class)
    private String lastname1;
    @JsonView(Views.IdName.class)
    private String lastname2;
    @JsonView(Views.IdName.class)
    private String lastname3;
    @JsonView(Views.IdName.class)
    private String lastname4;

}
