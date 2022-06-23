package com.umaid.demoquickstart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umaid.demoquickstart.domain.CompanyDTO;
import com.umaid.demoquickstart.domain.response.CompanyResponse;
import com.umaid.demoquickstart.service.CompanyServiceImpl;

@RestController
@RequestMapping("/data")
public class CompanyController {

	@Autowired
	private CompanyServiceImpl companyServiceImpl;

	@GetMapping
	public CompanyResponse<List<CompanyDTO>> getAllCompany() {
		return companyServiceImpl.getAllCompany();
	}

	@GetMapping("/{eid}")
	public CompanyResponse<CompanyDTO> getCompanyByEid(@PathVariable int eid) {
		return companyServiceImpl.findCompanyByEid(eid);
	}

	@PostMapping
	public CompanyResponse<CompanyDTO> addData(@RequestBody CompanyDTO c) {
		return companyServiceImpl.addCompany(c);
	}

	@PutMapping("/{eid}")
	public CompanyResponse<CompanyDTO> updateByEid(@RequestBody CompanyDTO c, @PathVariable int eid) {
		return companyServiceImpl.updateByEid(c, eid);
	}

	@DeleteMapping("/{eid}")
	public CompanyResponse<CompanyDTO> deleteCompanyByEid(@PathVariable int eid) {
		return companyServiceImpl.deleteCompanyByEid(eid);
	}
}
