package tackle.easay.com.organization.endpoint;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;
import tackle.easay.com.organization.entity.Organization;
import tackle.easay.com.organization.service.OrganizationService;
import tackle.easay.com.organization.util.AuthComponent;

@RestController
@RequestMapping(value = "/organization-client")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class OrganizationController {

	@Autowired
	private AuthComponent authComponent;
	@Autowired
	private OrganizationService organizationService;
	
	@PostMapping(value="/create",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Organization> createOrganization(HttpServletRequest request,@RequestBody Organization organization) {
		try {
			Long userId = authComponent.authenticate(request);
			organization=organizationService.createOrganization(organization,userId);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
		return new ResponseEntity<Organization>(organization,HttpStatus.OK);
	}
	
	@GetMapping(value = "/listall",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Organization>> allOrganization(HttpServletRequest request) {
		List<Organization> organizationList = new ArrayList<Organization>();
		try {
			Long userId = authComponent.authenticate(request);
			organizationList = organizationService.listAllOrganization(userId);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);
		}
		return new ResponseEntity<List<Organization>>(organizationList,HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> deleteOrganization(HttpServletRequest request,@PathVariable("id") Integer id) {
		boolean flag = true;
		try {
			Long userId = authComponent.authenticate(request);
			flag = organizationService.deleteOrganization(id);
		}catch(Exception e) {
			
		}
		return new ResponseEntity<Boolean>(flag,HttpStatus.OK);
	}
	
 
}
