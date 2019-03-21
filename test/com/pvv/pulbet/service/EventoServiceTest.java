package com.pvv.pulbet.service;

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
		//e.setIdDeporte(2l);
		//e.setFecha(new Date());
		//e.setIdCompeticion(1l);
		e.setIdEvento(6l);
		
		Results<Evento> results = null;
		int startIndex = 1;
		int count = 5;
		int i = 1;
				
		do {
			results = eventoService.findByCriteria(e, startIndex, count, "ENG");
			System.out.println("Found "+results.getTotal()+" results.");				
			if (results.getPage().size()>0) {
				System.out.println("Page ["+startIndex+" - "+(startIndex+results.getPage().size()-1)+"] : ");				
				for (Evento ev: results.getPage()) {
					System.out.println("Result "+i+": "+ev.toString());
					i++;
				}
				startIndex = startIndex + count;
			}

		} while (!(results.getPage().size()<count));
		
		
		
		
		
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
