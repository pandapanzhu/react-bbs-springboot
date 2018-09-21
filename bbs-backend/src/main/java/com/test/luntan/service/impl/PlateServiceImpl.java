package com.test.luntan.service.impl;

import com.test.luntan.biz.PlateBiz;
import com.test.luntan.model.Plate;
import com.test.luntan.service.PlateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PlateServiceImpl implements PlateService {

    @Resource
    private PlateBiz plateBiz;

    @Override
    public List<Plate> getAllPlate() {
        return plateBiz.getPlateForIndex();
    }
}
