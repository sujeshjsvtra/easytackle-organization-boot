package tackle.easay.com.organization.service;

import java.util.List;

import tackle.easay.com.organization.entity.Organization;

public interface OrganizationService {

	public Organization createOrganization(Organization organization,Long userId)throws Exception;
	
	public List<Organization> listAllOrganization(Long userId)throws Exception;
	
	public Organization organizationByUserId(Long userId)throws Exception;
	
	public Organization organizationById(Long id)throws Exception;
	
	public boolean deleteOrganization(Integer organizationId) throws Exception;
	
	public boolean changeStatus(Integer organizationId,Integer status)throws Exception;
	
	public Organization updateOrganization(Organization organization)throws Exception;
	
}
