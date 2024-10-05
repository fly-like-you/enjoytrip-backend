package com.ssafy.api.sido.dao;

import com.ssafy.api.sido.model.SidoDto;
import java.sql.SQLException;
import java.util.List;

public interface SidoDao {
    List<SidoDto> getAllSidos() throws SQLException;
}
