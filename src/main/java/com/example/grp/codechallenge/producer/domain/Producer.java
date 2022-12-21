package com.example.grp.codechallenge.producer.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.example.grp.codechallenge.movie.domain.Movie;

@Entity
@Table(name = "PRODUCERS")
public class Producer {

	@Id
	@GeneratedValue(generator = "uuid4")
	@GenericGenerator(name = "UUID", strategy = "uuid4")
	@Type(type = "org.hibernate.type.UUIDCharType")
	@Column(name ="ID", columnDefinition = "CHAR(36)")
	private UUID id;
	
	@Column(name = "NAME", length = 100)
	private String name;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "PRODUCER_MOVIES"
    	, joinColumns = { @JoinColumn(name = "PRODUCER_ID") }
    	, inverseJoinColumns = { @JoinColumn(name = "MOVIE_ID") } )
	private List<Movie> movies;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public void add(Movie movie) {
		if (movies == null) {
			movies = new ArrayList<>();
		}
		movies.add(movie);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producer other = (Producer) obj;
		return Objects.equals(id, other.id);
	}
	
}
