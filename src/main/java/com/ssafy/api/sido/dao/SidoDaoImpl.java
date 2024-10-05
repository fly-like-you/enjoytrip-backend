package com.ssafy.api.sido.dao;

import com.ssafy.api.sido.model.SidoDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DBUtil;

public class SidoDaoImpl implements SidoDao {

    static private SidoDao instance = new SidoDaoImpl();
    private SidoDaoImpl() {}
    public static SidoDao getInstance() {
        return instance;
    }

    @Override
    public List<SidoDto> getAllSidos() throws SQLException {
        List<SidoDto> sidoList = new ArrayList<>();
        String query = "SELECT sido_code, sido_name FROM sidos";

        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SidoDto sido = new SidoDto();
                sido.setSidoCode(rs.getInt("sido_code"));
                sido.setSidoName(rs.getString("sido_name"));
                sidoList.add(sido);
            }
        }
        return sidoList;
    }
}
