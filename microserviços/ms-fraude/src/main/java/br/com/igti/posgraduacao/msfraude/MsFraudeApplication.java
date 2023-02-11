package br.com.igti.posgraduacao.msfraude;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

@SpringBootApplication
public class MsFraudeApplication {



	private static final Logger log = LoggerFactory.getLogger(MsFraudeApplication.class);
	private final Environment env;

	public MsFraudeApplication(Environment environment) {
		this.env = environment;
	}


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MsFraudeApplication.class);
		Environment env = app.run(args).getEnvironment();
		logAppOnStartup(env);
	}

	private static void logAppOnStartup(Environment env) {
		String protocol = Optional.ofNullable(env.getProperty("server.ssl.key-store")).map(key -> "https").orElse("http");
		String serverPort = env.getProperty("server.port");
		String contextPath = Optional
				.ofNullable(env.getProperty("server.servlet.context-path"))
				.filter(StringUtils::isNotBlank)
				.orElse("/");
		String hostAddress = "localhost";
		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.warn("The host name could not be determined, using `localhost` as fallback");
		}
		log.info(
				"\n----------------------------------------------------------\n\t" +
						"Developed By Carlos Roberto Medeiros - Pos IGTI - Arquitetura de Software 2023:\n\t" +
						"Application '{}' is running! Access URLs:\n\t" +
						"HealthCheck Access URLs:'{}' \n\t" +
						"Local: \t\t{}://localhost:{}{}\n\t" +
						"External: \t{}://{}:{}{}\n\t" +
						"Profile(s): \t{}\n----------------------------------------------------------",
				contextPath,
				"http://localhost:8031/service-fraude/actuator/health",
				protocol,
				serverPort,
				contextPath,
				protocol,
				hostAddress,
				serverPort,
				contextPath,
				env.getActiveProfiles().length == 0 ? env.getDefaultProfiles() : env.getActiveProfiles()
		);


	}

}
