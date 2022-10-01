/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.AtendimentoModel;
import static org.hibernate.criterion.Projections.id;
import util.ConnectionFactory;

/**
 *
 * @author jonathandamasiomedeiros
 */
public class AtendimentoController {

    private EntityManager entityManager = null;

    public int save(AtendimentoModel atendimentoModel) throws SQLException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(atendimentoModel);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return atendimentoModel.getId();
    }

    public List<AtendimentoModel> getAll() throws SQLException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Query query = entityManager.createQuery("from AtendimentoModel");
            return query.getResultList();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

    }

    public AtendimentoModel getFirst() throws SQLException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Query query = entityManager.createQuery("from AtendimentoModel where status = 0 order by id asc");
            query.setMaxResults(1);
            return (AtendimentoModel) query.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

    }

    public List<AtendimentoModel> getNextList() throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Query query = entityManager.createQuery("from AtendimentoModel where status = 0 order by id asc");
            query.setMaxResults(3);
            return query.getResultList();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

    }

    public List<AtendimentoModel> getChamadosList() throws SQLException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Query query = entityManager.createQuery("from AtendimentoModel where status = 2 order by id desc");
            query.setMaxResults(3);
            return query.getResultList();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public AtendimentoModel getChamado() throws SQLException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Query query = entityManager.createQuery("from AtendimentoModel where status = 1 order by id asc");
            query.setMaxResults(1);
            return (AtendimentoModel) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Não existe ninguem sendo atendido");
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return null;
    }

    public void updateJaAtendido() throws SQLException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Query query = entityManager.createQuery("update AtendimentoModel SET status = 2 where status = 1");
            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void update(AtendimentoModel atendimentoModel) throws SQLException {
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin(); 
            entityManager.merge(atendimentoModel);
            entityManager.getTransaction().commit();

            entityManager.close();
            entityManagerFactory.close();
    }
}
