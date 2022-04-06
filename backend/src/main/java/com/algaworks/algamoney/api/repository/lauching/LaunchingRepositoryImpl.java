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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import com.algaworks.algamoney.api.model.Launching;
import com.algaworks.algamoney.api.model.Launching_;
import com.algaworks.algamoney.api.repository.filter.LaunchingFilter;

public class LaunchingRepositoryImpl implements LaunchingRepositoryQuery{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<Launching> filter(LaunchingFilter launchingFilter, Pageable pageable) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Launching> criteria = builder.createQuery(Launching.class); 
		Root<Launching> root = criteria.from(Launching.class);
		
		//cria as restrições
		Predicate[] predicates = createRestrictions(launchingFilter,builder,root) ;
		criteria.where(predicates);
		
		TypedQuery<Launching> query = entityManager.createQuery(criteria);
		addingRestricionsOfPagination(query,pageable);
		
		return new PageImpl<>(query.getResultList(),pageable, total(launchingFilter)) ;
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

	private void addingRestricionsOfPagination(TypedQuery<Launching> query, Pageable pageable) {
		int currentPage = pageable.getPageNumber() ;
		int totalRecordPerPager = pageable.getPageSize();
		int firstRecordOfPage = currentPage * totalRecordPerPager;
		
		query.setFirstResult(firstRecordOfPage);
		query.setMaxResults(totalRecordPerPager);
	}


	private Long total(LaunchingFilter launchingFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Launching> root = criteria.from(Launching.class);
		
		Predicate[] predicates = createRestrictions(launchingFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return entityManager.createQuery(criteria).getSingleResult();
	}
}
