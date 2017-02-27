package dad.practica.pesemu.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import dad.practica.pesemu.model.Usuario;
import dad.practica.pesemu.repositories.UsuarioRepository;

@Component
public class UsuarioRepositoryAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		Usuario usuario = usuarioRepository.findByCorreo(auth.getName());
		
		if(usuario == null) {
			throw new BadCredentialsException("Usuario no encontrado");
		}
		
		String contrasenia = (String) auth.getCredentials();
		if(!new BCryptPasswordEncoder().matches(contrasenia, usuario.getContraseniaHash())) {
			throw new BadCredentialsException("Contrasenia incorrecta");
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();
		for(String rol : usuario.getRoles()){
			roles.add(new SimpleGrantedAuthority(rol));
		}
		
		return new UsernamePasswordAuthenticationToken(usuario.getCorreo(), contrasenia, roles);
	}

	@Override
	public boolean supports(Class<?> authenticationObject) {
		return true;
	}

}
