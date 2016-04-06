package com.example.repository;

import com.example.entity.DomainObject;

import java.util.Set;

/**
 * Created by pavelbortnikov on 06.04.16.
 */
public interface DataRepository<V extends DomainObject> {

    void persist(V object);

    void delete(V object);

    Set<String> getRandomData();

}
