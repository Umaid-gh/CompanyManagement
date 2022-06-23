package com.umaid.demoquickstart.converter;

import org.springframework.stereotype.Component;

import com.umaid.demoquickstart.domain.CompanyDAO;

@Component
public class DtoToDaoConverter {

	public CompanyDAO convert(int id, String cname, int eid, String ename) {
		return CompanyDAO.builder().id(id).cname(cname).eid(eid).ename(ename).build();
	}
}
