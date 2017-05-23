package model;

import data.Connection;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by feba6481 on 23.05.17.
 */
public class CurrConnections extends AbstractListModel<Connection> {

    private List<Connection> connectionList = new ArrayList<>();

    public void add(Connection c){
        connectionList.add(c);
    }

    @Override
    public int getSize() {
        return connectionList.size();
    }

    @Override
    public Connection getElementAt(int index) {
        return connectionList.get(index);
    }

    public boolean contains(String authLine){
        for(Connection c : connectionList){
            if(c.toString().equals(authLine))
                return false;
        }
        return true;
    }

    public Connection getConnectionWith(String authLine)throws Exception{
        for(Connection c : connectionList){
            if(c.toString().equals(authLine))
                return c;
        }
        throw new Exception("no match found. adding...");
    }
}
