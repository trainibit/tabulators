package com.trainibit.tzdriver_tabulators.service;

import com.trainibit.tzdriver_tabulators.entity.Tabulator;
import com.trainibit.tzdriver_tabulators.request.TabulatorRequest;
import com.trainibit.tzdriver_tabulators.response.GeoPolygonResponse;
import com.trainibit.tzdriver_tabulators.response.TabulatorResponse;

import java.util.List;
import java.util.UUID;

public interface TabulatorService {

    TabulatorResponse saveTabulator(TabulatorRequest request);

    List<TabulatorResponse> getAllActiveTabulators();

    TabulatorResponse getTabulatorByUuid(UUID uuidTab);
}
