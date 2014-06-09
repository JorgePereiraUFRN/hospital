package br.ufrn.hospital.controller;

import java.util.Calendar;

import br.ufrn.hospital.DAO.DAOFactory;
import br.ufrn.hospital.DAO.EventoDaoInterface;
import br.ufrn.hospital.DAO.HibernateDAOFactory;
import br.ufrn.hospital.DAO.PacienteDAOInterface;
import br.ufrn.hospital.exceptions.ComunicationException;
import br.ufrn.hospital.exceptions.ControllerException;
import br.ufrn.hospital.exceptions.DAOException;
import br.ufrn.hospital.exceptions.ObjetoNuloException;
import br.ufrn.hospital.exceptions.TopicAlreadyExistsException;
import br.ufrn.hospital.exceptions.ValorInvalidoException;
import br.ufrn.model.Evento;
import br.ufrn.model.Paciente;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PacienteController {

	private static final DAOFactory daoFactory = new HibernateDAOFactory();
	private static final PacienteDAOInterface pacienteDAO = daoFactory
			.getPacienteDAO();
	private EventoDaoInterface eventoDAO = daoFactory
			.getEventoDAO();

	private static final String uriHub = "http://localhost:8080/hub/hub/";

	private static final String QTD_SORO = "qtdSoro", PRESSAO = "pressao",
			BATIMENTOS = "batimentos";

	private boolean ValidarPaciente(Paciente p) throws ObjetoNuloException,
			ValorInvalidoException {

		if (p == null) {
			throw new ObjetoNuloException("Paciente nulo");
		}

		/*
		 * if (p.getId() == null || p.getId() < 0) { throw new
		 * ValorInvalidoException( "o identificador do paciente é invalido!"); }
		 */

		if (p.getIdTopico() == null || p.getIdTopico().equals("")) {
			throw new ValorInvalidoException(
					"o identificador do tópico é invalido!");
		}

		if (p.getNome() == null || p.getNome().equals("")) {
			throw new ValorInvalidoException("o nome do paciento está vazio!");
		}

		if (p.getDiagnostico() == null || p.getDiagnostico().equals("")) {
			throw new ValorInvalidoException(
					"o diagnóstico do paciente é vazio!");
		}

		return true;
	}
        
       public List<Paciente> listarPacientes() throws DAOException  {
            List<Paciente> pacientes;
            pacientes = pacienteDAO.findAll(Paciente.class);
            return pacientes;
	}

}
