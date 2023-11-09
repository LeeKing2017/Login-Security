package web.LoginSecurity.domain.member.service;

import web.LoginSecurity.domain.member.domain.Member;

public interface MemberService {
    void saveMember(Member member);

    Boolean isExistedEmail(String memberEmail);

    void checkEmailAllowed(String memberEmail);

    Member findMemberByEmail(String memberEmail);

    Boolean isLoginAllowed(Member member, String passowrd);
}
