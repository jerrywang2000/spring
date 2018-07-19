package com.tools.objectManagement.entity;

import java.util.Date;

public class Customer extends BaseEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.CUSTOMER_ID
     *
     * @mbg.generated Thu Jun 28 16:17:11 CST 2018
     */
    private Integer customerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.CUSTOMER_NAME
     *
     * @mbg.generated Thu Jun 28 16:17:11 CST 2018
     */
    private String customerName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.STATUS
     *
     * @mbg.generated Thu Jun 28 16:17:11 CST 2018
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.CREATED_DATE
     *
     * @mbg.generated Thu Jun 28 16:17:11 CST 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.CREATED_BY
     *
     * @mbg.generated Thu Jun 28 16:17:11 CST 2018
     */
    private String createdBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.LAST_UPDATED_DATE
     *
     * @mbg.generated Thu Jun 28 16:17:11 CST 2018
     */
    private Date lastUpdatedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.LAST_UPDATED_BY
     *
     * @mbg.generated Thu Jun 28 16:17:11 CST 2018
     */
    private String lastUpdatedBy;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.CUSTOMER_ID
     *
     * @return the value of customer.CUSTOMER_ID
     *
     * @mbg.generated Thu Jun 28 16:17:11 CST 2018
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.CUSTOMER_ID
     *
     * @param customerId the value for customer.CUSTOMER_ID
     *
     * @mbg.generated Thu Jun 28 16:17:11 CST 2018
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.CUSTOMER_NAME
     *
     * @return the value of customer.CUSTOMER_NAME
     *
     * @mbg.generated Thu Jun 28 16:17:11 CST 2018
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.CUSTOMER_NAME
     *
     * @param customerName the value for customer.CUSTOMER_NAME
     *
     * @mbg.generated Thu Jun 28 16:17:11 CST 2018
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.STATUS
     *
     * @return the value of customer.STATUS
     *
     * @mbg.generated Thu Jun 28 16:17:11 CST 2018
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.STATUS
     *
     * @param status the value for customer.STATUS
     *
     * @mbg.generated Thu Jun 28 16:17:11 CST 2018
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.CREATED_DATE
     *
     * @return the value of customer.CREATED_DATE
     *
     * @mbg.generated Thu Jun 28 16:17:11 CST 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.CREATED_DATE
     *
     * @param createdDate the value for customer.CREATED_DATE
     *
     * @mbg.generated Thu Jun 28 16:17:11 CST 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.CREATED_BY
     *
     * @return the value of customer.CREATED_BY
     *
     * @mbg.generated Thu Jun 28 16:17:11 CST 2018
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.CREATED_BY
     *
     * @param createdBy the value for customer.CREATED_BY
     *
     * @mbg.generated Thu Jun 28 16:17:11 CST 2018
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.LAST_UPDATED_DATE
     *
     * @return the value of customer.LAST_UPDATED_DATE
     *
     * @mbg.generated Thu Jun 28 16:17:11 CST 2018
     */
    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.LAST_UPDATED_DATE
     *
     * @param lastUpdatedDate the value for customer.LAST_UPDATED_DATE
     *
     * @mbg.generated Thu Jun 28 16:17:11 CST 2018
     */
    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.LAST_UPDATED_BY
     *
     * @return the value of customer.LAST_UPDATED_BY
     *
     * @mbg.generated Thu Jun 28 16:17:11 CST 2018
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.LAST_UPDATED_BY
     *
     * @param lastUpdatedBy the value for customer.LAST_UPDATED_BY
     *
     * @mbg.generated Thu Jun 28 16:17:11 CST 2018
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy == null ? null : lastUpdatedBy.trim();
    }
}