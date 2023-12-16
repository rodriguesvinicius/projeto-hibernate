package br.com.projetohibernate.dao;

import br.com.projetohibernate.config.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class GenericDao <T, ID extends Serializable> {
    private final Class<T> persistentClass;

    public GenericDao(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public void save(T entity) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.persist(entity);
                transaction.commit();
            } catch (HibernateException ex) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }

    public void update(T entity) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.merge(entity);
                transaction.commit();
            } catch (HibernateException ex) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }

    public T findById(ID id) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            return session.get(persistentClass, id);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<T> findAll() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            String hql = "FROM ".concat(persistentClass.getSimpleName());
            return session.createQuery(hql, persistentClass).getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return Collections.emptyList();
    }

    public void delete(ID id) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                T entity = session.get(persistentClass, id);
                if (entity != null) {
                    session.remove(entity);
                }
                transaction.commit();
            } catch (HibernateException ex) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }
}
