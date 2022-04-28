package com.vince.service.impl;

import com.vince.bean.Clothes;
import com.vince.service.ClothesService;
import com.vince.utils.BusinessException;
import com.vince.utils.ClothesIO;
import com.vince.utils.ProductsXmlUtils;

import java.util.List;

public class ClothesServiceImpl implements ClothesService {

    private ClothesIO clothesIO = new ClothesIO();

    @Override
    public List<Clothes> list() throws BusinessException {
        return clothesIO.list();
    }

    @Override
    public Clothes findClothesById(String clothesId) throws BusinessException {
        return clothesIO.findClothesById(clothesId);
    }

    public void update() throws BusinessException{
        clothesIO.update();
    }
}
