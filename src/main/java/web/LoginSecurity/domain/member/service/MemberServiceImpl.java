package web.LoginSecurity.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.LoginSecurity.domain.member.domain.Member;
import web.LoginSecurity.domain.member.repository.MemberRepository;
import web.LoginSecurity.global.exception.IsExistedEmailException;
import web.LoginSecurity.global.exception.NotFoundMemberException;

import java.util.NoSuchElementException;

import static web.LoginSecurity.global.exception.ExceptionMessage.EMAIL_EXISTED;
import static web.LoginSecurity.global.exception.ExceptionMessage.MEMBER_NOT_EXISTED;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    @Override
    public void saveMember(Member member) {
        memberRepository.save(member);
    }

    @Override
    public void checkEmailAllowed(String memberEmail) {
        if (isExistedEmail(memberEmail).equals(Boolean.TRUE)){
            throw new IsExistedEmailException(EMAIL_EXISTED);
        }
    }

    @Override
    public Boolean isExistedEmail(String memberEmail) {
        return memberRepository.findByEmail(memberEmail).isPresent();
    }

    @Override
    public Member findMemberByEmail(String memberEmail){
        return memberRepository.findByEmail(memberEmail).orElseThrow(() -> new NotFoundMemberException(MEMBER_NOT_EXISTED));
    }

    @Override
    public Boolean isLoginAllowed(Member member, String password){
        return member.getPassword().equals(password) ? Boolean.TRUE : Boolean.FALSE;
    }
}
