package e.commerce.Eapp;


import e.commerce.Eapp.entity.Client;

import e.commerce.Eapp.repository.ClientRepository;
import e.commerce.Eapp.security.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EAppApplication.class, args);
	}

  //	@Bean
//    CommandLineRunner commandLineRunner(){
//		return args -> {
//			Stream.of("FOUDA","ABOMO","NGAN").forEach(name->{
//				Client client=new Client();
//				client.setId(UUID.randomUUID().toString());
//				client.setEmail(name+"@gmail.com");
//				client.setUserName(name);
//				userRepository.save(client);
//			});
//		};
//	}
	//@Bean
	CommandLineRunner commandLineRunner(ClientRepository clientRepository){
		return args -> {
			clientRepository.save(new Client(UUID.randomUUID().toString(),"FOUDA","FOUDA@gmail.com"));
			clientRepository.save(new Client(UUID.randomUUID().toString(),"ABOMO","ABOMO@gmail.com"));
			clientRepository.save(new Client(UUID.randomUUID().toString(),"NGAN","NGAN@gmail.com"));
		};
	}
	//@Bean
	CommandLineRunner start(JdbcUserDetailsManager jdbcUserDetailsManager){

		return args -> {

			UserDetails u1=jdbcUserDetailsManager.loadUserByUsername("user1");
			UserDetails u2=jdbcUserDetailsManager.loadUserByUsername("user2");
			UserDetails u3=jdbcUserDetailsManager.loadUserByUsername("user3");
			if (u1==null)
			jdbcUserDetailsManager.createUser(
					User.withUsername("user1").password(passwordEncoder().encode("1234")).roles("USER").build()
			);
             if (u2==null)
			jdbcUserDetailsManager.createUser(
					User.withUsername("user2").password(passwordEncoder().encode("1234")).roles("ADMIN").build()
			);
             if ((u3==null))
			jdbcUserDetailsManager.createUser(
					User.withUsername("user3").password(passwordEncoder().encode("1234")).roles("ADMIN","USER").build()
			);
		};


	}
	//@Bean

	CommandLineRunner commandLineRunnerUserDetailsService(AccountService accountService){
		return args -> {
			accountService.addNewRole("USER");
			accountService.addNewRole("ADMIN");
			accountService.addNewUser("user1","1234","user1@gmail.com","1234");
			accountService.addNewUser("user2","1234","user2@gmail.com","1234");
			accountService.addNewUser("admin","1234","admin@gmail.com","1234");
			accountService.addRoleToUser("user1","USER");
			accountService.addRoleToUser("user2","USER");
			accountService.addRoleToUser("admin","ADMIN");
			accountService.addRoleToUser("admin","USER");
		};
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}
}
