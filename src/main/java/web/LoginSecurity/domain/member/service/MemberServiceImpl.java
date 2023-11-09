package web.LoginSecurity.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.LoginSecurity.domain.member.domain.Member;
import web.LoginSecurity.domain.member.repository.MemberRepository;
import web.LoginSecurity.global.exception.isExistedEmailException;

import static web.LoginSecurity.global.exception.ExceptionMessage.EMAIL_EXISTED;

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
            throw new isExistedEmailException(EMAIL_EXISTED);
        }
    }

    @Override
    public Boolean isExistedEmail(String memberEmail) {
        return memberRepository.findByEmail(memberEmail).isPresent();
    }
}
