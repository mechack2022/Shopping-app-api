package com.fragile.order_service.dto;

import com.fragile.order_service.model.OrderLineItem;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {

    List<OrderLineItemDto> orderLineItemDtoList;

}
