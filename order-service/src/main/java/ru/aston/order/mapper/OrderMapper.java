package ru.aston.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aston.order.domain.Order;
import ru.aston.order.dto.OrderRequest;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "name", target = "productName")
    @Mapping(target = "customer", ignore = true)
    Order toOrder(OrderRequest req);
}
