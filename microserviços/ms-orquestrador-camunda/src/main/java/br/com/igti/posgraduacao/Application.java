package br.com.igti.posgraduacao;

import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.info.BuildProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

@SpringBootApplication
@EnableProcessApplication
@EnableFeignClients
public class Application {

  private BuildProperties buildProperties;
  private static BuildProperties buildPropertiesStatic;

  private static final Logger log = LoggerFactory.getLogger(Application.class);
  private final Environment env;

  public static BuildProperties getBuildPropertiesStatic() {
    return buildPropertiesStatic;
  }

  @PostConstruct
  public void init(){
    Application.buildPropertiesStatic = this.buildProperties;
  }

  public Application(BuildProperties buildProperties, Environment env) {
    this.buildProperties = buildProperties;
    this.env = env;
  }

  public static void main(String... args) {
    SpringApplication app = new SpringApplication(Application.class);
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
                    "GroupId: '{}'\n\t" +
                    "ArtifactId: '{}'\n\t" +
                    "Version: '{}' \n\t" +
                    "Camunda Access URLs:'{}' \n\t" +
                    "Swagger Access URLs:'{}' \n\t" +
                    "Local: \t\t{}://localhost:{}{}\n\t" +
                    "External: \t{}://{}:{}{}\n\t" +
                    "Profile(s): \t{}\n----------------------------------------------------------",
            contextPath,
            buildPropertiesStatic.getGroup(),
            buildPropertiesStatic.getArtifact(),
            buildPropertiesStatic.getVersion(),
            "http://"+hostAddress+":"+serverPort+contextPath+"/camunda",
            "http://"+hostAddress+":"+serverPort+contextPath+"/swaggerui.html",
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