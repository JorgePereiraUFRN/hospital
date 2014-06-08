package br.ufrn.hospital.DAO;

import java.io.Serializable;
import java.util.List;

import br.ufrn.hospital.exceptions.DAOException;




public interface GenericDaoInterface<T, ID extends Serializable> {
	
	public T findById(Class<T> classe, ID id) throws DAOException;
	 
	public List<T> findAll(Class<T> classe)throws DAOException;
 
	public T save(T entity)throws DAOException;
 
	public T update(T entity)throws DAOException;
        
	public void delete(T entity)throws DAOException;
}
