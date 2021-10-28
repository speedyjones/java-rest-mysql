package com.rest.resttemplae;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.resttemplae.models.ForexDao;


@Service
@Transactional
public class ForexService {

	@Autowired
	private ForexRepro forexrepro;
	
	public List<ForexDao> getAllrates()
	{
			return forexrepro.findAll();
	}
	
	public void saveRates(ForexDao forex )
	{
		forexrepro.save(forex);
	}
	
	public ForexDao getRates(Integer Id)
	{
		return forexrepro.findById(Id).get();
	}
	
	public void deleteRates(Integer Id)
	{
		forexrepro.deleteById(Id);
	}
	
}
