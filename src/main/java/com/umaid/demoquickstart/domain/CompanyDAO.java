package com.umaid.demoquickstart.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class CompanyDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String cname;
	
	private Integer eid;
	private String ename;

	public CompanyDAO(CompanyDTO dto) {
		//Converting from DTO to Entity
		this.id = dto.getId();
		this.cname = dto.getCname();
		this.eid = dto.getEid();
		this.ename = dto.getEname();
	}

}
