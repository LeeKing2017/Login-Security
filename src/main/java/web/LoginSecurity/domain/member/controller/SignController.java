package web.LoginSecurity.domain.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import web.LoginSecurity.domain.member.domain.Member;
import web.LoginSecurity.domain.member.dto.MemberDto;
import web.LoginSecurity.domain.member.service.MemberService;
import web.LoginSecurity.domain.recaptcha.service.ReCaptchaService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/web")
public class SignController {
    private final MemberService memberService;
    private final ReCaptchaService reCaptchaService;

    @PostMapping("/member")
    @ResponseBody
    public String signUp(@RequestBody MemberDto.CreateMember createMemberDto){
        String memberEmail = createMemberDto.getEmail();
        String memberPassword = createMemberDto.getPassword();
        String memberNickname = createMemberDto.getNickname();

        memberService.checkEmailAllowed(memberEmail);

        Member member = new Member().toEntity(memberEmail, memberPassword,memberNickname);
        memberService.saveMember(member);

        return "/web/login";
    }

    @PostMapping("/login")
    public MemberDto.LoginResponse login(@RequestBody MemberDto.LoginRequest loginRequestDto){
        String requestEmail = loginRequestDto.getEmail();
        String requestPassword = loginRequestDto.getPassword();
        String reCaptchaResponse = loginRequestDto.getReCaptchaResponse();

        reCaptchaService.verifyReCaptcha(reCaptchaResponse);

        Member member = memberService.findMemberByEmail(requestEmail);

        return memberService.isLoginAllowed(member, requestPassword) ?
                new MemberDto.LoginResponse(true) :
                new MemberDto.LoginResponse(false, "로그인 실패");
    }
}
