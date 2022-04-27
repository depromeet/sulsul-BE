package com.depromeet.sulsul.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaginationUtil {

    public static int PAGINATION_SIZE = 20;

    public static boolean isOverPaginationSize(List contentsWithPageable) {
        return contentsWithPageable.size() == PaginationUtil.PAGINATION_SIZE + 1;
    }
}
