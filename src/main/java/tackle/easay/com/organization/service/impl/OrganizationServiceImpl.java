package tackle.easay.com.organization.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tackle.easay.com.organization.entity.Organization;
import tackle.easay.com.organization.exception.DataNotFoundException;
import tackle.easay.com.organization.exception.EmailAlreadyExist;
import tackle.easay.com.organization.pojo.Authenticate;
import tackle.easay.com.organization.repository.OrganizationRepository;
import tackle.easay.com.organization.service.OrganizationService;
import tackle.easay.com.organization.util.AuthComponent;
import tackle.easay.com.organization.util.SystemConstants;

@Service
@Slf4j
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private AuthComponent authComponent;

	@Autowired
	private OrganizationRepository organizationRepository;

	@Override
	@Transactional
	public Organization createOrganization(Organization organization,Long id) throws Exception {
		if (organization != null) {
			Long userId = organizationRepository.findUserIdByEmail(organization.getEmail())==null?null:organizationRepository.findUserIdByEmail(organization.getEmail()).getUserId();
			if (userId != null )
				throw new EmailAlreadyExist("Email Already Exist.!,Please use another one");
			Authenticate authenticate = authComponent.temporalAuthentication(organization.getEmail());
			if (authenticate != null) {
				String code = null;
				organization.setOwnerId(authenticate.getUserId());
				organization.setCreatedBy(id);
				organization = organizationRepository.save(organization);
				if(organization.getStatus().intValue()==SystemConstants.DRAFT.intValue() && organization.getCode()==null) {
					organization.setCode("DRFT-"+RandomStringUtils.randomAlphanumeric(4).toUpperCase()+organization.getId());
				}
				if(organization.getStatus().intValue()!=SystemConstants.DRAFT.intValue() && organization.getCode()==null) {
					organization.setCode(RandomStringUtils.randomAlphanumeric(4).toUpperCase()+organization.getId());
				}
				organization = organizationRepository.save(organization);
			
			}

		}else {
			throw new DataNotFoundException("Input Data is Empty..! ");
		}
		return organization;
	}

	@Override
	public List<Organization> listAllOrganization(Long userId) throws Exception {
		List<Organization> organizationList = null;
		if(userId!=null) {
			organizationList = organizationRepository.findAllByOwnerIdOrCreatedBy(userId,userId);
		}
		return organizationList;
	}

	@Override
	public Organization organizationByUserId(Long userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Organization organizationById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteOrganization(Integer organizationId) throws Exception {
		boolean flag = true;
		
		return false;
	}

	@Override
	public boolean changeStatus(Integer organizationId, Integer status) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Organization updateOrganization(Organization organization) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
