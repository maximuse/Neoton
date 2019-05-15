/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Albumok;

/**
 *
 * @author maximuse
 */
public class AlbumokJpaController implements Serializable {

    public AlbumokJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Albumok albumok) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(albumok);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Albumok albumok) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            albumok = em.merge(albumok);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = albumok.getId();
                if (findAlbumok(id) == null) {
                    throw new NonexistentEntityException("The albumok with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Albumok albumok;
            try {
                albumok = em.getReference(Albumok.class, id);
                albumok.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The albumok with id " + id + " no longer exists.", enfe);
            }
            em.remove(albumok);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Albumok> findAlbumokEntities() {
        return findAlbumokEntities(true, -1, -1);
    }

    public List<Albumok> findAlbumokEntities(int maxResults, int firstResult) {
        return findAlbumokEntities(false, maxResults, firstResult);
    }

    private List<Albumok> findAlbumokEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Albumok.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Albumok findAlbumok(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Albumok.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlbumokCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Albumok> rt = cq.from(Albumok.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
