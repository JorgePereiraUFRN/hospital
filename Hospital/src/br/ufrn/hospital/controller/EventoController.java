/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.hospital.controller;

import br.ufrn.hospital.DAO.DAOFactory;
import br.ufrn.hospital.DAO.EventoDaoInterface;
import br.ufrn.hospital.DAO.HibernateDAOFactory;
import br.ufrn.hospital.DAO.PacienteDAOInterface;
import br.ufrn.hospital.exceptions.DAOException;
import br.ufrn.hospital.exceptions.ObjetoNuloException;
import br.ufrn.hospital.exceptions.ValorInvalidoException;
import br.ufrn.model.Evento;
import br.ufrn.model.Paciente;
import java.util.List;

/**
 *
 * @author augusto
 */
public class EventoController {
    	private static final DAOFactory daoFactory = new HibernateDAOFactory();
	private static final EventoDaoInterface eventoDAO = daoFactory
			.getEventoDAO();

	private boolean ValidarEvento(Evento e) throws ObjetoNuloException,
			ValorInvalidoException {

		if (e == null) {
			throw new ObjetoNuloException("evento nulo");
		}


		if (e.getDescricao() == null || e.getDescricao().equals("")) {
			throw new ValorInvalidoException(
					"A descricao do evento eh invalida");
		}

		if (e.getPaciente() == null) {
			throw new ValorInvalidoException("Nao ha paciente associado a esse evento");
		}

		if (e.getData() == null) {
			throw new ValorInvalidoException(
					"A data eh invalida");
		}

		return true;
	}
        
        public List<Evento> listarEventos() throws DAOException  {
            List<Evento> eventos;
            eventos = eventoDAO.findAll(Evento.class);
            return eventos;
	}
        
        public List<Evento> listarEventosPorPaciente() throws DAOException  {
            List<Evento> eventos;
            eventos = eventoDAO.findAll(Evento.class);
            return eventos;
	}
        
        public List<Evento> listarEventosPorPaciente(Paciente p) throws DAOException  {
            List<Evento> eventos;
            eventos = eventoDAO.listarEventosPorPaciente(p);
            return eventos;
	}
        
}
