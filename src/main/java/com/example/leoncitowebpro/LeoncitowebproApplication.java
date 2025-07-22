package com.example.leoncitowebpro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.leoncitowebpro.model.Role;
import com.example.leoncitowebpro.model.Usuario;
import com.example.leoncitowebpro.repository.RoleRepository;
import com.example.leoncitowebpro.repository.UsuarioRepository;

@SpringBootApplication
public class LeoncitowebproApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeoncitowebproApplication.class, args);

	

		
	}


	@Bean
public CommandLineRunner initData(RoleRepository roleRepository, UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
    return args -> {
        if (roleRepository.findByNombre("ADMIN").isEmpty()) {
            Role admin = new Role(); admin.setNombre("ADMIN"); roleRepository.save(admin);
            Role usuario = new Role(); usuario.setNombre("USUARIO"); roleRepository.save(usuario);

            // Admin inicial
            Usuario adminUser = new Usuario();
            adminUser.setNombre("admin");
            adminUser.setEmail("admin@tienda.com");
            adminUser.setPassword(encoder.encode("admin123"));
            adminUser.setRol(admin);
            usuarioRepository.save(adminUser);
        }
    };
}

}
