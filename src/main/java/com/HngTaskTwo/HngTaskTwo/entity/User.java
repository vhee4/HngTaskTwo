package com.HngTaskTwo.HngTaskTwo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@JsonPropertyOrder("""
        user_id,
        name,
        email
        """)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("name")
    @Column(nullable = false)
    private String name;

    @JsonProperty("email")
    @Column(nullable = false)
    private String email;
}
