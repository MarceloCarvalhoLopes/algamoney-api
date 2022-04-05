package com.algaworks.algamoney.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Launching.class)
public abstract class Launching_ {

	public static volatile SingularAttribute<Launching, String> observation;
	public static volatile SingularAttribute<Launching, Person> person;
	public static volatile SingularAttribute<Launching, LocalDate> dueDate;
	public static volatile SingularAttribute<Launching, String> description;
	public static volatile SingularAttribute<Launching, Long> id;
	public static volatile SingularAttribute<Launching, LocalDate> paymentDate;
	public static volatile SingularAttribute<Launching, LaunchingType> type;
	public static volatile SingularAttribute<Launching, Category> category;
	public static volatile SingularAttribute<Launching, BigDecimal> value;

	public static final String OBSERVATION = "observation";
	public static final String PERSON = "person";
	public static final String DUE_DATE = "dueDate";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String PAYMENT_DATE = "paymentDate";
	public static final String TYPE = "type";
	public static final String CATEGORY = "category";
	public static final String VALUE = "value";

}

