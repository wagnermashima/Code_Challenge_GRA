package com.example.grp.codechallenge.producer.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Controller;

import com.example.grp.codechallenge.producer.domain.Producer;

@Controller
public class ProducerRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Producer save(Producer producer) {
		entityManager.persist(producer);
		return producer;
	}

	@SuppressWarnings("unchecked")
	public Producer findByName(String name) {
		StringBuilder hql = new StringBuilder();
		hql.append("select prod ");
		hql.append("from Producer prod ");
		hql.append("where prod.name like ?1 ");
		Query query = entityManager.createQuery(hql.toString());
		query.setParameter(1, name);
		
		return (Producer) query.getResultStream().findFirst().orElse(null);
	}

	public Long count() {
		StringBuilder hql = new StringBuilder();
		hql.append("select count(prod) ");
		hql.append("from Producer prod ");
		Query query = entityManager.createQuery(hql.toString());
		return (Long) query.getSingleResult();
	}
	
	public Producer findSomethin() {
		StringBuilder hql = new StringBuilder();
		hql.append("select prod ");
		hql.append("from Producer prod ");
		hql.append("inner join prod.movies movi ");
		hql.append("inner join prod.movies movi ");
		hql.append("where prod.name like ?1 ");
		Query query = entityManager.createQuery(hql.toString());
		
		return (Producer) query.getResultStream().findFirst().orElse(null);
	}

}
