package mft.model.service;

import mft.model.entity.Stuff;
import mft.model.repository.StuffDa;

import java.util.List;

public class StuffService {

    private static StuffService service = new StuffService();

    private StuffService() {
    }

    public static StuffService getService() {
        return service;
    }

    public Stuff save(Stuff stuff) throws Exception {
        try (StuffDa stuffDa = new StuffDa()) {
            return stuffDa.save(stuff);
        }
    }

    public Stuff edit(Stuff stuff) throws Exception {
        try (StuffDa stuffDa = new StuffDa()) {
            return stuffDa.edit(stuff);
        }
    }

    public Stuff remove(int id) throws Exception {
        try (StuffDa stuffDa = new StuffDa()) {
            Stuff stuff = stuffDa.findById(id);
            if (stuff != null) {
                stuffDa.remove(id);
                return stuff;
            }
            else {
//                throw  new Exception("No Person");
                return null;
            }
        }
    }

    public List<Stuff> findAll() throws Exception {
        try (StuffDa stuffDa = new StuffDa()) {
            return stuffDa.findAll();
        }
    }

    public Stuff findById(int id) throws Exception {
        try (StuffDa stuffDa = new StuffDa()) {
            return stuffDa.findById(id);
        }
    }

    public List<Stuff> findByName(String name) throws Exception {
        try (StuffDa stuffDa = new StuffDa()) {
            return stuffDa.findByName(name);
        }
    }
}


