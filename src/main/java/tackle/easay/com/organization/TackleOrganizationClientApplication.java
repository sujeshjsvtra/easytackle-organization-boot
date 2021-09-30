package tackle.easay.com.organization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties.Web;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class TackleOrganizationClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(TackleOrganizationClientApplication.class, args);
	}

	public WebClient.Builder wentClientBuilder(){
		return WebClient.builder();
	}
}
