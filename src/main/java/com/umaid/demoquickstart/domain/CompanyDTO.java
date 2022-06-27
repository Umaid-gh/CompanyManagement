package com.umaid.demoquickstart.domain;

import org.springframework.stereotype.Component;

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
@Component
public class CompanyDTO {

	private int id;
	private String cname;
	private Integer eid;
	private String ename;

	public CompanyDTO(CompanyDAO entity) {
		//Converting from Entity to DTO
		this.id = entity.getId();
		this.cname = entity.getCname();
		this.eid = entity.getEid();
		this.ename = entity.getEname();
	}
	
	

}
