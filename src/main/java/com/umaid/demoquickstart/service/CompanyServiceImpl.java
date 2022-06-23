package com.umaid.demoquickstart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.umaid.demoquickstart.domain.CompanyDAO;
import com.umaid.demoquickstart.domain.CompanyDTO;
import com.umaid.demoquickstart.domain.response.CompanyResponse;
import com.umaid.demoquickstart.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private CompanyRepository comRepo;

	// @Autowired
	// private DaoToDtoConverter dtoConverter;

	@Override
	public CompanyResponse<List<CompanyDTO>> getAllCompany() {
		List<CompanyDTO> companyDTO = new ArrayList<>();
		CompanyResponse<List<CompanyDTO>> response = new CompanyResponse<>();

		List<CompanyDAO> dataDb = (List<CompanyDAO>) comRepo.findAll();

		if (dataDb.isEmpty()) {
			// response.setData(null);
			// response.setStatus(404);
			// response.setMessage("Empty Database!!");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Database is empty");
		} else {
			for (int i = 0; i < dataDb.size(); i++) {
				CompanyDAO company = dataDb.get(i);
				CompanyDTO dto = new CompanyDTO(company);
				// CompanyDTO dto = dtoConverter.convert(company.getId(),
				// company.getCname(), company.getEid(), company.getEname());
				companyDTO.add(dto);
			}
			response.setData(companyDTO);
			response.setStatus(200);
			response.setMessage("DataBase retrived!!");
		}
		return response;
	}

	public CompanyResponse<CompanyDTO> addCompany(CompanyDTO request) {
		if(request.getEname()==""||request.getEname()==null)
		{
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Employee Name is empty!!!");
		}
		
		//converting DTO and DAO 
		CompanyDAO entity = new CompanyDAO(request);
		//searching DB to check in Eid already present
		List<CompanyDAO> dataDb = (List<CompanyDAO>) comRepo.findAll();
		for(int i=0;i<dataDb.size();i++)
		{
			CompanyDAO company = dataDb.get(i);
			if(company.getEid()==entity.getEid())
			{
				throw new ResponseStatusException(HttpStatus.FOUND, "Employee ID Already present!!!");
			}
		}
		//saving new DAO 
		comRepo.save(entity);
		CompanyResponse<CompanyDTO> response = new CompanyResponse<CompanyDTO>(request, "New Employee added!!", 200);

		return response;
	}

	public CompanyResponse<CompanyDTO> findCompanyByEid(int eid) {
		// searching from DB and storing in Optional
		Optional<CompanyDAO> d = comRepo.findByEid(eid);
		// creating new response
		CompanyResponse<CompanyDTO> response = new CompanyResponse<>();
		if (d.isPresent()) {
			// converting DAO to DTO
			CompanyDTO c = new CompanyDTO(d.get());
			response.setData(c);
			response.setMessage("Employee ID Found!!!");
			response.setStatus(200);
		} else {
//			response.setData(null);
//			response.setMessage("Employee ID Not Found !!!");
//			response.setStatus(404);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee ID Not Found!!!");
		}
		return response;
	}

	public CompanyResponse<CompanyDTO> updateByEid(CompanyDTO c, int eid) {
		// searching from DB and storing in Optional
		Optional<CompanyDAO> db = comRepo.findByEid(eid);
		// creating new response
		CompanyResponse<CompanyDTO> response = new CompanyResponse<>();
		if (db.isPresent()) {
			// Retrieving from Optional
			CompanyDAO d = db.get();
			if (c.getEname() != null)
				d.setEname(c.getEname());
			if (c.getCname() != null)
				d.setCname(c.getCname());
			comRepo.save(d);
			response.setData(c);
			response.setMessage("Updated the Employee ID: " + eid);
			response.setStatus(200);
		} else {
//			response.setData(null);
//			response.setMessage("Employee ID: " + eid + " Not Found to Update!!!");
//			response.setStatus(404);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee ID Not Found!!!!");
		}
		return response;
	}

	@Override
	public CompanyResponse<CompanyDTO> deleteCompanyByEid(int eid) {
		Optional<CompanyDAO> companyDets = comRepo.findByEid(eid);

		CompanyResponse<CompanyDTO> response = new CompanyResponse<>();

		if (companyDets.isPresent()) {
			CompanyDTO c = new CompanyDTO(companyDets.get());
			response.setData(c);
			response.setMessage("Employee Details deleted for Employee Eid: " + eid);
			response.setStatus(200);
			comRepo.deleteByEid(eid);
		} else {
//			response.setData(null);
//			response.setMessage("Employee ID: " + eid + " Not Found to Deleted!!!");
//			response.setStatus(404);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee ID Not Found!!!");
		}
		return response;
	}

}
