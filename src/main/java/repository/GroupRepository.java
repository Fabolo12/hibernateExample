package repository;

import config.HibernateUtil;
import model.Group;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class GroupRepository implements GeneralRepository<Group> {
    @Override
    public Optional<Group> getById(final String id) {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        return Optional.ofNullable(entityManager.find(Group.class, id));
    }

    @Override
    public List<Group> getAll() {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        return entityManager.createQuery("from Group ", Group.class)
                .getResultList();
    }

    @Override
    public void delete(final String id) {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Group where id = :id")
                .setParameter("id", id)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }
}
