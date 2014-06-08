package br.ufrn.hospital.DAO;

import br.ufrn.hospital.exceptions.DAOException;
import br.ufrn.model.Paciente;

public interface PacienteDAOInterface extends GenericDaoInterface<Paciente, Long> {

	public Paciente findByCPF(String cpf) throws DAOException;
	
}
