package io.github.homeant.service;

import io.github.homeant.entity.DictTypeDTO;
import java.util.List;

public interface DictTypeService {
    void save(DictTypeDTO dictTypeDTO);

    DictTypeDTO get(DictTypeDTO dictTypeDTO);

    List<DictTypeDTO> getList(DictTypeDTO dictTypeDTO);
}