package br.ufrn.hospital.controller;

import br.ufrn.hospital.exceptions.ComunicationException;
import br.ufrn.hospital.exceptions.TopicDoesNotExistException;
import br.ufrn.hospital.subscriber.AbstractSubscriber;
import br.ufrn.hospital.subscriber.ConcreteSubscriber;

public class WaitNewTopics {
	
	private final HospitalController hospitalController;

	private AbstractSubscriber subscriber = new ConcreteSubscriber("Novo-Paciente",this);
	
	public WaitNewTopics(HospitalController hospitalController) throws ComunicationException, TopicDoesNotExistException {
		this.hospitalController = hospitalController;
		subscriber.subscribe();
	}
	
	public void novoTopicoCadastrado(String topico ){
		hospitalController.subscreverTopico(topico);
	}


}
