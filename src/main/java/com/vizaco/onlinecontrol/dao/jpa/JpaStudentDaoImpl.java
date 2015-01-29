package com.vizaco.onlinecontrol.dao.jpa;

import com.vizaco.onlinecontrol.dao.StudentDao;
import com.vizaco.onlinecontrol.model.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

@Repository
public class JpaStudentDaoImpl implements StudentDao {

    @PersistenceContext
    private EntityManager em;

    public JpaStudentDaoImpl() {
    }

    public Collection<Student> findByLastName(String lastName) {
        Query query = this.em.createQuery("SELECT DISTINCT student FROM Student student left join fetch student.users WHERE student.lastName LIKE :lastName");
        query.setParameter("lastName", lastName + "%");
        List resultList = query.getResultList();
        return query.getResultList();
    }

    @Override
    public Student findById(String id) {
        Query query = this.em.createQuery("SELECT DISTINCT student FROM Student student left join fetch student.users WHERE student.studentId =:id");
        query.setParameter("id", id);
        return (Student) query.getSingleResult();
    }

    @Override
    public List<Student> getAllStudents() throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT student FROM Student student");
        List resultList = query.getResultList();
        return query.getResultList();
    }


    @Override
    public void save(Student student) {
    	if (student.getStudentId() == null) {
    		this.em.persist(student);
    	}
    	else {
    		this.em.merge(student);
    	}

    }

}
