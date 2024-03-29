package org.example.mariajeu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponseDTO {

    private HttpStatus successStatus;
    private String successContent;
    private Object Data;

}
