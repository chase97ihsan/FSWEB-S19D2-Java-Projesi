package com.workintech.S7D2.service;

import com.workintech.S7D2.dao.MemberRepository;
import com.workintech.S7D2.dao.RoleRepository;
import com.workintech.S7D2.entity.Member;
import com.workintech.S7D2.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationService {

    private MemberRepository memberRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(MemberRepository memberRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    //Passwordü encoded etmemizin sebebi veri tabanında şifre açık bir şekilde deişre olmasın...
    public Member register(String email,String password){
        Optional<Member> optional=memberRepository.findMemberByEmail(email);
        if(optional.isPresent()){
            return null;
            //Throw  exception
        }
        String encodedPassword=passwordEncoder.encode(password);
        Role role=roleRepository.findByAuthority("USER").get();
        Set<Role> roles=new HashSet<>();
        roles.add(role);
        Member member=new Member();
        member.setEmail(email);
        member.setPassword(encodedPassword);
        member.setAuthorities(roles);
        return memberRepository.save(member);
    }
    public void delete(){
        memberRepository.deleteAll();
        roleRepository.deleteAll();
    }
}
