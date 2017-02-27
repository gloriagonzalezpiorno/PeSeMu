package dad.practica.pesemu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UsuarioRepositoryAuthenticationProvider authenticationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//páginas publicas
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/index.html").permitAll();
		http.authorizeRequests().antMatchers("/registroUsuario").permitAll();
		http.authorizeRequests().antMatchers("/registro_usuario.html").permitAll();
		http.authorizeRequests().antMatchers("/usuarioNuevo").permitAll();
		http.authorizeRequests().antMatchers("/usuario_registrado.html").permitAll();
		http.authorizeRequests().antMatchers("/fallo.html").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/inicioSesion").permitAll();
		http.authorizeRequests().antMatchers("/tipos_producto.html").permitAll();
		http.authorizeRequests().antMatchers("/catalogo").permitAll();
		http.authorizeRequests().antMatchers("/catalogo/{tipo}/{genero}").permitAll();
		http.authorizeRequests().antMatchers("/catalogo/{tipo}/{genero}/{id}").permitAll();
		
		//páginas privadas
		http.authorizeRequests().anyRequest().authenticated();
		
		//login form
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("correo");
		http.formLogin().passwordParameter("contrasena");
		http.formLogin().defaultSuccessUrl("/sesionIniciada");
		http.formLogin().failureUrl("/fallo.html");
		
		//Logout
		http.logout().logoutUrl("logout.html");
		http.logout().logoutSuccessUrl("/");
		
		//Disable CSRF at the moment
		//http.csrf().disable();
		
	}
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        auth.authenticationProvider(authenticationProvider);
    }
		
	
}