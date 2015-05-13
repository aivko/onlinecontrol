package com.vizaco.onlinecontrol.dao.jpa;

import com.vizaco.onlinecontrol.dao.StudentDao;
import com.vizaco.onlinecontrol.model.Role;
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

    public JpaStudentDaoImpl(EntityManager em) {
        this.em = em;
    }

    public List<Student> findByLastName(String lastName) {
        Query query = this.em.createQuery("SELECT DISTINCT student FROM Student student left join fetch student.users WHERE student.lastName LIKE :lastName");
        query.setParameter("lastName", lastName + "%");
        List resultList = query.getResultList();
        return resultList;
    }

    @Override
    public Student findById(Long id) {
        Query query = this.em.createQuery("SELECT DISTINCT student FROM Student student left join fetch student.users WHERE student.studentId =:id");
        query.setParameter("id", id);

        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null; // handle no-results case
        } else {
            return (Student)resultList.get(0);
        }
        
//        return (Student) query.getSingleResult();
    }

    @Override
    public List<Student> getAllStudents() throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT student FROM Student student");
        List resultList = query.getResultList();
        return resultList;
    }


    @Override
    public void save(Student student) {

        if (student == null){
            return;
        }

    	if (student.getStudentId() == null) {
    		this.em.persist(student);
    	}
    	else {
    		this.em.merge(student);
    	}

    }

    @Override
    public void delete(Student student) throws DataAccessException {
        if (student == null){
            return;
        }
        this.em.remove(student);
    }

}
