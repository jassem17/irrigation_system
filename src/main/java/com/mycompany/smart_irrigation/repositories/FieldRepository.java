package com.mycompany.smart_irrigation.repositories;

import com.mycompany.smart_irrigation.entities.Field;
import com.mycompany.smart_irrigation.entities.Parcel;
import jakarta.nosql.mapping.Page;
import jakarta.nosql.mapping.Pagination;
import jakarta.nosql.mapping.Repository;

import java.util.List;
import java.util.stream.Stream;

public interface FieldRepository extends Repository<Field, String> {

    Stream<Field> findAll();

    Page<Field> findAll(Pagination pagination);

    Stream<Field> findByIdField(Long idField);

    Stream<Field> findByLocation(List<Float> location);
}
