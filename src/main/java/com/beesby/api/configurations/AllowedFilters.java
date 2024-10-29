package com.beesby.api.configurations;

import com.beesby.api.utils.AllowedFilter;
import com.beesby.api.utils.DynamicFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AllowedFilters {

    public static void addFiltersWithPrefix(String prefix, List<AllowedFilter> actualFilters, List<AllowedFilter> newFilters) {
        for (AllowedFilter filter : newFilters) {
            String newKey = "%s.%s".formatted(prefix, filter.getKey());
            filter.setKey(newKey);
            actualFilters.add(filter);
        }
    }

    public static List<AllowedFilter> user() {
        List<AllowedFilter> allowedFilters = new ArrayList<>();
        DynamicFilter.addIdFilters(allowedFilters, false, "id");
        DynamicFilter.addIdFilters(allowedFilters, true, "createdBy", "updatedBy", "deletedBy");
        DynamicFilter.addStringFilters(allowedFilters, false, "cpf", "name", "status");
        DynamicFilter.addDateFilters(allowedFilters, false, "createdAt", "updatedAt", "birthDate");
        DynamicFilter.addDateFilters(allowedFilters, true, "deletedAt");
        addFiltersWithPrefix("address", allowedFilters, address());
        return allowedFilters;
    }

    public static List<AllowedFilter> address() {
        List<AllowedFilter> allowedFilters = new ArrayList<>();
        DynamicFilter.addStringFilters(allowedFilters, true, "street", "number", "complement", "neighborhood", "city", "state", "zipCode");
        return allowedFilters;
    }
}
