package net.codejava.springmvc.repo;

import java.util.List;

import net.codejava.springmvc.domain.Member;

public interface MemberDao
{
    public Member findById(Long id);

    public Member findByEmail(String email);

    public List<Member> findAllOrderedByName();

    public void register(Member member);
}
