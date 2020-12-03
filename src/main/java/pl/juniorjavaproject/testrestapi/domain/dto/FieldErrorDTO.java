package pl.juniorjavaproject.testrestapi.domain.dto;

import lombok.Data;

@Data
public class FieldErrorDTO {
    private String fieldName;
    private String fieldMessage;
    private String rejectedValue;
}
