package io.github.asteria.service;

import io.github.asteria.entity.DictTypeDTO;
import java.util.List;

public interface DictTypeService {
    void save(DictTypeDTO dictTypeDTO);

    DictTypeDTO get(DictTypeDTO dictTypeDTO);

    List<DictTypeDTO> getList(DictTypeDTO dictTypeDTO);
}