package com.example.demo.service;

import java.io.File;
import java.util.List;

public interface FilterService {
    boolean isValid(File file, List<String> logger);
}
