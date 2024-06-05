package com.example.KAV63X.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Tanulok")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Tanulo {@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long azon;
    private String nev;
    private String osztaly;
}
