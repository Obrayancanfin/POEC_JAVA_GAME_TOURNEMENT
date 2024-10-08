package org.example.game_tournement.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity @Table(name = "user")

public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Le nom d utilisateur est obligatoire") 
    private String username;

    @NotBlank(message = "Le mot de passe est obligatoire") @Length(min = 8, max = 40, message = "Le mot de passe doit contenir entre 8 et 40 caractères") @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!.%*?&])[A-Za-z\\d@$!%*.?&]{8,40}$", message = "Le mot de passe doit contenir au moins une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial")
    private String password;

    @NotBlank(message = "la répétition de mot de passz est obligatoire")
    @Transient private String repeatedPassword; // @Transient --> non-persistence en BDD
    private String avatarPath; // En partant du principe que l'on stockera les avatars hors BDD pour l'instant (en local dans l'appli)

    @NotBlank(message = "L ' email est obligatoire")
    @Length(min = 8, max = 40, message = "l' email doit contenir au moins 6 caractéres")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email doit etre du  format *@*.*")

    private String email;


    @NotBlank(message = "la répétition d'email est obligatoire")
    @Transient
    private String repeatedEmail;

    @Builder.Default
    private String roles = "USER";

    
}

