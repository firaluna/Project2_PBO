/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import config.myConfig;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Produk.Produk;

public class DbController extends myConfig {
    public static List<Produk> getDatabase() {
        connection();
        
        ArrayList<Produk> produkList = new ArrayList<>();

        try {
            // query = "SELECT nama, harga, stok FROM tb_produk ORDER BY ID DESC";
            query = "SELECT nama, harga, jumlah FROM tb_produk";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                produkList.add(new Produk(resultSet.getString("nama"), resultSet.getInt("harga"), resultSet.getInt("jumlah"))); 
            }
            
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produkList;
    }
    public static void updateJumlah(int id, int stock) {
        connection();
        try {
            preparedStatement = connection.prepareStatement("UPDATE tb_product SET STOCK=? WHERE ID=?");
            preparedStatement.setInt(1, stock);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
        public static void updateHarga(int id, long harga) {
        connection();
        query = "UPDATE tb_produk SET harga=? WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, harga);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
        public static void updateNama(int id, String nama) {
        connection();
        query = "UPDATE tb_produk SET nama=? WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nama);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertData(String nama, long harga, int jumlah) {
        connection();
        query = "INSERT INTO tb_produk (nama, harga, jumlah) VALUES (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nama);
            preparedStatement.setLong(2, harga);
            preparedStatement.setInt(3, jumlah);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateProduct(String nama, int harga, int jumlah) {
        connection();
        query = "UPDATE tb_produk SET price=?, stock=? WHERE name=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(3, nama);
            preparedStatement.setInt(1, harga);
            preparedStatement.setInt(2, jumlah);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean deleteData(String nama) {
        connection();
        query = "DELETE FROM tb_produk WHERE nama=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nama);
            int affectedRowDelete = preparedStatement.executeUpdate();
            if (affectedRowDelete > 0) {
                return true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}