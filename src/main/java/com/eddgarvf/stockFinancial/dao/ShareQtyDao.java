package com.eddgarvf.stockFinancial.dao;

import com.eddgarvf.stockFinancial.model.ShareQty;

public interface ShareQtyDao {

    ShareQty getById(int userId);
    ShareQty getShares(int userId, int stockId);
    void add(ShareQty shareQty);
    void update(ShareQty shareQty);
}
