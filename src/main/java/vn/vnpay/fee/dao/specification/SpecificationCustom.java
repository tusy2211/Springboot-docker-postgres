package vn.vnpay.fee.dao.specification;

import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;
import vn.vnpay.fee.beans.FeeBean_;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Selection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SpecificationCustom {

    public static <T> Specification<T> hasValue(String key, String value) {
        return (root, query, cb) -> Strings.isBlank(value) ? cb.conjunction() : cb.equal(root.get(key), value);
    }

    public static <T> Specification<T> hasValue(String key, Integer value) {
        return (root, query, cb) -> value == null || value == 0 ? cb.conjunction() : cb.equal(root.get(key), value);
    }

    public static <T> Specification<T> hasValue(String key, Long value) {
        return (root, query, cb) -> value == null || value == 0 ? cb.conjunction() : cb.equal(root.get(key), value);
    }

    public static <T> Specification<T> hasLike(String key, String value) {
        return (root, query, cb) -> Strings.isBlank(value) ? cb.conjunction() : cb.like(root.get(key),
                "%" + value + "%");
    }

    public static <T> Specification<T> hasValueIn(String key, Collection value) {
        return (root, query, cb) -> root.get(key).in(value);
    }

    public static <T, V> Specification<V> hasJoin(String key1Join, JoinType joinType, String key2Join, String value) {
        return (root, query, cb) -> {
            if (Strings.isBlank(value)) {
                return cb.conjunction();
            }
            final Join<T, V> join = root.join(key1Join, joinType);
            return cb.equal(join.get(key2Join), value);
        };
    }

    public static <T, V> Specification<V> hasJoin(String key1Join, JoinType joinType, String key2Join, Long value) {
        return (root, query, cb) -> {
            if (null == value || value == 0) {
                return cb.conjunction();
            }
            final Join<T, V> join = root.join(key1Join, joinType);
            return cb.equal(join.get(key2Join), value);
        };
    }

    public static <T> Specification<T> greaterThan(String key, Timestamp fromDate) {
        return (root, query, cb) -> (fromDate == null) ? cb.conjunction() : cb.greaterThan(root.get(key), fromDate);
    }

    public static <T> Specification<T> lessThan(String key, Timestamp toDate) {
        return (root, query, cb) -> (toDate == null) ? cb.conjunction() : cb.lessThan(root.get(key), toDate);
    }

    public static <T> Specification<T> greaterThanOrEqualTo(String key, Timestamp fromDate) {
        return (root, query, cb) -> fromDate == null ? cb.conjunction() : cb.greaterThanOrEqualTo(root.get(key),
                fromDate);
    }

    public static <T> Specification<T> lessThanOrEqualTo(String key, Timestamp toDate) {
        return (root, query, cb) -> toDate == null ? cb.conjunction() : cb.lessThanOrEqualTo(root.get(key), toDate);
    }

    public static <T> Specification<T> between(String key, Timestamp fromDate, Timestamp toDate) {
        return (root, query, cb) -> (fromDate == null || toDate == null) ? cb.conjunction() : cb.between(root.get(key),
                fromDate, toDate);
    }

    public static <T> Specification<T> bySearchFilter(String[] properties, String key, Long value) {
        return (root, query, cb) -> {
            query = cb.createTupleQuery();
            List<Selection<? extends Object>> selectionList = new ArrayList<Selection<? extends Object>>();
            for (String property : properties) {

                Selection<? extends Object> selection = root.get(property);

                selectionList.add(selection);
            }
            return value == null || value == 0 ? query.multiselect(selectionList).getGroupRestriction() :
                    query.multiselect(selectionList).where(cb.equal(root.get(key), value)).getRestriction();
        };
    }
}
