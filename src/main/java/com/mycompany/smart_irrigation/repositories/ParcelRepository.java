package com.mycompany.smart_irrigation.repositories;

import com.mycompany.smart_irrigation.entities.Parcel;
import jakarta.nosql.mapping.Page;
import jakarta.nosql.mapping.Pagination;
import jakarta.nosql.mapping.Repository;

import java.util.List;
import java.util.stream.Stream;

public interface ParcelRepository extends Repository<Parcel, String> {

    Stream<Parcel> findAll();

    Page<Parcel> findAll(Pagination pagination);


    List<Parcel> findByIdField(String idField);

}
