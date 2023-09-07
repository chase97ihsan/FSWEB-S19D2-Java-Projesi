package com.workintech.S7D2;

import com.workintech.S7D2.dao.MemberRepository;
import com.workintech.S7D2.dao.RoleRepository;
import com.workintech.S7D2.entity.Member;
import com.workintech.S7D2.entity.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class S7D2Application {

	public static void main(String[] args) {
		SpringApplication.run(S7D2Application.class, args);
	}
	@Bean
	CommandLineRunner run(RoleRepository roleRepository, MemberRepository memberRepository,
						  PasswordEncoder passwordEncoder) {
		return args -> {

			if (roleRepository.findByAuthority("ADMIN").isPresent()) {
				return;
			}
			Role admin=new Role();
			admin.setAuthority("ADMIN");
			roleRepository.save(admin);
			Set<Role> roleSet = new HashSet<>();
			roleSet.add(admin);
			Member admin1 = new Member();
			admin1.setEmail("ihsan97@admin.com");
			admin1.setPassword(passwordEncoder.encode("hello"));
			admin1.setAuthorities(roleSet);
			memberRepository.save(admin1);
		};
	}

}
