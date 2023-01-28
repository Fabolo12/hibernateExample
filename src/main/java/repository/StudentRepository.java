package repository;

import config.HibernateUtil;
import model.Student;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class StudentRepository implements GeneralRepository<Student> {
    @Override
    public Optional<Student> getById(final String id) {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        return Optional.ofNullable(entityManager.find(Student.class, id));
    }

    @Override
    public List<Student> getAll() {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        return entityManager.createQuery("from Student ", Student.class)
                .getResultList();
    }

    @Override
    public void delete(final String id) {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Student where id = :id")
                .setParameter("id", id)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }
}
