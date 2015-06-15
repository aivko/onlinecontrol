package com.vizaco.onlinecontrol.dao.jpa;

import com.vizaco.onlinecontrol.dao.StudentDao;
import com.vizaco.onlinecontrol.model.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    @Override
    public List<Student> findByLastName(String lastName) {
        Query query = this.em.createQuery("SELECT DISTINCT student FROM Student student left join fetch student.parents WHERE student.lastName LIKE :lastName");
        query.setParameter("lastName", lastName + "%");
        return query.getResultList();
    }

    @Override
    public Student findById(Long id) {
        Query query = this.em.createQuery("SELECT DISTINCT student FROM Student student left join fetch student.parents WHERE student.id =:id");
        query.setParameter("id", id);

        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null; // handle no-results case
        } else {
            return (Student)resultList.get(0);
        }
        
    }

    @Override
    public List<Student> getAllStudents() throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT student FROM Student student left join fetch student.parents");
        return query.getResultList();
    }


    @Override
    public void save(Student student) {

        if (student == null){
            return;
        }

    	if (student.getId() == null) {
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
