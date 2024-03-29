package org.example.mariajeu.controller.userController;

import org.example.mariajeu.dto.ResponseDTO;
import org.example.mariajeu.dto.userDto.EmailCheckDTO;
import org.example.mariajeu.dto.userDto.EmailRequest;
import org.example.mariajeu.exception.AppException;
import org.example.mariajeu.exception.ErrorCode;
import org.example.mariajeu.service.MailService.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;

    @PostMapping("/Send")
    public ResponseEntity<ResponseDTO> mailSend(@RequestBody @Valid EmailRequest dto) {

        mailService.joinEmail(dto.getEmail());

        return ResponseEntity.ok(ResponseDTO.builder()
                .successStatus(HttpStatus.OK)
                .successContent(dto.getEmail()+"\n해당 메일에 인증번호가 정상 전송되었습니다.")
                .Data(dto)
                .build()
        );
    }

    @PostMapping("/AuthCheck")
    public ResponseEntity<ResponseDTO> AuthCheck(@RequestBody @Valid EmailCheckDTO dto){
        Boolean Checked=mailService.CheckAuthNum(dto.getEmail(),dto.getAuthNum());
        if(Checked){
            return ResponseEntity.ok(ResponseDTO.builder()
                    .successStatus(HttpStatus.OK)
                    .successContent("인증이 완료되었습니다")
                    .Data(dto)
                    .build()
            );
        }
        else{
            throw new AppException(ErrorCode.BAD_REQUEST,"인증 번호가 맞지 않습니다.",dto);
        }
    }

}