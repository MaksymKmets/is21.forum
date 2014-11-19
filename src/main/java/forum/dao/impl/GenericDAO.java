package forum.dao.impl;



import forum.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

abstract class GenericDAO<T> implements Serializable {
	private static final long serialVersionUID = 1L;
    protected Session session;
	private Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    public void update(T entity){
        try{
            beginTransaction();
            updateEntity(entity);
            commit();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }finally {
            if(session.isOpen()){
                closeTransaction();
            }

        }

    }
    public void save(T entity){
        beginTransaction();
        saveEntity(entity);
        commitAndCloseTransaction();
    }
    public void delete(T entity){
        beginTransaction();
        session.delete(entity);
        commitAndCloseTransaction();
    }
    public T find(Integer id){
        try{
            beginTransaction();
            return findById(id);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            commitAndCloseTransaction();
        }
    }
    // Using the unchecked because JPA does not have a
    // em.getCriteriaBuilder().createQuery()<T> method
    public List<T> getAll() {
        ArrayList<T> result;
        beginTransaction();
        result=(ArrayList<T>)session.createCriteria(entityClass).list();
        commitAndCloseTransaction();
        return result;
    }
    public List<T> getAllDesc(){
        ArrayList<T> result;
        beginTransaction();
        result=(ArrayList<T>)session.createCriteria(entityClass).addOrder( Order.desc("date") ).list();
        commitAndCloseTransaction();
        return result;
    }

	protected void beginTransaction() {
        session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	protected void commit() {
		session.getTransaction().commit();
	}
	protected void closeTransaction() {
		session.close();
	}
	protected void commitAndCloseTransaction() {
		commit();
		closeTransaction();
	}



	protected void saveEntity(T entity) {
		session.save(entity);
	}
    protected void updateEntity(T entity){
        session.update(entity);
    }
    protected T findById(Integer entityID) {
		return (T) session.get(entityClass, entityID);
	}

	// Using the unchecked because JPA does not have a
	// query.getSingleResult()<T> method
	@SuppressWarnings("unchecked")
	protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
		T result = null;

		try {
			Query query = session.createQuery(namedQuery);

			// Method that will populate parameters if they are passed not null and empty
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			result = (T) query.getFirstResult();

		} catch (NoResultException e) {
			System.out.println("No result found for named query: " + namedQuery);
		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	private void populateQueryParameters(Query query, Map<String, Object> parameters) {
		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}
}
