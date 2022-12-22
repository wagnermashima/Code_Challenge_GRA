package com.example.grp.codechallenge.movie.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.example.grp.codechallenge.movie.domain.Movie;

@Controller
public class MovieRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public Movie save(Movie movie) {
		entityManager.persist(movie);
		return movie;
	}
	
	@SuppressWarnings("unchecked")
	public List<Movie> findMovies() {
		StringBuilder hql = new StringBuilder();
		hql.append("select movi from Movie movi");
		Query query = entityManager.createQuery(hql.toString());
		return query.getResultList();
	}

	public Long count() {
		StringBuilder hql = new StringBuilder();
		hql.append("select count(movi) from Movie movi");
		Query query = entityManager.createQuery(hql.toString());
		return (Long) query.getSingleResult();
	}

	public void clear() {
		Query delete = entityManager.createQuery("delete from Movie");
		delete.executeUpdate();
	}

}
