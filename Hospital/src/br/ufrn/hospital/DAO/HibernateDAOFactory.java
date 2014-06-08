
package br.ufrn.hospital.DAO;




public class HibernateDAOFactory extends DAOFactory {

	
	
	private GenericHibernateDAO instantiateDAO(Class daoClass) {
        try {
            GenericHibernateDAO dao = (GenericHibernateDAO)daoClass.newInstance();
            //dao.setSession(getCurrentSession());
            return dao;
        } catch (Exception ex) {
            throw new RuntimeException("Can not instantiate DAO: " + daoClass, ex);
        }
    }

	@Override
	public PacienteDAO getPacienteDAO() {
		return (PacienteDAO) instantiateDAO(PacienteDAO.class);
	}

	@Override
	public EventoDAO getEventoDAO() {
		return (EventoDAO) instantiateDAO(EventoDAO.class);
	}


	
	
	
}

