package houseInventory;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;

public class FileDatabase implements Database{

    private final Gson gson;

    private final String filename;

    public FileDatabase(Gson gson, String filename) {
        this.gson = gson;
        this.filename = filename;
    }

    public FileDatabase(Gson gson) {
        this(gson,"database.txt");
    }

    @Override
    public void saveInventory(Inventory inventory) {
        try {
            try(PrintWriter writer = new PrintWriter(filename, "UTF-8")) {
                writer.println(gson.toJson(inventory));
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Inventory getInventory() {
        File f = new File(filename);
        if(f.exists() && !f.isDirectory()) {
            try {
                JsonReader reader = new JsonReader(new FileReader(filename));
                return gson.fromJson(reader,Inventory.class);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new Inventory();
    }
}
