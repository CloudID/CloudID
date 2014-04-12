package com.cloudid;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserID {

	@Id
	private String UfHash;
	@Id
	private String lastLocation;
	private String UfID;
	private String fName;
	private String lName;
	private String dob;
	private String imageURL;
	private long timeStampCreated;
	private long timeStampLastAccessed;
	
	
	public long getTimeStampCreated() {
		return timeStampCreated;
	}
	public void setTimeStampCreated(long timeStampCreated) {
		this.timeStampCreated = timeStampCreated;
	}
	public long getTimeStampLastAccessed() {
		return timeStampLastAccessed;
	}
	public void setTimeStampLastAccessed(long timeStampLastAccessed) {
		this.timeStampLastAccessed = timeStampLastAccessed;
	}
	public String getUfHash() {
		return UfHash;
	}
	public void setUfHash(String ufHash) {
		UfHash = ufHash;
	}
	public String getUfID() {
		return UfID;
	}
	public void setUfID(String ufID) {
		UfID = ufID;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
}
