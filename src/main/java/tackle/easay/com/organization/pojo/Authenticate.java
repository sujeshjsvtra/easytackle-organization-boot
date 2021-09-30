package tackle.easay.com.organization.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class Authenticate implements Serializable{

	private Long id;
	private String username;
	private String password;
	private String secretCode;
	private Long userId;
	

}
