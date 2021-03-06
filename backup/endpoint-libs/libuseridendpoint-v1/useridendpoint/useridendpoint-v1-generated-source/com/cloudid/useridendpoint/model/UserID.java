/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2014-04-01 18:14:47 UTC)
 * on 2014-04-12 at 21:07:28 UTC 
 * Modify at your own risk.
 */

package com.cloudid.useridendpoint.model;

/**
 * Model definition for UserID.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the useridendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class UserID extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String dob;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String fName;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String imageURL;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String lName;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long timeStampCreated;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long timeStampLastAccessed;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String ufHash;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String ufID;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDob() {
    return dob;
  }

  /**
   * @param dob dob or {@code null} for none
   */
  public UserID setDob(java.lang.String dob) {
    this.dob = dob;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getFName() {
    return fName;
  }

  /**
   * @param fName fName or {@code null} for none
   */
  public UserID setFName(java.lang.String fName) {
    this.fName = fName;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getImageURL() {
    return imageURL;
  }

  /**
   * @param imageURL imageURL or {@code null} for none
   */
  public UserID setImageURL(java.lang.String imageURL) {
    this.imageURL = imageURL;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getLName() {
    return lName;
  }

  /**
   * @param lName lName or {@code null} for none
   */
  public UserID setLName(java.lang.String lName) {
    this.lName = lName;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getTimeStampCreated() {
    return timeStampCreated;
  }

  /**
   * @param timeStampCreated timeStampCreated or {@code null} for none
   */
  public UserID setTimeStampCreated(java.lang.Long timeStampCreated) {
    this.timeStampCreated = timeStampCreated;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getTimeStampLastAccessed() {
    return timeStampLastAccessed;
  }

  /**
   * @param timeStampLastAccessed timeStampLastAccessed or {@code null} for none
   */
  public UserID setTimeStampLastAccessed(java.lang.Long timeStampLastAccessed) {
    this.timeStampLastAccessed = timeStampLastAccessed;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getUfHash() {
    return ufHash;
  }

  /**
   * @param ufHash ufHash or {@code null} for none
   */
  public UserID setUfHash(java.lang.String ufHash) {
    this.ufHash = ufHash;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getUfID() {
    return ufID;
  }

  /**
   * @param ufID ufID or {@code null} for none
   */
  public UserID setUfID(java.lang.String ufID) {
    this.ufID = ufID;
    return this;
  }

  @Override
  public UserID set(String fieldName, Object value) {
    return (UserID) super.set(fieldName, value);
  }

  @Override
  public UserID clone() {
    return (UserID) super.clone();
  }

}
