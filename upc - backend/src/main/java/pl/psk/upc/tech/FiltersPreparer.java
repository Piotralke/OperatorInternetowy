package pl.psk.upc.tech;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FiltersPreparer {
    public static Pageable preparePageable(int pageNo, int pageSize, List<String> sort) {
        if (sort == null) {
            return PageRequest.of(pageNo, pageSize);
        }
        return PageRequest.of(pageNo, pageSize, Sort.by(transformSortParams(sort)));

    }

    private static List<Sort.Order> transformSortParams(List<String> sort) {
        List<Sort.Order> sortDetails = new ArrayList<>();
        if (sort != null || !sort.isEmpty()) {
            //sample data for the first item in the list:
            //String[0] - startDate
            //String[1] - ASC
            List<String[]> sortParamsArr = sort.stream()
                    .map(s -> s.split(":"))
                    .toList();
            for (String[] arr : sortParamsArr) {
                Sort.Direction sortOrder = getSortOrder(arr[1]);
                sortDetails.add(new Sort.Order(sortOrder, arr[0]));
            }
        }
        return sortDetails;
    }

    private static Sort.Direction getSortOrder(String sortOrder) {
        return sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    }
}
