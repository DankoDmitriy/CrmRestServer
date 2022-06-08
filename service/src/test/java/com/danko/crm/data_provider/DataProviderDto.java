package com.danko.crm.data_provider;

import com.danko.crm.service.dto.BaseDto;

public interface DataProviderDto<T extends BaseDto>{
    T getDtoWithOutId();

    T getDtoWithId();

    T getDtoWithIdNotActive();
}
