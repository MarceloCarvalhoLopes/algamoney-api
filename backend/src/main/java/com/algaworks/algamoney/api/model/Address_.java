package com.algaworks.algamoney.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Address.class)
public abstract class Address_ {

	public static volatile SingularAttribute<Address, String> number;
	public static volatile SingularAttribute<Address, String> zipCode;
	public static volatile SingularAttribute<Address, String> address;
	public static volatile SingularAttribute<Address, String> city;
	public static volatile SingularAttribute<Address, String> district;
	public static volatile SingularAttribute<Address, String> state;
	public static volatile SingularAttribute<Address, String> adjunct;

	public static final String NUMBER = "number";
	public static final String ZIP_CODE = "zipCode";
	public static final String ADDRESS = "address";
	public static final String CITY = "city";
	public static final String DISTRICT = "district";
	public static final String STATE = "state";
	public static final String ADJUNCT = "adjunct";

}

