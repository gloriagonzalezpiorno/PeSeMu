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
		//páginas publicas--------------------------------------------------------------------------------
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/principal.html").permitAll();
		http.authorizeRequests().antMatchers("/registroUsuario").permitAll();
		http.authorizeRequests().antMatchers("/registro_usuario.html").permitAll();
		http.authorizeRequests().antMatchers("/usuarioNuevo").permitAll();
		http.authorizeRequests().antMatchers("/usuario_registrado.html").permitAll();
		http.authorizeRequests().antMatchers("/fallo.html").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/inicioSesion").permitAll();
		http.authorizeRequests().antMatchers("/tipos_producto.html").permitAll();
		http.authorizeRequests().antMatchers("/catalogo").permitAll();
		
		//páginas privadas--------------------------------------------------------------------------------
		//cache
		http.authorizeRequests().antMatchers("/cache").hasAnyRole("ADMIN");
		//carrito
		http.authorizeRequests().antMatchers("/carrito/**").hasAnyRole("USUARIO","ADMIN");
		http.authorizeRequests().antMatchers("/comprar").hasAnyRole("USUARIO","ADMIN");
		http.authorizeRequests().antMatchers("/catalogo/**/aniadirACarrito").hasAnyRole("USUARIO","ADMIN");
		http.authorizeRequests().antMatchers("/compra_finalizada.html").hasAnyRole("USUARIO","ADMIN");
		http.authorizeRequests().antMatchers("/producto_aniadido_carrito.html").hasAnyRole("USUARIO","ADMIN");
		//opiniones
		http.authorizeRequests().antMatchers("/catalogo/**/opinion").hasAnyRole("USUARIO","ADMIN");	
		http.authorizeRequests().antMatchers("/saldo_aniadido.html").hasAnyRole("USUARIO","ADMIN");
		//usuario
		http.authorizeRequests().antMatchers("/aniadirSaldo").hasAnyRole("USUARIO","ADMIN");
		http.authorizeRequests().antMatchers("/aniadir_saldo.html").hasAnyRole("USUARIO","ADMIN");
		http.authorizeRequests().antMatchers("/saldo_aniadido.html").hasAnyRole("USUARIO","ADMIN");
		http.authorizeRequests().antMatchers("/sesion_iniciada.html").hasAnyRole("USUARIO","ADMIN");
		http.authorizeRequests().antMatchers("/usuario_registrado.html").hasAnyRole("USUARIO","ADMIN");
		//producto
		http.authorizeRequests().antMatchers("/nuevo_producto.html").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/catalogo/**/aniadirProducto").hasAnyRole("ADMIN");
		//imagenes privadas
		http.authorizeRequests().antMatchers("/ver_carrito.jpg").hasAnyRole("USUARIO","ADMIN");

		
		//login form
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("correo");
		http.formLogin().passwordParameter("contrasena");
		http.formLogin().defaultSuccessUrl("/sesionIniciada");
		http.formLogin().failureUrl("/loginerror");
		
		//Logout
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");
		
	}
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        auth.authenticationProvider(authenticationProvider);
    }
		
	
}