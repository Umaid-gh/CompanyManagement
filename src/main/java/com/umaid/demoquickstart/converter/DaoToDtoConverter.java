package com.umaid.demoquickstart.converter;

import org.springframework.stereotype.Component;

import com.umaid.demoquickstart.domain.CompanyDTO;

@Component
public class DaoToDtoConverter {

	public CompanyDTO convert(int id, String cname, int eid, String ename) {
		return CompanyDTO.builder().id(id).cname(cname).eid(eid).ename(ename).build();
	}
}
