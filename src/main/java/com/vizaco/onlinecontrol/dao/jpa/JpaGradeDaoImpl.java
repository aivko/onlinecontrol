//package com.vizaco.onlinecontrol.dao.jpa;
//
//import com.vizaco.onlinecontrol.dao.GradeDao;
//import com.vizaco.onlinecontrol.dao.RoleDao;
//import com.vizaco.onlinecontrol.model.Grade;
//import com.vizaco.onlinecontrol.model.Role;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.FlushModeType;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.util.List;
//
//@Repository
//public class JpaGradeDaoImpl implements GradeDao {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    public JpaGradeDaoImpl() {
//    }
//
//    public JpaGradeDaoImpl(EntityManager em) {
//        this.em = em;
//    }
//
//    @Override
//    public Grade findById(Integer id) {
//        Query query = this.em.createQuery("SELECT DISTINCT grade FROM Grade grade WHERE grade.id =:id");
//        query.setParameter("id", id);
//
//        List resultList = query.getResultList();
//        if (resultList.isEmpty()) {
//            return null; // handle no-results case
//        } else {
//            return (Grade)resultList.get(0);
//        }
//    }
//
//    @Override
//    public List<Grade> getAllGrades() {
//        Query query = this.em.createQuery("SELECT DISTINCT grade FROM Grade grade");
//        return query.getResultList();
//    }
//
//
//    @Override
//    public Grade save(Grade grade) {
//
//        if (grade == null){
//            return null;
//        }
//
//    	if (grade.getId() == null) {
//    		this.em.persist(grade);
//    	}
//    	else {
//    		return this.em.merge(grade);
//    	}
//        return grade;
//    }
//
//    @Override
//    public void delete(Grade grade) {
//        if (grade == null){
//            return;
//        }
//        em.remove(grade);
//    }
//
//    @Override
//    public void flush() {
//        this.em.flush();
//    }
//
//}
