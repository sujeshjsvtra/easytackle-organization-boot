package tackle.easay.com.organization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tackle.easay.com.organization.entity.Organization;
import tackle.easay.com.organization.projection.OrganizationProjection;

public interface OrganizationRepository extends CrudRepository<Organization, Integer> {

	@Query(value = "select U.id userId from ams_user U where U.email=:email " ,nativeQuery = true)
	OrganizationProjection findUserIdByEmail(@Param("email") String email);
	
	List<Organization> findAllByOwnerIdOrCreatedBy(Long ownerId,Long createdBy);
	
}
