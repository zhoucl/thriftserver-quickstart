package com.eboji.pojo;

import java.util.ArrayList;
import java.util.List;

public class HomeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HomeExample() {
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

        public Criteria andHomelinkIsNull() {
            addCriterion("homelink is null");
            return (Criteria) this;
        }

        public Criteria andHomelinkIsNotNull() {
            addCriterion("homelink is not null");
            return (Criteria) this;
        }

        public Criteria andHomelinkEqualTo(String value) {
            addCriterion("homelink =", value, "homelink");
            return (Criteria) this;
        }

        public Criteria andHomelinkNotEqualTo(String value) {
            addCriterion("homelink <>", value, "homelink");
            return (Criteria) this;
        }

        public Criteria andHomelinkGreaterThan(String value) {
            addCriterion("homelink >", value, "homelink");
            return (Criteria) this;
        }

        public Criteria andHomelinkGreaterThanOrEqualTo(String value) {
            addCriterion("homelink >=", value, "homelink");
            return (Criteria) this;
        }

        public Criteria andHomelinkLessThan(String value) {
            addCriterion("homelink <", value, "homelink");
            return (Criteria) this;
        }

        public Criteria andHomelinkLessThanOrEqualTo(String value) {
            addCriterion("homelink <=", value, "homelink");
            return (Criteria) this;
        }

        public Criteria andHomelinkLike(String value) {
            addCriterion("homelink like", value, "homelink");
            return (Criteria) this;
        }

        public Criteria andHomelinkNotLike(String value) {
            addCriterion("homelink not like", value, "homelink");
            return (Criteria) this;
        }

        public Criteria andHomelinkIn(List<String> values) {
            addCriterion("homelink in", values, "homelink");
            return (Criteria) this;
        }

        public Criteria andHomelinkNotIn(List<String> values) {
            addCriterion("homelink not in", values, "homelink");
            return (Criteria) this;
        }

        public Criteria andHomelinkBetween(String value1, String value2) {
            addCriterion("homelink between", value1, value2, "homelink");
            return (Criteria) this;
        }

        public Criteria andHomelinkNotBetween(String value1, String value2) {
            addCriterion("homelink not between", value1, value2, "homelink");
            return (Criteria) this;
        }

        public Criteria andHomedescIsNull() {
            addCriterion("homedesc is null");
            return (Criteria) this;
        }

        public Criteria andHomedescIsNotNull() {
            addCriterion("homedesc is not null");
            return (Criteria) this;
        }

        public Criteria andHomedescEqualTo(String value) {
            addCriterion("homedesc =", value, "homedesc");
            return (Criteria) this;
        }

        public Criteria andHomedescNotEqualTo(String value) {
            addCriterion("homedesc <>", value, "homedesc");
            return (Criteria) this;
        }

        public Criteria andHomedescGreaterThan(String value) {
            addCriterion("homedesc >", value, "homedesc");
            return (Criteria) this;
        }

        public Criteria andHomedescGreaterThanOrEqualTo(String value) {
            addCriterion("homedesc >=", value, "homedesc");
            return (Criteria) this;
        }

        public Criteria andHomedescLessThan(String value) {
            addCriterion("homedesc <", value, "homedesc");
            return (Criteria) this;
        }

        public Criteria andHomedescLessThanOrEqualTo(String value) {
            addCriterion("homedesc <=", value, "homedesc");
            return (Criteria) this;
        }

        public Criteria andHomedescLike(String value) {
            addCriterion("homedesc like", value, "homedesc");
            return (Criteria) this;
        }

        public Criteria andHomedescNotLike(String value) {
            addCriterion("homedesc not like", value, "homedesc");
            return (Criteria) this;
        }

        public Criteria andHomedescIn(List<String> values) {
            addCriterion("homedesc in", values, "homedesc");
            return (Criteria) this;
        }

        public Criteria andHomedescNotIn(List<String> values) {
            addCriterion("homedesc not in", values, "homedesc");
            return (Criteria) this;
        }

        public Criteria andHomedescBetween(String value1, String value2) {
            addCriterion("homedesc between", value1, value2, "homedesc");
            return (Criteria) this;
        }

        public Criteria andHomedescNotBetween(String value1, String value2) {
            addCriterion("homedesc not between", value1, value2, "homedesc");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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