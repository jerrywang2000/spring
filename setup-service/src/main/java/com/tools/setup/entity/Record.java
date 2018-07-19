package com.tools.setup.entity;

import java.util.Date;
import org.springframework.hateoas.ResourceSupport;


public class Record extends ResourceSupport {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record.RECORD_ID
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    private String recordId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record.RECORD_NAME
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    private String recordName;

    
    private String apiName;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record.DESCRIPTION
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record.RECORD_TYPE
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    private String recordType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record.RECORD_STATUS
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    private String recordStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record.SOURCE
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    private String source;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record.CREATED_DATE
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record.CREATED_BY
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    private String createdBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record.LAST_UPDATED_DATE
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    private Date lastUpdatedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record.LAST_UPDATED_BY
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    private String lastUpdatedBy;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record.RECORD_ID
     *
     * @return the value of record.RECORD_ID
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    public String getRecordId() {
        return recordId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record.RECORD_ID
     *
     * @param recordId the value for record.RECORD_ID
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record.RECORD_NAME
     *
     * @return the value of record.RECORD_NAME
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    public String getRecordName() {
        return recordName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record.RECORD_NAME
     *
     * @param recordName the value for record.RECORD_NAME
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    public void setRecordName(String recordName) {
        this.recordName = recordName == null ? null : recordName.trim();
    }

    public String getApiName() {
        return apiName;
    }


    public void setApiName(String apiName) {
        this.apiName = apiName == null ? null : apiName.trim();
    }
    
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record.DESCRIPTION
     *
     * @return the value of record.DESCRIPTION
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record.DESCRIPTION
     *
     * @param description the value for record.DESCRIPTION
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record.RECORD_TYPE
     *
     * @return the value of record.RECORD_TYPE
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    public String getRecordType() {
        return recordType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record.RECORD_TYPE
     *
     * @param recordType the value for record.RECORD_TYPE
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    public void setRecordType(String recordType) {
        this.recordType = recordType == null ? null : recordType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record.RECORD_STATUS
     *
     * @return the value of record.RECORD_STATUS
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    public String getRecordStatus() {
        return recordStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record.RECORD_STATUS
     *
     * @param recordStatus the value for record.RECORD_STATUS
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus == null ? null : recordStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record.SOURCE
     *
     * @return the value of record.SOURCE
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record.SOURCE
     *
     * @param source the value for record.SOURCE
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record.CREATED_DATE
     *
     * @return the value of record.CREATED_DATE
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record.CREATED_DATE
     *
     * @param createdDate the value for record.CREATED_DATE
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record.CREATED_BY
     *
     * @return the value of record.CREATED_BY
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record.CREATED_BY
     *
     * @param createdBy the value for record.CREATED_BY
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record.LAST_UPDATED_DATE
     *
     * @return the value of record.LAST_UPDATED_DATE
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record.LAST_UPDATED_DATE
     *
     * @param lastUpdatedDate the value for record.LAST_UPDATED_DATE
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record.LAST_UPDATED_BY
     *
     * @return the value of record.LAST_UPDATED_BY
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record.LAST_UPDATED_BY
     *
     * @param lastUpdatedBy the value for record.LAST_UPDATED_BY
     *
     * @mbg.generated Fri Jun 22 14:44:25 CST 2018
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy == null ? null : lastUpdatedBy.trim();
    }
}