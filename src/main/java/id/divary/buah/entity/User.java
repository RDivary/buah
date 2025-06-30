package id.divary.buah.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "user_")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
    private String username;
    private String password;

    private String role;

}
