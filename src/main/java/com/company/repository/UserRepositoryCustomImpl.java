package com.company.repository;

import com.company.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("userDao")
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @PersistenceContext
    EntityManager em ;

    @Override
    public List<User> getAll(String name, String surname) {
        String jpql="select u from User u where 1=1";

        if (name != null && !name.trim().isEmpty()) {
            jpql += " and u.name=:name ";
        }

        if (surname != null && !surname.trim().isEmpty()) {
            jpql += " and u.surname=:surname ";
        }

        Query q = em.createQuery(jpql, User.class);

        if (name != null && !name.trim().isEmpty()) {
            q.setParameter("name", name);
        }

        if (surname != null && !surname.trim().isEmpty()) {
            q.setParameter("surname", surname);
        }

        return q.getResultList();
    }

    @Override
    public User findByUsernameAndPassword(String email, String password) {
        Query q = em.createQuery("select u from User u where u.username=:e and u.password=:p", User.class);
        q.setParameter("e", email);
        q.setParameter("p", password);

        List<User> list = q.getResultList();
        if(list.size()==1){
            return list.get(0);
        }
        return null ;
    }

    @Override
    public User findByUsername(String email) {
        Query q = em.createQuery("select u from User u where u.username=:e", User.class);
        q.setParameter("e", email);

        List<User> list = q.getResultList();
        if(list.size()==1){
            return list.get(0);
        }
        return null ;
    }

}
