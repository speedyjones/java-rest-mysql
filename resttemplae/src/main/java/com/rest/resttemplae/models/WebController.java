package com.rest.resttemplae.models;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.resttemplae.ForexService;

@RestController
@RequestMapping("/myapp")
public class WebController {

		@Autowired
		ForexService forexService;
	
	
		@GetMapping("")
		public List<ForexDao> list()
		{
			return forexService.getAllrates();
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<ForexDao> get(@PathVariable Integer id)
		{
			try {
				ForexDao forexdao = forexService.getRates(id);
				return new ResponseEntity<ForexDao>(forexdao, HttpStatus.OK);
			}
			catch(NoSuchElementException e)
			{
				return new ResponseEntity<ForexDao>(HttpStatus.NOT_FOUND);
			}
		}
		
		@PostMapping("/")
		public void add(@RequestBody ForexDao forexDao)
		{
			forexService.saveRates(forexDao);
		}
		
		@PutMapping("/{id}")
		public ResponseEntity<?> update(@RequestBody ForexDao forexDao, @PathVariable Integer id)
		{
			try {
				ForexDao existRates = forexService.getRates(id);
				forexDao.setId(id);
				forexService.saveRates(forexDao);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			catch(NoSuchElementException e)
			{
				return new ResponseEntity<ForexDao>(HttpStatus.NOT_FOUND);
			}
		}
		
		
		@DeleteMapping("/{id}")
		public void delete(@PathVariable Integer id)
		{
			forexService.deleteRates(id);
		}
		
		public ForexDao forexdao(@RequestParam(value = "name",defaultValue="Robot") String name )
		{
			ForexDao response = new ForexDao();
			response.setId(1);
			response.setRates("10.2244");
			response.setUpdated("166677 "+name);
			return response;
		}
	
}
