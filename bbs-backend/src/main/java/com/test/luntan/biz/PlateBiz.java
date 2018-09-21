package com.test.luntan.biz;

import com.test.luntan.model.Plate;
import com.test.luntan.repository.PlateRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PlateBiz {

    @Resource
    private PlateRepository plateRepository;

    public List<Plate> getPlateForIndex() {
        //拿到所有的分类,并根绝sort排序
        return plateRepository.findAll(Sort.by("sort"));
    }


}
