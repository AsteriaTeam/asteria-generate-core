package io.github.homeant.service.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;

import io.github.homeant.domain.DictType;
import io.github.homeant.entity.DictTypeDTO;
import io.github.homeant.mapper.DictTypeDynamicSqlSupport;
import io.github.homeant.mapper.DictTypeMapper;
import io.github.homeant.service.DictTypeService;
import java.util.List;
import ma.glasnost.orika.MapperFacade;

public class DictTypeServiceImpl implements DictTypeService {
    private final DictTypeMapper dictTypeMapper;

    private final MapperFacade mapperFacade;

    public DictTypeServiceImpl(DictTypeMapper dictTypeMapper, MapperFacade mapperFacade) {
        this.dictTypeMapper = dictTypeMapper;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public void save(DictTypeDTO dictTypeDTO) {
        DictType domain = mapperFacade.map(dictTypeDTO,DictType.class);
        if(domain.getId()!=null){
            dictTypeMapper.updateByPrimaryKeySelective(domain);
        }else{
            dictTypeMapper.insertSelective(domain);
        }
    }

    @Override
    public DictTypeDTO get(DictTypeDTO dictTypeDTO) {
        return dictTypeMapper.selectOne(dsl->dsl.where()
        .and(DictTypeDynamicSqlSupport.id ,isEqualToWhenPresent(dictTypeDTO::getId))
        .and(DictTypeDynamicSqlSupport.dictName ,isEqualToWhenPresent(dictTypeDTO::getDictName))
        .and(DictTypeDynamicSqlSupport.dictCode ,isEqualToWhenPresent(dictTypeDTO::getDictCode))
        .and(DictTypeDynamicSqlSupport.status ,isEqualToWhenPresent(dictTypeDTO::getStatus))
        .and(DictTypeDynamicSqlSupport.isValid ,isEqualToWhenPresent(dictTypeDTO::getIsValid))
        .and(DictTypeDynamicSqlSupport.createdAt ,isEqualToWhenPresent(dictTypeDTO::getCreatedAt))
        .and(DictTypeDynamicSqlSupport.updatedAt ,isEqualToWhenPresent(dictTypeDTO::getUpdatedAt))
        .and(DictTypeDynamicSqlSupport.deletedAt ,isEqualToWhenPresent(dictTypeDTO::getDeletedAt))
        ).map(r->mapperFacade.map(r,DictTypeDTO.class)).orElse(null);
    }

    @Override
    public List<DictTypeDTO> getList(DictTypeDTO dictTypeDTO) {
        return mapperFacade.mapAsList(dictTypeMapper.select(dsl->dsl.where()
        .and(DictTypeDynamicSqlSupport.id ,isEqualToWhenPresent(dictTypeDTO::getId))
        .and(DictTypeDynamicSqlSupport.dictName ,isEqualToWhenPresent(dictTypeDTO::getDictName))
        .and(DictTypeDynamicSqlSupport.dictCode ,isEqualToWhenPresent(dictTypeDTO::getDictCode))
        .and(DictTypeDynamicSqlSupport.status ,isEqualToWhenPresent(dictTypeDTO::getStatus))
        .and(DictTypeDynamicSqlSupport.isValid ,isEqualToWhenPresent(dictTypeDTO::getIsValid))
        .and(DictTypeDynamicSqlSupport.createdAt ,isEqualToWhenPresent(dictTypeDTO::getCreatedAt))
        .and(DictTypeDynamicSqlSupport.updatedAt ,isEqualToWhenPresent(dictTypeDTO::getUpdatedAt))
        .and(DictTypeDynamicSqlSupport.deletedAt ,isEqualToWhenPresent(dictTypeDTO::getDeletedAt))
        ),DictTypeDTO.class);
    }
}