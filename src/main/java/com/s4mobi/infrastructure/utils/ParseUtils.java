package com.s4mobi.infrastructure.utils;

import com.s4mobi.infrastructure.model.ParsePageable;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;

public final class ParseUtils {

    private static final String FIELD_ID = "id";

    private ParseUtils() { }

    public static String parseSearch(final String search, final Object object) {
        String condition = Strings.EMPTY;

        final Field[] fields = ((Class<?>) object).getDeclaredFields();
        for(Field field : fields) {
            if (FIELD_ID.equalsIgnoreCase(field.getName())) {
                continue;
            }

            final String fieldCondition = String.format("{\"%s\":{\"$regex\":\"%s\"}}", field.getName(), search);
            if (condition.equals(Strings.EMPTY)) {
                condition = fieldCondition;
            } else {
                condition = String.join(",", condition, fieldCondition);
            }
        }

        return String.format("{\"$or\":[%s]}", condition);
    }

    public static ParsePageable parsePageable(final Pageable pageable) {
        final int page = pageable.getPageNumber();
        final int limit = pageable.getPageSize();
        final int skip = limit * page;

        String orders = Strings.EMPTY;
        for (Sort.Order order : pageable.getSort().toList()) {
            if (orders.equals(Strings.EMPTY)) {
                orders = order.getProperty();
            } else {
                orders = String.join(",", orders, order.getProperty());
            }
        }

        return ParsePageable.builder()
                .limit(limit)
                .skip(skip)
                .orders(orders)
                .build();
    }
}
