package com.smartlogis.companyservice.interfaces.dto.event;

import java.util.UUID;

public record StockReplenishedEvent(
	UUID productId,
	UUID companyId,
	int replenishedQuantity
) {
}
