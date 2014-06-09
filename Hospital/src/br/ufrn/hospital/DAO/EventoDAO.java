package br.ufrn.hospital.DAO;

import java.util.List;

import javax.persistence.Query;

import br.ufrn.model.Evento;
import br.ufrn.model.Paciente;

public class EventoDAO extends GenericHibernateDAO<Evento, Long> implements EventoDaoInterface {

	public EventoDAO() {
		// TODO Auto-generated constructor stub
	}
	
    @Override
    public List<Evento> listarEventosPorPaciente(Paciente p) {
       List<Evento> list = null;
       Query q = getInstance().createQuery(
                    "select o from " + Evento.class.getSimpleName() + " o WHERE o.paciente = :pacienteid");
       q.setParameter("pacienteid", p);
       list = q.getResultList();
       return list;
    }

}
