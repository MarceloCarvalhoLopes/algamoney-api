package com.algaworks.algamoney.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SystemUser.class)
public abstract class SystemUser_ {

	public static volatile SingularAttribute<SystemUser, String> password;
	public static volatile ListAttribute<SystemUser, Permission> permissions;
	public static volatile SingularAttribute<SystemUser, String> name;
	public static volatile SingularAttribute<SystemUser, Long> id;
	public static volatile SingularAttribute<SystemUser, String> email;

	public static final String PASSWORD = "password";
	public static final String PERMISSIONS = "permissions";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String EMAIL = "email";

}

