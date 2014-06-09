package br.ufrn.hospital.DAO;

import java.util.List;

import br.ufrn.model.Evento;
import br.ufrn.model.Paciente;

public interface EventoDaoInterface extends GenericDaoInterface<Evento, Long>{
	public List<Evento> listarEventosPorPaciente(Paciente p);
}
