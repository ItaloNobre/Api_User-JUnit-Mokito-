package br.com.api.testes.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {


    private Integer id;
    private String name;
    //private String email;
    private String password;

}