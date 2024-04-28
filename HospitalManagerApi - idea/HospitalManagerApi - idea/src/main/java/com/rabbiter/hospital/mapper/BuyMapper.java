package com.rabbiter.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rabbiter.hospital.pojo.Buy;


public interface BuyMapper extends BaseMapper<Buy> {

    void updateByIdTest(Buy buy);

}
