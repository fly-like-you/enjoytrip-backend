package com.ssafy.api.sido.service;

import com.ssafy.api.sido.dao.SidoDao;
import com.ssafy.api.sido.dao.SidoDaoImpl;
import com.ssafy.api.sido.model.SidoDto;
import java.sql.SQLException;
import java.util.List;

public class SidoServiceImpl implements SidoService {

    private SidoDao sidoDao = SidoDaoImpl.getInstance();
    static private SidoService instance = new SidoServiceImpl();
    private SidoServiceImpl(){};

    public static SidoService getInstance() {
        return instance;
    }

    @Override
    public List<SidoDto> getAllSidos() throws SQLException {
        return sidoDao.getAllSidos();
    }
}
