/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.indra_prasnikar.m2lt3.control;

import at.indra_prasnikar.m2lt3.bo.Buch;
import at.indra_prasnikar.m2lt3.control.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author rpadmin
 */
public class BuchJpaController implements Serializable {

    public BuchJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public BuchJpaController() {
        this.emf = Persistence.createEntityManagerFactory("at.indra_prasnikar_M2LT3_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Buch buch) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(buch);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Buch buch) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            buch = em.merge(buch);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = buch.getId();
                if (findBuch(id) == null) {
                    throw new NonexistentEntityException("The buch with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Buch buch;
            try {
                buch = em.getReference(Buch.class, id);
                buch.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The buch with id " + id + " no longer exists.", enfe);
            }
            em.remove(buch);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Buch> findBuchEntities() {
        return findBuchEntities(true, -1, -1);
    }

    public List<Buch> findBuchEntities(int maxResults, int firstResult) {
        return findBuchEntities(false, maxResults, firstResult);
    }

    private List<Buch> findBuchEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Buch.class));
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

    public Buch findBuch(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Buch.class, id);
        } finally {
            em.close();
        }
    }

    public int getBuchCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Buch> rt = cq.from(Buch.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
