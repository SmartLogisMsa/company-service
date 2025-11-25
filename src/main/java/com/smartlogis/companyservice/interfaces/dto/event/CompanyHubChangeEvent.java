package com.smartlogis.companyservice.interfaces.dto.event;

import java.util.UUID;

public record CompanyHubChangeEvent(
	UUID companyId,
	UUID newHubId
) {
}
