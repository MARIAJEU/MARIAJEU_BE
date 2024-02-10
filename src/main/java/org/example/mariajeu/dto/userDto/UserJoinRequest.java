package org.example.mariajeu.dto.userDto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.mariajeu.domain.userDomain.Role;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class UserJoinRequest {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{5,12}$", message = "아이디는 특수문자를 제외한 5~12자리여야 합니다.")
    private String userName;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @Email
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @NotBlank(message = "인증번호를 입력해주세요")
    @Size(min = 6, max = 6, message = "인증번호는 6글자 입니다")
    private String authNum;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    private String nickName;

    private Role role;

    private boolean agreedToTerms1;
    private boolean agreedToTerms2;
    private boolean agreedToOptionalTerms;
}
