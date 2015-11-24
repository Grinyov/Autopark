package com.grinyov.dao;

import com.grinyov.entity.Bus;
import com.grinyov.entity.Driver;
import com.grinyov.entity.Route;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by green on 11/24/15.
 */
public interface BusDAO {
    public void addBus(Bus bus) throws SQLException;
    public void updateBus(Long bus_id, Bus bus) throws SQLException;
    public Bus getBusById(Long bus_id) throws SQLException;
    public Collection getAllBusses() throws SQLException;
    public void deleteBus(Bus bus) throws SQLException;
    public Collection getBussesByDriver(Driver driver) throws SQLException;
    public Collection getBussesByRoute(Route route) throws SQLException;
}
