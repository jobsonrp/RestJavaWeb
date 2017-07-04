/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jobson
 */
@Path("testcontroller")
public class testController {

    @GET
    @Path("/getData")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<testModel> getDataInJSON() throws ClassNotFoundException, SQLException {
        ArrayList<testModel> tmm = new ArrayList<>();
        Connection con = null;
        String username = "root";
        String password = "@123456@";
        String query = "select * from test.tabela1";
        Class.forName("com.mysql.jdbc.Driver"); // com.mysql.jdbc.Driver org.apache.derby.jdbc
        con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql?zeroDateTimeBehavior=convertToNull", username, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            testModel tm = new testModel();
            tm.setId(rs.getInt("id"));
            tm.setName(rs.getString("name"));
            tm.setEmail(rs.getString("email"));
            tmm.add(tm);
        }

        return tmm;
    }

}
