package com.pvv.pulbet.service;

import java.util.ArrayList;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.model.Evento;
import com.pvv.pulbet.service.impl.EventoServiceImpl;

public class EventoServiceTest {
	
	private EventoService eventoService = null;

	public EventoServiceTest() {
		eventoService =  new EventoServiceImpl(); 
	}
	
	public void testFindCriteria() throws DataException {
		EventoCriteria e  = new EventoCriteria();
		//e.setParticipante("ma");
		e.setIdDeporte(2l);
		//e.setFecha(new Date());
		//e.setIdCompeticion(1l);
		//e.setIdEvento(6l);
		
		List<Evento> eventos = new ArrayList<Evento>();
		
		eventos = eventoService.findByCriteria(e);
		
		for (Evento ev : eventos) {
			System.out.println(ev);
		}
	}
	
	public static void main (String args[]) {
		
		EventoServiceTest test = new EventoServiceTest();
		
		try {

			test.testFindCriteria();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
