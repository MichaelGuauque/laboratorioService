package com.laboratorio.dto;

public record DespachoProductoDTO(
        String external_order_id,
        String product_name,
        Integer quantity
) {
}
