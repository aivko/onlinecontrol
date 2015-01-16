/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vizaco.onlinecontrol.dao.jpa;

import com.vizaco.onlinecontrol.dao.StudentDao;
import com.vizaco.onlinecontrol.model.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

/**
 * JPA implementation of the {@link StudentDao} interface.
 *
 */
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
    public void save(Student student) {
    	if (student.getStudentId() == null) {
    		this.em.persist(student);
    	}
    	else {
    		this.em.merge(student);
    	}

    }

}
