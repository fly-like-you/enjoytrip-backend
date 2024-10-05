package com.ssafy.api.sido.service;

import com.ssafy.api.sido.model.SidoDto;
import java.sql.SQLException;
import java.util.List;

public interface SidoService {
    List<SidoDto> getAllSidos() throws SQLException;
}
