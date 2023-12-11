//package com.azship.apifrete.apirest.v1.mapper;
//
//import com.azship.apifrete.domain.entities.entitiesEnum.FreightTypeEnum;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class FreightStatusMapper {
//
//    private Map<FreightTypeEnumOutput, FreightTypeEnum> dtoMapEntity = new HashMap<>();
//
////    private Map<FreightTypeEnumInput, FreightTypeEnum> inputMapEntity = new HashMap<>();
//
//    private Map<FreightTypeEnum, FreightTypeEnumOutput> entityMapDto = new HashMap<>();
//
//    public Map<FreightTypeEnumOutput, FreightTypeEnum> mapDtoToEntity() {
//        dtoMapEntity.put(FreightTypeEnumOutput.GROUND, FreightTypeEnum.GROUND);
//        dtoMapEntity.put(FreightTypeEnumOutput.AIR, FreightTypeEnum.AIR);
//        dtoMapEntity.put(FreightTypeEnumOutput.MARITIME, FreightTypeEnum.MARITIME);
//
//        return dtoMapEntity;
//    }
//
//    public FreightTypeEnum dtoToEntityMapper(FreightTypeEnumOutput FreightTypeEnumOutput) {
//        FreightTypeEnum entity = mapDtoToEntity().get(FreightTypeEnumOutput);
//        if (entity == null) {
//            // return default status or exception
//        }
//        return entity;
//    }
//
////    public Map<FreightTypeEnumInput, FreightTypeEnum> mapInputToEntity() {
////        inputMapEntity.put(FreightTypeEnumInput.PF, FreightTypeEnum.FISICA);
////        inputMapEntity.put(FreightTypeEnumInput.PJ, FreightTypeEnum.JURIDICA);
////
////        return inputMapEntity;
////    }
////
////    public FreightTypeEnum inputToEntityMapper(FreightTypeEnumInput FreightTypeEnumInput) {
////        FreightTypeEnum entity = mapInputToEntity().get(FreightTypeEnumInput);
////        if (entity == null) {
////            // return default status or exception
////        }
////        return entity;
////    }
//
//    public Map<FreightTypeEnum, FreightTypeEnumOutput> mapToDto() {
//        entityMapDto.put(FreightTypeEnum.GROUND, FreightTypeEnumOutput.GROUND);
//        entityMapDto.put(FreightTypeEnum.AIR, FreightTypeEnumOutput.AIR);
//        entityMapDto.put(FreightTypeEnum.MARITIME, FreightTypeEnumOutput.MARITIME);
//
//        return entityMapDto;
//    }
//
//    public FreightTypeEnumOutput entityToDtoMapper(FreightTypeEnum FreightTypeEnum) {
//        FreightTypeEnumOutput dto = mapToDto().get(FreightTypeEnum);
//        if (dto == null) {
//            // return default status or exception
//        }
//        return dto;
//    }
//}
