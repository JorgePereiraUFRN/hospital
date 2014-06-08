package br.ufrn.hospital.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;

import br.ufrn.hospital.DAO.DAOFactory;
import br.ufrn.hospital.DAO.HibernateDAOFactory;
import br.ufrn.hospital.DAO.PacienteDAOInterface;
import br.ufrn.hospital.exceptions.ComunicationException;
import br.ufrn.hospital.exceptions.ControllerException;
import br.ufrn.hospital.exceptions.DAOException;
import br.ufrn.hospital.exceptions.TopicDoesNotExistException;
import br.ufrn.hospital.subscriber.AbstractSubscriber;
import br.ufrn.hospital.subscriber.ConcreteSubscriber;
import br.ufrn.model.Paciente;

public class HospitalController {

	private final DAOFactory daoFactory = new HibernateDAOFactory();

	private final PacienteDAOInterface pacienteDAO = daoFactory
			.getPacienteDAO();

	private final WaitNewTopics waitNewTopics;

	private static final Map<String, Paciente> topicsPacientes = Collections
			.synchronizedMap(new HashMap<String, Paciente>());

	public HospitalController() throws ControllerException {
		try {
			waitNewTopics = new WaitNewTopics(this);
			subscreverEmtopicosAtivos();
		} catch (ComunicationException | TopicDoesNotExistException e) {
			throw new ControllerException(e.getMessage());
		}

	}

	private void subscreverEmtopicosAtivos() {
		AbstractSubscriber subscriber = new ConcreteSubscriber("", this);

		String[] topics = subscriber.getAllTopics();

		for (final String topico : topics) {

			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					if (!topico.equals("Novo-Paciente")) {
						subscreverTopico(topico);
					}

				}
			});

			t.start();

		}

	}

	public void subscreverTopico(String topico) {

		AbstractSubscriber subscriber = new ConcreteSubscriber(topico, this);
		try {
			subscriber.subscribe();
			if (!topico.equals("Novo-Paciente"))
				topicsPacientes.put(topico, pacienteDAO.findByCPF(topico));

			System.out.println("Novo paciente cadastrado cpf: " + topico);

		} catch (ComunicationException | TopicDoesNotExistException e) {

			e.printStackTrace();
		} catch (DAOException e) {

			e.printStackTrace();
		}

	}

	public void tratarNotificacaoPaciente(String topico, String mensagem) {

		Paciente p = topicsPacientes.get(topico);

		System.out.println("Notificação: " + p.getNome() + "  \n" + mensagem);
		
		
		/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		 * ITANILDO AQUI NESTE METODO VC DEVE CHAMART A INFERFACE GRAFICA E EMITOR 
		 * O ALERTA D QUE UMA NOTIFICAÇÃO CHEGOU A NOTIFICAÇÃO É A MENSAGEM
		 * E ESTA RELACIONADA AO PACIENTE P
		 */

	}

	public static void main(String[] args) {

		try {
			HospitalController hospitalController = new HospitalController();
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
