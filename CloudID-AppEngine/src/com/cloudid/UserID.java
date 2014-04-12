package com.cloudid;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserID {

	@Id
	private String UfHash;
	private String UfID;
	private String fName;
	
}
