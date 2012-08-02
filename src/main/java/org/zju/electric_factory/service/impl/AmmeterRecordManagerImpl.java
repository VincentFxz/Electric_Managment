package org.zju.electric_factory.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zju.electric_factory.dao.AmmeterRecordDAO;
import org.zju.electric_factory.entity.Ammeter;
import org.zju.electric_factory.entity.AmmeterRecord;
import org.zju.electric_factory.service.AmmeterRecordManager;

@Service
@Transactional
public class AmmeterRecordManagerImpl implements AmmeterRecordManager {

    @Autowired
    private AmmeterRecordDAO ammeterRecordDAO;

    public void add(AmmeterRecord ammeterRecord) {
        ammeterRecordDAO.add(ammeterRecord);
    }

    public List<AmmeterRecord> getAll() {
        return ammeterRecordDAO.getAll();
    }

    public AmmeterRecord get(int id) {
        return ammeterRecordDAO.get(id);
    }

    public List<AmmeterRecord> getByAmmeter(int ammeterId) {
        return ammeterRecordDAO.getByAmmeter(ammeterId);
    }

    @Override
    public List<AmmeterRecord> getAllAmmeterRecordAscById() {
        return ammeterRecordDAO.getByOrder("id", true);
    }

    @Override
    public List<AmmeterRecord> getAllAmmeterRecordDescById() {
        return ammeterRecordDAO.getByOrder("id", false);
    }

    @Override
    public List<AmmeterRecord> getAllAmmeterRecordAscByAmmeterName() {
        return ammeterRecordDAO.getByOrder("ammeterName", true);
    }

    @Override
    public List<AmmeterRecord> getAllAmmeterRecordDescByAmmeterName() {
        return ammeterRecordDAO.getByOrder("ammeterName", false);
    }

    @Override
    public List<AmmeterRecord> getAllAmmeterRecordAscByRecordDate() {
        return ammeterRecordDAO.getByOrder("recordDate", true);
    }

    @Override
    public List<AmmeterRecord> getAllAmmeterRecordDescByRecordDate() {
        return ammeterRecordDAO.getByOrder("recordDate", false);
    }

    @Override
    public List<AmmeterRecord> getAllAmmeterRecordAscByAmmeterValue() {
        return ammeterRecordDAO.getByOrder("ammeterValue", true);
    }

    @Override
    public List<AmmeterRecord> getAllAmmeterRecordDescByAmmeterValue() {
        return ammeterRecordDAO.getByOrder("ammeterValue", false);
    }

}
