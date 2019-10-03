package com.deportes.deportes_api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deportes.deportes_api.repositorios.ReservasRepositorio;

import com.deportes.deportes_api.tablas.Reservas;
import com.deportes.deportes_api.tablas.Deporte;
import com.deportes.deportes_api.tools.DateTools;
import com.deportes.deportes_api.tools.JPAcustomSpecification;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080"})
@SuppressWarnings({"rawtypes","unchecked"})
@RestController
@RequestMapping("/reservas")
public class ReservasController <T> {
	
	@Autowired
	ReservasRepositorio repository;
	
	Logger logger = Logger.getLogger(ReservasController.class);
	JPAcustomSpecification jpacustomSpecification = new JPAcustomSpecification();
	DateTools dateTools = new DateTools();
	
	@PostMapping("")
	public Reservas save( @RequestBody Reservas newReservas) {
		logger.info("access to: post /save route");
		Reservas reservas = null;
		try {
			reservas= (Reservas) repository.save(newReservas);
			logger.info("Reservas: registro guardado");	
		}catch(Exception ex) {
			logger.error(ex);
		}
		return reservas;
		
	}
	
	@DeleteMapping("/{id}")
	public Boolean delete(@PathVariable Integer id) {
		logger.info("access to: Delete /{"+id+"} route");
		try {
			repository.deleteById(id);;
			return true;
		}catch(Exception ex) {
			logger.error(ex);
			return false;
		}
	}
	
	@PutMapping("/{id}")
	public Reservas updateEmployee(@RequestBody Reservas newReservas, @PathVariable Integer id) {
		try {
			return (Reservas) repository.findById(id)
				      .map(reservas -> {
				        ((Reservas) reservas).setNombre(newReservas.getNombre());
				        ((Reservas) reservas).setApellido(newReservas.getApellido());
				        ((Reservas) reservas).setDependencia(newReservas.getDependencia());
				        ((Reservas) reservas).setJubilado(newReservas.getJubilado());
				        ((Reservas) reservas).setDescuento(newReservas.getDescuento());
				        ((Reservas) reservas).setCarnet(newReservas.getCarnet());
				        
				        
				        return repository.save(reservas);
				      })
				      .orElseGet(() -> {
				    	  newReservas.setId(id);
				        return repository.save(newReservas);
				      });	
		}catch(Exception ex) {
			logger.error(ex);
			return null;
		}
	}
	
	@GetMapping("/{id}")
	public @ResponseBody  Optional<Reservas> finbyid(@PathVariable int id) {
		logger.info("access to: / reservas/"+id);
		Optional<Reservas> list = null;
		try {
			list= repository.findById(id);
		}catch(Exception ex){
			logger.error(ex);
		}
		return list;
	}
	
	@GetMapping("/findbynombre/{name}")
	public @ResponseBody  List<Reservas> finbyname(@PathVariable String name) {
		logger.info("access to: / reservas/finbynombre/"+name);
		List<Reservas> list = null;
		try {
			list= repository.findByNombre(name);
		}catch(Exception ex){
			logger.error(ex);
		}
		return list;
	}
	
	
	@GetMapping("")
	public @ResponseBody  Iterable<Reservas> findall(@RequestParam("searchCriteria") Optional<String> searchCriteria,@RequestParam Optional<String> orderCriteria) {
		logger.info("access to: / deporte/findall?searchCriteria="+searchCriteria+"&orderCriteria="+orderCriteria);
		Iterable<Reservas> list = null;
		JSONArray searchCriteriaArray=null, orderCriteriaArray=null;
		try {
			 if (!searchCriteria.isEmpty())	searchCriteriaArray = new JSONArray(searchCriteria.get());
			 if (!orderCriteria.isEmpty())	orderCriteriaArray = new JSONArray(orderCriteria.get());
			 list= repository.findAll(jpacustomSpecification.getSpecification(searchCriteriaArray,orderCriteriaArray ));
		}catch(Exception ex){
			logger.error(ex);
		}
		return list;
	}
	
	@GetMapping("/page/{pageNumber}/{pageSize}")
	public  @ResponseBody  Page  page(	@PathVariable int pageNumber,@PathVariable int pageSize,
			@RequestParam("searchCriteria") Optional<String> searchCriteria,@RequestParam Optional<String> orderCriteria) {
		logger.info("access to: / deporte/page/"+pageNumber+"/"+pageSize+"?searchCriteria="+searchCriteria+"&orderCriteria="+orderCriteria);
		JSONArray searchCriteriaArray=null, orderCriteriaArray=null; 
		try {
			 if (!searchCriteria.isEmpty())	searchCriteriaArray = new JSONArray(searchCriteria.get());
			 if (!orderCriteria.isEmpty())	orderCriteriaArray = new JSONArray(orderCriteria.get());
			 Page<?> page = repository.findAll(jpacustomSpecification.getSpecification(searchCriteriaArray,orderCriteriaArray ),PageRequest.of(pageNumber, pageSize));
			 page.getTotalElements();
		     page.getTotalPages();   
		     return page;
		}catch(Exception ex){
			logger.error(ex);
			return null;
		}
		
	}
	    

}
