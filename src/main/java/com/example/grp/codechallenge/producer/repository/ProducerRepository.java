package com.example.grp.codechallenge.producer.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Controller;

import com.example.grp.codechallenge.producer.domain.Producer;
import com.example.grp.codechallenge.producer.domain.ProducerWinner;

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
	
	@SuppressWarnings("unchecked")
	public List<ProducerWinner> findWinnersMoreThanOnce() {
		StringBuilder hql = new StringBuilder();
		hql.append("select prod.name ");
		hql.append(", movi.title ");
		hql.append(", movi.year ");
		hql.append("from Movie movi ");
		hql.append("inner join movi.producers prod ");
		hql.append("where ( select count(movi2) from Movie movi2 ");
		hql.append("		inner join movi2.producers prod2 ");
		hql.append("		where movi2.winner = true ");
		hql.append("		and prod2 = prod ) > 1 ");
		hql.append("order by prod.name, movi.year ");
		Query query = entityManager.createQuery(hql.toString());
		return convertProducerWinner(query.getResultList());
	}

	private List<ProducerWinner> convertProducerWinner(List<Object[]> list) {
		return list.stream().map(i -> createProducerWinner(i)).collect(Collectors.toList());
	}
	
	private ProducerWinner createProducerWinner(Object[] obj) {
		ProducerWinner winner = new ProducerWinner();
		winner.setName((String) obj[0]);
		winner.setTitle((String) obj[1]);
		winner.setYear((Integer) obj[2]);
		return winner;
	}

}
