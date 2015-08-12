//package com.vizaco.onlinecontrol.dao.jpa;
//
//import com.vizaco.onlinecontrol.dao.ParentDao;
//import com.vizaco.onlinecontrol.dao.TeacherDao;
//import com.vizaco.onlinecontrol.model.Parent;
//import com.vizaco.onlinecontrol.model.Student;
//import com.vizaco.onlinecontrol.model.Teacher;
//import com.vizaco.onlinecontrol.model.User;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.util.List;
//
//@Repository
//public class JpaTeacherDaoImpl implements TeacherDao {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    public JpaTeacherDaoImpl() {
//    }
//
//    public JpaTeacherDaoImpl(EntityManager em) {
//        this.em = em;
//    }
//
//    @Override
//    public Teacher findById(Integer id) {
//        Query query = this.em.createQuery("SELECT DISTINCT teacher FROM Teacher teacher WHERE teacher.id =:id");
//        query.setParameter("id", id);
//
//        List resultList = query.getResultList();
//        if (resultList.isEmpty()) {
//            return null; // handle no-results case
//        } else {
//            return (Teacher)resultList.get(0);
//        }
//    }
//
//    @Override
//    public Teacher findTeacherByUser(User user) {
//
//        Query query = this.em.createQuery("SELECT DISTINCT teacher FROM Teacher teacher where teacher.user =:user");
//        query.setParameter("user", user);
//
//        List resultList = query.getResultList();
//        if (resultList.isEmpty()) {
//            return null; // handle no-results case
//        } else {
//            return (Teacher)resultList.get(0);
//        }
//    }
//
//    @Override
//    public List<Teacher> getAllTeachers() {
//        Query query = this.em.createQuery("SELECT DISTINCT teacher FROM Teacher teacher");
//        return query.getResultList();
//    }
//
//    @Override
//    public void save(Teacher teacher) {
//
//        if (teacher == null){
//            return;
//        }
//
//    	if (teacher.getId() == null) {
//    		this.em.persist(teacher);
//    	}
//    	else {
//    		this.em.merge(teacher);
//    	}
//    }
//
//    @Override
//    public void delete(Teacher teacher) {
//        if (teacher == null){
//            return;
//        }
//        this.em.remove(teacher);
//    }
//
//}
