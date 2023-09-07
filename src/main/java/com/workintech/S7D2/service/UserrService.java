package com.workintech.S7D2.service;

import com.workintech.S7D2.dao.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserrService implements UserDetailsService {

    private MemberRepository memberRepository;
    @Autowired
    public UserrService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findMemberByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User is not valid"));
    }
}
