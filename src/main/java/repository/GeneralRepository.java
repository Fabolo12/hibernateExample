package repository;

import config.HibernateUtil;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface GeneralRepository<T> {
    default void save(@Valid final T object) {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    Optional<T> getById(final String id);

    List<T> getAll();

    void delete(final String id);
}
