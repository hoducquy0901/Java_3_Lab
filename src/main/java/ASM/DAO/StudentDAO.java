/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASM.DAO;

import ASM.Service.JdbcHelper;
import ASM.Model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acer
 */
public class StudentDAO {

    private Connection connect = null;
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;

    public List<Student> Select() {

        String sql = "SELECT * FROM STUDENTS";

        List<Student> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql);
            while (rs.next()) {
                Student entity = new Student();
                entity.setMaSV(rs.getString(1));
                entity.setHoTen(rs.getString(2));
                entity.setEmail(rs.getString(3));
                entity.setSoDT(rs.getString(4));
                entity.setGioiTinh(rs.getBoolean(5));
                entity.setDiaChi(rs.getString(6));
                entity.setHinh(rs.getString(7));
                list.add(entity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public int insert(Object o) {
        String sql = "INSERT INTO [dbo].[STUDENTS]([MaSV],[HoTen],[Email],[SoDT],[GioiTinh],[DiaChi],[Hinh])\n"
                + "VALUES (?,?,?,?,?,?,?);";
        try {
            Student sv = (Student) o;
            connect = JdbcHelper.openDbConnection();
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, sv.getMaSV());
            preparedStatement.setString(2, sv.getHoTen());
            preparedStatement.setString(3, sv.getEmail());
            preparedStatement.setString(4, sv.getSoDT());
            preparedStatement.setBoolean(5, sv.getGioiTinh());
            preparedStatement.setString(6, sv.getDiaChi());
            preparedStatement.setString(7, sv.getHinh());

            preparedStatement.executeUpdate();

            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public int update(Object o) {
        String sql = "UPDATE [dbo].[STUDENTS]\n"
                + "   SET [HoTen] = ?,[Email] =? ,[SoDT] = ?,[GioiTinh] = ?,[DiaChi] = ?,[Hinh] = ?\n"
                + " WHERE [MaSV] = ?";
        try {
            Student sv = (Student) o;
            connect = JdbcHelper.openDbConnection();
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, sv.getHoTen());
            preparedStatement.setString(2, sv.getEmail());
            preparedStatement.setString(3, sv.getSoDT());
            preparedStatement.setBoolean(4, sv.getGioiTinh());
            preparedStatement.setString(5, sv.getDiaChi());
            preparedStatement.setString(6, sv.getHinh());
            preparedStatement.setString(7, sv.getMaSV());

            preparedStatement.executeUpdate();

            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public void delete(String MaSV){
          String sql="DELETE FROM Students WHERE MaSV=?";
          JdbcHelper.executeUpdate(sql, MaSV);
      }

    public boolean ktrMaSV(String maSV) {
        String sql = "SELECT * FROM Students WHERE MaSV = ?";
        try {
            connect = JdbcHelper.openDbConnection();
            preparedStatement = connect.prepareCall(sql);
            preparedStatement.setString(1, maSV);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    public Student findByMaSV(String MaSV) {
        String sql = "SELECT * FROM Students WHERE MaSV = ?";

        Student entity = new Student();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, MaSV);
            while (rs.next()) {
                entity.setMaSV(rs.getString(1));
                entity.setHoTen(rs.getString(2));
                entity.setEmail(rs.getString(3));
                entity.setSoDT(rs.getString(4));
                entity.setGioiTinh(rs.getBoolean(5));
                entity.setDiaChi(rs.getString(6));
                entity.setHinh(rs.getString(7));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }
}
