package com.app.wagon.util;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Component;

@Component
public class PageinationUtil {

    public PagedListHolder<?> getPageHolder(Optional<Integer> size, Optional<Integer> pagenum, List<?> list) {
        return getPageHolder(size, pagenum, Optional.ofNullable(null), list);

    }

    public <T> List<T> getList(PagedListHolder pg, Class T) {
        return (List<T>) pg.getPageList();
    }

    public PagedListHolder<?> getPageHolder(Optional<Integer> size, Optional<Integer> pagenum, Optional<String> byField,
            List<?> list) {
        // Pageable pageable = PageRequest.of(pagenum.orElse(0), size.orElse(8),
        // Sort.by(Direction.ASC, byField.orElse("name")));
        int realpage = getPageNum(pagenum, list.size(), size.orElse(8));

        PagedListHolder<?> ph = new PagedListHolder<>(list);
        ph.setPageSize(size.orElse(8));
        ph.setSort(new MutableSortDefinition(byField.orElse("price"), true, false));
        ph.setPage(realpage);

        return ph;
    }

    public Integer getPageNum(Optional<Integer> pagenum, int listSize, int pageSize) {

        Integer requestNum = pagenum.orElse(0);

        int total = (int) Math.ceil((listSize / (pageSize * 1.0)));

        if (requestNum <= 0)
            return 0;

        if (requestNum >= total)
            return total - 1;

        System.out.println("first after: " + (requestNum - 1));
        return requestNum - 1;
    }

}
