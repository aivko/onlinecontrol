package com.vizaco.onlinecontrol.dao.springdatajpa.specifications;

import com.vizaco.onlinecontrol.model.Shedule;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by super on 8/11/15.
 */
public class SheduleSpec {

    public static Specification<Shedule> isLongTermCustomer() {
        return new Specification<Shedule>() {
            public Predicate toPredicate(Root<Shedule> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

                return null;

            }
        };
    }

}