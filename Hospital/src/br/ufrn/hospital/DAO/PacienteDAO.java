package br.ufrn.hospital.DAO;

import javax.persistence.Query;

import br.ufrn.hospital.exceptions.DAOException;
import br.ufrn.model.Paciente;

public class PacienteDAO extends GenericHibernateDAO<Paciente, Long> implements PacienteDAOInterface{

	public PacienteDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Paciente findByCPF(String cpf) throws DAOException {
		Paciente p = null;
		System.out.println(cpf+" %%");
		try {
		 Query query = getInstance().createQuery(
					"select o from " + Paciente.class.getSimpleName() + " o where o.idTopico = :cpf");
		 query.setParameter("cpf", cpf);
		 
		 p = (Paciente) query.getSingleResult();
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
		return p;
	}
	
	

}
