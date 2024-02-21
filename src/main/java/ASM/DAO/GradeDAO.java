/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASM.DAO;

import ASM.Model.Grade;
import ASM.Service.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acer
 */
public class GradeDAO {
    public static void insert(Grade model) {
        String sql = "INSERT INTO Grade (ID, MaSV,  TiengAnh, TinHoc,  GDTC) VALUES (?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getId(),
                model.getMaSV(),
                model.getTiengAnh(),
                model.getTinHoc(),
                model.getGDTC()
        );
    }

    public void update(Grade model) {
        String sql = "UPDATE Grade SET TiengAnh=?, TinHoc=?, GDTC=? WHERE ID=?";

        JdbcHelper.executeUpdate(sql,
                model.getTiengAnh(),
                model.getTinHoc(),
                model.getGDTC(),
                model.getId()
        );
    }

    public void delete(String id) {
        String sql = "DELETE FROM Grade WHERE ID=?";
        JdbcHelper.executeUpdate(sql, id);
    }

    public List<Grade> select() {
        String sql = "SELECT * FROM Grade";
        return select(sql);
    }

    public List<Grade> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM Grade WHERE HoTen LIKE ?";
        return select(sql, "%" + keyword + "%");
    }

    public List<Grade> selectByCourse(Integer makh) {
        String sql = "SELECT * FROM Grade WHERE ID NOT IN (SELECT ID FROM HocVien WHERE ID=?)";
        return select(sql, makh);
    }

    public Grade findById(String manh) {
        String sql = "SELECT * FROM Grade WHERE ID=?";
        List<Grade> list = select(sql, manh);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<Grade> select(String sql, Object... args) {
        List<Grade> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    Grade model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private Grade readFromResultSet(ResultSet rs) throws SQLException {
        Grade model = new Grade();
        model.setId(rs.getInt("ID"));
        model.setMaSV(rs.getString("MaSV"));
        model.setTiengAnh(rs.getInt("TiengAnh"));
        model.setTinHoc(rs.getInt("TinHoc"));
        model.setGDTC(rs.getInt("GDTC"));

        return model;
    }
}
