package dad.practica.pesemu;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;

@EnableCaching
@SpringBootApplication
@EnableHazelcastHttpSession
public class PesemuApplication {
	
	@Autowired
	private Environment env;
	
	private static final Log LOG = LogFactory.getLog(PesemuApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PesemuApplication.class, args);
	}

	//Configurar Hazelcast
	@Bean
	public Config config() {
		List<String> serversAddresses = Arrays.asList(env.getProperty("pesemu.apps.ip.addresses").split(","));
		Config config = new Config();
		JoinConfig joinConfig = config.getNetworkConfig().getJoin();
		joinConfig.getMulticastConfig().setEnabled(false);
		joinConfig.getTcpIpConfig().setEnabled(true).setMembers(serversAddresses);
		return config;
	}
	
	@Bean
	public CacheManager cacheManager(){
		LOG.info("Activating cache...");
		return new ConcurrentMapCacheManager("facturasCache");
	}

}
