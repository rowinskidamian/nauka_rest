package pl.juniorjavaproject.testrestapi.domain.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;
}
