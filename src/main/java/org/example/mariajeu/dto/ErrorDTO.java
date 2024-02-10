package org.example.mariajeu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ErrorDTO {
    private String errorCode;
    private String errorContent;
}
