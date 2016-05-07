package com.example.repository;

import com.example.entity.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.Types;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pavelbortnikov on 06.04.16.
 */

@org.springframework.stereotype.Repository("dataRespitory")
public class DataRepositoryImpl implements DataRepository<Data> {

    @Autowired
    protected JdbcOperations jdbcOperations;

    @Override
    public void persist(Data object) {

        Object[] params = new Object[] { object.getId(), object.getDescription() };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };

        jdbcOperations.update("INSERT INTO yourapp_data(\n" +
                "            data_id, data_description)\n" +
                "    VALUES (cast(? as UUID), ?);", params, types);
    }

    @Override
    public void delete(Data object) {
        jdbcOperations.update("DELETE FROM yourapp_data\n" +
                " WHERE data_id = '" + object.getId().toString() + "';");
    }

    @Override
    public Set<String> getRandomData() {
        Set<String> result = new HashSet<>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet("SELECT data_description FROM yourapp_data p ORDER BY RANDOM() LIMIT 50;");
        while (rowSet.next()) {
            result.add(rowSet.getString("data_description"));
        }
        return result;
    }

    @Override
    public Set<String> getUser(String login) {
        Set<String> result = new HashSet<>();
        Object[] params = new Object[] { login };
        int[] types = new int[] { Types.VARCHAR};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet("SELECT * FROM accounts WHERE login_account = ?;", params, types);
        while (rowSet.next()) {
            result.add(rowSet.getString("login_account"));
        }
        return result;
    }


}
