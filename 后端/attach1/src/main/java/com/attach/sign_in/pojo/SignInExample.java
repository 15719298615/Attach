package com.attach.sign_in.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SignInExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SignInExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSignInIdIsNull() {
            addCriterion("sign_in_id is null");
            return (Criteria) this;
        }

        public Criteria andSignInIdIsNotNull() {
            addCriterion("sign_in_id is not null");
            return (Criteria) this;
        }

        public Criteria andSignInIdEqualTo(Integer value) {
            addCriterion("sign_in_id =", value, "signInId");
            return (Criteria) this;
        }

        public Criteria andSignInIdNotEqualTo(Integer value) {
            addCriterion("sign_in_id <>", value, "signInId");
            return (Criteria) this;
        }

        public Criteria andSignInIdGreaterThan(Integer value) {
            addCriterion("sign_in_id >", value, "signInId");
            return (Criteria) this;
        }

        public Criteria andSignInIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sign_in_id >=", value, "signInId");
            return (Criteria) this;
        }

        public Criteria andSignInIdLessThan(Integer value) {
            addCriterion("sign_in_id <", value, "signInId");
            return (Criteria) this;
        }

        public Criteria andSignInIdLessThanOrEqualTo(Integer value) {
            addCriterion("sign_in_id <=", value, "signInId");
            return (Criteria) this;
        }

        public Criteria andSignInIdIn(List<Integer> values) {
            addCriterion("sign_in_id in", values, "signInId");
            return (Criteria) this;
        }

        public Criteria andSignInIdNotIn(List<Integer> values) {
            addCriterion("sign_in_id not in", values, "signInId");
            return (Criteria) this;
        }

        public Criteria andSignInIdBetween(Integer value1, Integer value2) {
            addCriterion("sign_in_id between", value1, value2, "signInId");
            return (Criteria) this;
        }

        public Criteria andSignInIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sign_in_id not between", value1, value2, "signInId");
            return (Criteria) this;
        }

        public Criteria andSignInPasswordIsNull() {
            addCriterion("sign_in_password is null");
            return (Criteria) this;
        }

        public Criteria andSignInPasswordIsNotNull() {
            addCriterion("sign_in_password is not null");
            return (Criteria) this;
        }

        public Criteria andSignInPasswordEqualTo(String value) {
            addCriterion("sign_in_password =", value, "signInPassword");
            return (Criteria) this;
        }

        public Criteria andSignInPasswordNotEqualTo(String value) {
            addCriterion("sign_in_password <>", value, "signInPassword");
            return (Criteria) this;
        }

        public Criteria andSignInPasswordGreaterThan(String value) {
            addCriterion("sign_in_password >", value, "signInPassword");
            return (Criteria) this;
        }

        public Criteria andSignInPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("sign_in_password >=", value, "signInPassword");
            return (Criteria) this;
        }

        public Criteria andSignInPasswordLessThan(String value) {
            addCriterion("sign_in_password <", value, "signInPassword");
            return (Criteria) this;
        }

        public Criteria andSignInPasswordLessThanOrEqualTo(String value) {
            addCriterion("sign_in_password <=", value, "signInPassword");
            return (Criteria) this;
        }

        public Criteria andSignInPasswordLike(String value) {
            addCriterion("sign_in_password like", value, "signInPassword");
            return (Criteria) this;
        }

        public Criteria andSignInPasswordNotLike(String value) {
            addCriterion("sign_in_password not like", value, "signInPassword");
            return (Criteria) this;
        }

        public Criteria andSignInPasswordIn(List<String> values) {
            addCriterion("sign_in_password in", values, "signInPassword");
            return (Criteria) this;
        }

        public Criteria andSignInPasswordNotIn(List<String> values) {
            addCriterion("sign_in_password not in", values, "signInPassword");
            return (Criteria) this;
        }

        public Criteria andSignInPasswordBetween(String value1, String value2) {
            addCriterion("sign_in_password between", value1, value2, "signInPassword");
            return (Criteria) this;
        }

        public Criteria andSignInPasswordNotBetween(String value1, String value2) {
            addCriterion("sign_in_password not between", value1, value2, "signInPassword");
            return (Criteria) this;
        }

        public Criteria andSignInNameIsNull() {
            addCriterion("sign_in_name is null");
            return (Criteria) this;
        }

        public Criteria andSignInNameIsNotNull() {
            addCriterion("sign_in_name is not null");
            return (Criteria) this;
        }

        public Criteria andSignInNameEqualTo(String value) {
            addCriterion("sign_in_name =", value, "signInName");
            return (Criteria) this;
        }

        public Criteria andSignInNameNotEqualTo(String value) {
            addCriterion("sign_in_name <>", value, "signInName");
            return (Criteria) this;
        }

        public Criteria andSignInNameGreaterThan(String value) {
            addCriterion("sign_in_name >", value, "signInName");
            return (Criteria) this;
        }

        public Criteria andSignInNameGreaterThanOrEqualTo(String value) {
            addCriterion("sign_in_name >=", value, "signInName");
            return (Criteria) this;
        }

        public Criteria andSignInNameLessThan(String value) {
            addCriterion("sign_in_name <", value, "signInName");
            return (Criteria) this;
        }

        public Criteria andSignInNameLessThanOrEqualTo(String value) {
            addCriterion("sign_in_name <=", value, "signInName");
            return (Criteria) this;
        }

        public Criteria andSignInNameLike(String value) {
            addCriterion("sign_in_name like", value, "signInName");
            return (Criteria) this;
        }

        public Criteria andSignInNameNotLike(String value) {
            addCriterion("sign_in_name not like", value, "signInName");
            return (Criteria) this;
        }

        public Criteria andSignInNameIn(List<String> values) {
            addCriterion("sign_in_name in", values, "signInName");
            return (Criteria) this;
        }

        public Criteria andSignInNameNotIn(List<String> values) {
            addCriterion("sign_in_name not in", values, "signInName");
            return (Criteria) this;
        }

        public Criteria andSignInNameBetween(String value1, String value2) {
            addCriterion("sign_in_name between", value1, value2, "signInName");
            return (Criteria) this;
        }

        public Criteria andSignInNameNotBetween(String value1, String value2) {
            addCriterion("sign_in_name not between", value1, value2, "signInName");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterionForJDBCDate("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterionForJDBCDate("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterionForJDBCDate("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterionForJDBCDate("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterionForJDBCDate("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterionForJDBCDate("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andSiteLoIsNull() {
            addCriterion("site_lo is null");
            return (Criteria) this;
        }

        public Criteria andSiteLoIsNotNull() {
            addCriterion("site_lo is not null");
            return (Criteria) this;
        }

        public Criteria andSiteLoEqualTo(Double value) {
            addCriterion("site_lo =", value, "siteLo");
            return (Criteria) this;
        }

        public Criteria andSiteLoNotEqualTo(Double value) {
            addCriterion("site_lo <>", value, "siteLo");
            return (Criteria) this;
        }

        public Criteria andSiteLoGreaterThan(Double value) {
            addCriterion("site_lo >", value, "siteLo");
            return (Criteria) this;
        }

        public Criteria andSiteLoGreaterThanOrEqualTo(Double value) {
            addCriterion("site_lo >=", value, "siteLo");
            return (Criteria) this;
        }

        public Criteria andSiteLoLessThan(Double value) {
            addCriterion("site_lo <", value, "siteLo");
            return (Criteria) this;
        }

        public Criteria andSiteLoLessThanOrEqualTo(Double value) {
            addCriterion("site_lo <=", value, "siteLo");
            return (Criteria) this;
        }

        public Criteria andSiteLoIn(List<Double> values) {
            addCriterion("site_lo in", values, "siteLo");
            return (Criteria) this;
        }

        public Criteria andSiteLoNotIn(List<Double> values) {
            addCriterion("site_lo not in", values, "siteLo");
            return (Criteria) this;
        }

        public Criteria andSiteLoBetween(Double value1, Double value2) {
            addCriterion("site_lo between", value1, value2, "siteLo");
            return (Criteria) this;
        }

        public Criteria andSiteLoNotBetween(Double value1, Double value2) {
            addCriterion("site_lo not between", value1, value2, "siteLo");
            return (Criteria) this;
        }

        public Criteria andSiteLaIsNull() {
            addCriterion("site_la is null");
            return (Criteria) this;
        }

        public Criteria andSiteLaIsNotNull() {
            addCriterion("site_la is not null");
            return (Criteria) this;
        }

        public Criteria andSiteLaEqualTo(Double value) {
            addCriterion("site_la =", value, "siteLa");
            return (Criteria) this;
        }

        public Criteria andSiteLaNotEqualTo(Double value) {
            addCriterion("site_la <>", value, "siteLa");
            return (Criteria) this;
        }

        public Criteria andSiteLaGreaterThan(Double value) {
            addCriterion("site_la >", value, "siteLa");
            return (Criteria) this;
        }

        public Criteria andSiteLaGreaterThanOrEqualTo(Double value) {
            addCriterion("site_la >=", value, "siteLa");
            return (Criteria) this;
        }

        public Criteria andSiteLaLessThan(Double value) {
            addCriterion("site_la <", value, "siteLa");
            return (Criteria) this;
        }

        public Criteria andSiteLaLessThanOrEqualTo(Double value) {
            addCriterion("site_la <=", value, "siteLa");
            return (Criteria) this;
        }

        public Criteria andSiteLaIn(List<Double> values) {
            addCriterion("site_la in", values, "siteLa");
            return (Criteria) this;
        }

        public Criteria andSiteLaNotIn(List<Double> values) {
            addCriterion("site_la not in", values, "siteLa");
            return (Criteria) this;
        }

        public Criteria andSiteLaBetween(Double value1, Double value2) {
            addCriterion("site_la between", value1, value2, "siteLa");
            return (Criteria) this;
        }

        public Criteria andSiteLaNotBetween(Double value1, Double value2) {
            addCriterion("site_la not between", value1, value2, "siteLa");
            return (Criteria) this;
        }

        public Criteria andMaxNumberIsNull() {
            addCriterion("max_number is null");
            return (Criteria) this;
        }

        public Criteria andMaxNumberIsNotNull() {
            addCriterion("max_number is not null");
            return (Criteria) this;
        }

        public Criteria andMaxNumberEqualTo(Integer value) {
            addCriterion("max_number =", value, "maxNumber");
            return (Criteria) this;
        }

        public Criteria andMaxNumberNotEqualTo(Integer value) {
            addCriterion("max_number <>", value, "maxNumber");
            return (Criteria) this;
        }

        public Criteria andMaxNumberGreaterThan(Integer value) {
            addCriterion("max_number >", value, "maxNumber");
            return (Criteria) this;
        }

        public Criteria andMaxNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_number >=", value, "maxNumber");
            return (Criteria) this;
        }

        public Criteria andMaxNumberLessThan(Integer value) {
            addCriterion("max_number <", value, "maxNumber");
            return (Criteria) this;
        }

        public Criteria andMaxNumberLessThanOrEqualTo(Integer value) {
            addCriterion("max_number <=", value, "maxNumber");
            return (Criteria) this;
        }

        public Criteria andMaxNumberIn(List<Integer> values) {
            addCriterion("max_number in", values, "maxNumber");
            return (Criteria) this;
        }

        public Criteria andMaxNumberNotIn(List<Integer> values) {
            addCriterion("max_number not in", values, "maxNumber");
            return (Criteria) this;
        }

        public Criteria andMaxNumberBetween(Integer value1, Integer value2) {
            addCriterion("max_number between", value1, value2, "maxNumber");
            return (Criteria) this;
        }

        public Criteria andMaxNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("max_number not between", value1, value2, "maxNumber");
            return (Criteria) this;
        }

        public Criteria andEffectiveIsNull() {
            addCriterion("effective is null");
            return (Criteria) this;
        }

        public Criteria andEffectiveIsNotNull() {
            addCriterion("effective is not null");
            return (Criteria) this;
        }

        public Criteria andEffectiveEqualTo(Byte value) {
            addCriterion("effective =", value, "effective");
            return (Criteria) this;
        }

        public Criteria andEffectiveNotEqualTo(Byte value) {
            addCriterion("effective <>", value, "effective");
            return (Criteria) this;
        }

        public Criteria andEffectiveGreaterThan(Byte value) {
            addCriterion("effective >", value, "effective");
            return (Criteria) this;
        }

        public Criteria andEffectiveGreaterThanOrEqualTo(Byte value) {
            addCriterion("effective >=", value, "effective");
            return (Criteria) this;
        }

        public Criteria andEffectiveLessThan(Byte value) {
            addCriterion("effective <", value, "effective");
            return (Criteria) this;
        }

        public Criteria andEffectiveLessThanOrEqualTo(Byte value) {
            addCriterion("effective <=", value, "effective");
            return (Criteria) this;
        }

        public Criteria andEffectiveIn(List<Byte> values) {
            addCriterion("effective in", values, "effective");
            return (Criteria) this;
        }

        public Criteria andEffectiveNotIn(List<Byte> values) {
            addCriterion("effective not in", values, "effective");
            return (Criteria) this;
        }

        public Criteria andEffectiveBetween(Byte value1, Byte value2) {
            addCriterion("effective between", value1, value2, "effective");
            return (Criteria) this;
        }

        public Criteria andEffectiveNotBetween(Byte value1, Byte value2) {
            addCriterion("effective not between", value1, value2, "effective");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}