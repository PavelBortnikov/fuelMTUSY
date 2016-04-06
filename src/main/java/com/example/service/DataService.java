package com.example.service;

import java.util.Set;

/**
 * Created by pavelbortnikov on 06.04.16.
 */public interface DataService {

    public boolean persist(String problem);

    public Set<String> getRandomData();
}
