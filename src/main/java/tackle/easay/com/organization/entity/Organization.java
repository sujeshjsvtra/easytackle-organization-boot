package tackle.easay.com.organization.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name="ams_organization")
public class Organization implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String organizationName;
	private OrganizationType organizationType;
	private Integer employees;
	private String email;
	@Column(name="contact_number")
	private String contactNumber;
	@Column(name="start_date")
	private Date startDate;
	@Column(name="end_date")
	private Date endDate;
	private Integer status;
	private Long ownerId;
	private Long createdBy;
	private String code;

}
