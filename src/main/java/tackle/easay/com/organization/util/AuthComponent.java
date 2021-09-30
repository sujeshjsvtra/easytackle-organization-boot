package tackle.easay.com.organization.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import tackle.easay.com.organization.pojo.Authenticate;
import tackle.easay.com.organization.pojo.Tokens;

@Component
public class AuthComponent {
	@Autowired
	private WebClient.Builder webClientbuilder;
	
	public Tokens createToken(String email,Long userId) {
		Tokens token = null;
		try {
			token = webClientbuilder.build().post().uri(SystemConstants.BASE_SECURE_API + "/createtoken/" + email+"/"+userId)
					.retrieve().bodyToMono(Tokens.class).block();
		} catch (Exception e) {
			throw e;
		}
		return token;
	}

	public Long authenticate(HttpServletRequest request) throws Exception {
		Long userId = 0l;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", request.getHeader("Authorization"));
		 
			userId = webClientbuilder.build().get().uri(SystemConstants.BASE_SECURE_API + "/authenticate")
					.header("Authorization", request.getHeader("Authorization"))
					.retrieve()
					.bodyToMono(Long.class).block();
	 
		return userId;
	}
	
	public Authenticate temporalAuthentication(String email)throws Exception {
		Authenticate authenticate = null;
		try {
			authenticate = webClientbuilder.build().get().uri(SystemConstants.BASE_AUTHENTICATE_API + "/temporary/"+email)
					.retrieve().bodyToMono(Authenticate.class).block();
		} catch (Exception e) {
			throw e;
		}
		return authenticate;
	}
	
	public Boolean deleteAuthenticationByUserId(Long userId) throws Exception{
		boolean flag = false;
		try {
		flag = 	webClientbuilder.build().delete().uri(SystemConstants.BASE_AUTHENTICATE_API+"/delete/"+userId)
					.retrieve().bodyToMono(Boolean.class).block();
		}catch(Exception e) {
			throw e;
		}
		return flag;
	}
}
