package ru.kata.spring.boot_security.demo.dao.role;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getRoles() {
        return entityManager.createQuery("FROM Role ", Role.class).getResultList();
    }

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(getRole(id));
    }

    @Override
    public Role getRole(int id) {
        return entityManager.find(Role.class, id);
    }
}
