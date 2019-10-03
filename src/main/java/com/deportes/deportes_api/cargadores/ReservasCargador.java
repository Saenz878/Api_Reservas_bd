package com.deportes.deportes_api.cargadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.deportes.deportes_api.repositorios.ReservasRepositorio;

@Component
public class ReservasCargador implements ApplicationListener<ContextRefreshedEvent>  {
		private ReservasRepositorio reservasRepositorio;
		//private Logger log = Logger.getLogger(DeporteCargador.class);
		
	    @Autowired
	    public void setAlumnoRepository(ReservasRepositorio reservasRepositorio) {
	        this.reservasRepositorio = reservasRepositorio;
	    }
	    
		public void onApplicationEvent(ContextRefreshedEvent event) {
			System.out.println("Reservas_Cargador");
		}
	    
	    /*public void onApplicationEvent(ContextRefreshedEvent event) {
	    	System.out.println("DeporteCargador");
	    	
	    	//Deporte deporte = new Deporte();
	        //deporte.setNombre("Registro 1");
	        //deporteRepositorio.save(deporte);
	        //log.info("Saved Shirt - id: " + deporte.getId());
	    }*/

}
