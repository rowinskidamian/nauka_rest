package pl.damianrowinski.nauka_rest.domain.dto;

import lombok.Data;

@Data
public class FieldErrorDTO {
    private String fieldName;
    private String fieldMessage;
    private String rejectedValue;
}
