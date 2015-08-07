package com.vizaco.onlinecontrol.dao.springdatajpa;

import com.vizaco.onlinecontrol.dao.BusinessDao;
import com.vizaco.onlinecontrol.model.Person;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpringDataBusinessRepository extends BusinessDao, JpaRepository<User, Integer> {

    @Override
    @Query("SELECT DISTINCT student FROM Student student where student.user =:user")
    public Person getCurrentPerson(@Param("user") User user); /*{

        //TODO: need optimisation
        Query query;
        List resultList;
        query = this.em.createQuery("SELECT DISTINCT student FROM Student student where student.user =:user");
        query.setParameter("user", user);
        resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return (Person)resultList.get(0);
        }
        query = this.em.createQuery("SELECT DISTINCT teacher FROM Teacher teacher where teacher.user =:user");
        query.setParameter("user", user);
        resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return (Person)resultList.get(0);
        }
        query = this.em.createQuery("SELECT DISTINCT parent FROM Parent parent where parent.user =:user");
        query.setParameter("user", user);
        resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return (Person)resultList.get(0);
        }
        return null;

    }
*/
}
