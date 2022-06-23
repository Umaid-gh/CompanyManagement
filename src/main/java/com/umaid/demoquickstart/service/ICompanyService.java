package com.umaid.demoquickstart.service;

import java.util.List;

import com.umaid.demoquickstart.domain.CompanyDTO;
import com.umaid.demoquickstart.domain.response.CompanyResponse;

public interface ICompanyService {

	public CompanyResponse<List<CompanyDTO>> getAllCompany();

	public CompanyResponse<CompanyDTO> findCompanyByEid(int eid);

	public CompanyResponse<CompanyDTO> addCompany(CompanyDTO c);

	public CompanyResponse<CompanyDTO> updateByEid(CompanyDTO c,int eid);
	
	public CompanyResponse<CompanyDTO> deleteCompanyByEid(int eid);

}
