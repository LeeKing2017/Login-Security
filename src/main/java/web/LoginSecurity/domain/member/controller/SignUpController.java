package web.LoginSecurity.domain.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.LoginSecurity.domain.member.domain.Member;
import web.LoginSecurity.domain.member.dto.MemberDto;
import web.LoginSecurity.domain.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/web")
public class SignUpController {
    private final MemberService memberService;

    @PostMapping("/member")
    @ResponseBody
    public String signUp(@RequestBody MemberDto.CreateMember createMemberDto){
        log.info("create {}", createMemberDto.toString());
        String memberEmail = createMemberDto.getEmail();
        String memberPassword = createMemberDto.getPassword();
        String memberNickname = createMemberDto.getNickname();

        memberService.checkEmailAllowed(memberEmail);

        Member member = new Member().toEntity(memberEmail, memberPassword,memberNickname);
        memberService.saveMember(member);

        return "/web/login";
    }

    @PostMapping("/login")
    public String login(@RequestBody MemberDto.LoginMember loginMemberDto){
        String requestEmail = loginMemberDto.getEmail();
        String requestPassword = loginMemberDto.getPassword();

        Member member = memberService.findMemberByEmail(requestEmail);

        return memberService.isLogin(member, requestPassword) ? "로그인 성공" : "로그인 실패";
    }
}
