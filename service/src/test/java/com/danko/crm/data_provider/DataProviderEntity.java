package com.danko.crm.data_provider;

import com.danko.crm.model.BaseEntity;

public interface DataProviderEntity<T extends BaseEntity> {
    T getEntityWithOutId();

    T getEntityWithId();

    T getEntityWithIdNotActive();
}
