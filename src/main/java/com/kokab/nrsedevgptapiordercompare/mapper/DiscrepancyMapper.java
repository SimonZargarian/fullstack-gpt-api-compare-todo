package com.kokab.nrsedevgptapiordercompare.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.kokab.nrsedevgptapiordercompare.model.entity.Discrepancy;
import org.mapstruct.Mappings;
import com.kokab.nrsedevgptapiordercompare.model.dto.DiscrepancyDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiscrepancyMapper {
    @Mappings({
            @Mapping(target = "previousId", source = "id.previous"),
            @Mapping(target = "currentId", source = "id.current"),
            @Mapping(target = "previousUserId", source = "user_id.previous"),
            @Mapping(target = "currentUserId", source = "user_id.current"),
            @Mapping(target = "previousTitle", source = "title.previous"),
            @Mapping(target = "currentTitle", source = "title.current"),
            @Mapping(target = "previousIsCompleted", source = "is_completed.previous"),
            @Mapping(target = "currentIsCompleted", source = "is_completed.current")
    })
    Discrepancy discrepancyDTOToDiscrepancy(DiscrepancyDTO dto);

    List<Discrepancy> discrepancyDTOsToDiscrepancies(List<DiscrepancyDTO> dtos);
}
