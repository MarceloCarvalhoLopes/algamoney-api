package com.algaworks.algamoney.api.repository.lauching;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.ObjectUtils;

import com.algaworks.algamoney.api.model.Launching;
import com.algaworks.algamoney.api.model.Launching_;
import com.algaworks.algamoney.api.repository.filter.LaunchingFilter;

public class LaunchingRepositoryImpl implements LaunchingRepositoryQuery{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Launching> filter(LaunchingFilter launchingFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Launching> criteria = builder.createQuery(Launching.class); 
		Root<Launching> root = criteria.from(Launching.class);
		
		//cria as restrições
		Predicate[] predicates = createRestrictions(launchingFilter,builder,root) ;
		criteria.where(predicates);
		
		TypedQuery<Launching> query = entityManager.createQuery(criteria);
		
		return query.getResultList();
	}

	private Predicate[] createRestrictions(LaunchingFilter launchingFilter, CriteriaBuilder builder,
			Root<Launching> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		//where description like %bla bla bla%
		if (!ObjectUtils.isEmpty(launchingFilter.getDescription())) {
			predicates.add(builder.like(
					builder.lower(root.get(Launching_.description)), "%" + launchingFilter.getDescription().toLowerCase() + "%"));
		}
		
		if (launchingFilter.getDueDateOf() != null) {
			predicates.add(
						builder.greaterThanOrEqualTo(root.get(Launching_.dueDate), launchingFilter.getDueDateOf()));
		}
		
		if (launchingFilter.getDueDateUntil() != null) {
			predicates.add(
						builder.lessThanOrEqualTo(root.get(Launching_.dueDate), launchingFilter.getDueDateUntil()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}



}