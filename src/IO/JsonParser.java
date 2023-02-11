package IO;

import ObjetosJuego.Constantes;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JsonParser {

    

    public static ArrayList<PuntuacionFecha> readFile() throws FileNotFoundException {

        ArrayList<PuntuacionFecha> dataList = new ArrayList<PuntuacionFecha>();
        File file = new File(Constantes.SCORE_PATH);

        if(!file.exists() || file.length() == 0){
            return dataList;
        }


            JSONTokener parser = new JSONTokener( new FileInputStream(file));
            JSONArray jsonList = new JSONArray(parser);

            for(int i = 0; i < jsonList.length(); i++) {
                JSONObject obj = (JSONObject) jsonList.get(i);
                PuntuacionFecha data = new PuntuacionFecha();
                data.setPuntos(obj.getInt("puntos"));
                data.setFecha(obj.getString("fecha"));
                dataList.add(data);
            }

        return dataList;
    }

    public static void writeFile(ArrayList<PuntuacionFecha> dataList) throws IOException {
        File file = new File(Constantes.SCORE_PATH);

        File outputFile = null;
        outputFile.getParentFile().mkdirs();
        outputFile.createNewFile();

        JSONArray jsonList = new JSONArray();

        for(PuntuacionFecha data: dataList) {

            JSONObject obj = new JSONObject();
            obj.put("score", data.getPuntos());
            obj.put("date", data.getFecha());

            jsonList.put(obj);

        }

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile.toURI()));
        jsonList.write(writer);
        writer.close();
    }
}
