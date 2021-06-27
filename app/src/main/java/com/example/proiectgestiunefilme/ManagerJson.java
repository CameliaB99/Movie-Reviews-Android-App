package com.example.proiectgestiunefilme;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ManagerJson {
    private static ManagerJson instance;
    private ManagerJson(){

    }
    public static ManagerJson getInstance(){
        if (instance ==null){
            instance= new ManagerJson();
        }
        return instance;
    }
    public void preluareDate(final IJson listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://pastebin.com/raw/s3GvRDvK");
                    HttpURLConnection conexiune = (HttpURLConnection)url.openConnection();
                    InputStream stream = conexiune.getInputStream();
                    InputStreamReader reader = new InputStreamReader(stream);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String linie ="";
                    while((linie = bufferedReader.readLine())!=null){
                        stringBuilder.append(linie);
                    }
                    bufferedReader.close();
                    reader.close();
                    stream.close();
                    listener.onSuccess(parseDetailsData(stringBuilder.toString()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    listener.onFailure(e);
                } catch (IOException e) {
                    e.printStackTrace();
                    listener.onFailure(e);
                }

            }
        }).start();
    }
    private String parseDetailsData(String rezultat){
        String text= new String();
        try{
            text += "Detalii cinematograf \n\n";
            JSONObject obiect= new JSONObject(rezultat);
            JSONObject cinematograf = obiect.getJSONObject("cinematograf");
            text+="\t\t Logistica:\n";
            JSONObject logistica = cinematograf.getJSONObject("logistica");
            JSONArray sali = logistica.getJSONArray("sali");
            text+="\t\t\t\t Sali:\n";
            for(int i=0;i<sali.length();i++){
                JSONObject obiectcurent=sali.getJSONObject(i);
                String tip = obiectcurent.getString("tip");
                int nrLocuri=obiectcurent.getInt("nrLocuri");
                String dotari=obiectcurent.getString("dotari");
                text+="\t\t\t\t\t tip:"+tip+", nr locuri: "+nrLocuri+", dotari: "+dotari+"\n";
            }
            JSONArray echipamente= logistica.getJSONArray("echipamente");
            text+="\t\t\t\t Echipamente:\n";
            for(int i=0;i<echipamente.length();i++){
                JSONObject obiectcurent=echipamente.getJSONObject(i);
                String tip = obiectcurent.getString("tip");
                int an=obiectcurent.getInt("an");
                String uzura=obiectcurent.getString("uzura");
                text+="\t\t\t\t\t tip:"+tip+", an: "+an+", uzura: "+uzura+"\n";
            }
            JSONObject dotari = logistica.getJSONObject("dotari");
            text+="\t\t\t\t  Dotari:\n";
            JSONObject obiect_tichete = dotari.getJSONObject("aparatTichete");
            text+="\t\t\t\t Aparat tichete: ";
            int nrTichete =obiect_tichete.getInt("nrTichete");
            int bani=obiect_tichete.getInt("bani");
            String functionare_tichete=obiect_tichete.getString("functionare");
            text+=" nr tichete:"+nrTichete+", bani: "+bani+", functionare: "+functionare_tichete+"\n";

            JSONObject obiect_popcorn = dotari.getJSONObject("aparatPopcorn");
            text+="\t\t\t\t Aparat popcorn: ";
            String tip_popcorn = obiect_popcorn.getString("tip");
            int cantitate_popcorn = obiect_popcorn.getInt("cantitate");
            String voucher = obiect_popcorn.getString("voucher");
            text+= " tip: "+tip_popcorn+", cantitate: "+cantitate_popcorn+", voucher: "+voucher+"\n";

            JSONObject obiect_Cafea = dotari.getJSONObject("aparatCafea");
            text+="\t\t\t\t Aparat cafea: ";
            String tip_cafea = obiect_Cafea.getString("tip");
            int cantitate_cafea=obiect_Cafea.getInt("cantitate");
            String functionare_cafea=obiect_Cafea.getString("functionare");
            text += " tip: "+tip_cafea+", cantitate: "+cantitate_cafea+", functionare: "+functionare_cafea+"\n";
            JSONObject angajati = cinematograf.getJSONObject("angajati");
            text+= "\t\t Angajati: \n";
            JSONObject femeiServiciu = angajati.getJSONObject("femeiServiciu");
            text+= "\t\t\t\t Femeie de serviciu: \n";
            String numeF = femeiServiciu.getString("nume");
            int varstaF = femeiServiciu.getInt("varsta");
            String programF = femeiServiciu.getString("program");
            text+="\t\t\t\t\t nume: "+numeF+", varsta: "+varstaF+", program: "+programF+"\n";

            JSONObject actori = angajati.getJSONObject("actori");
            text+= "\t\t\t\t Actori: \n";
            String numeA = actori.getString("nume");
            int varstaA = actori.getInt("varsta");
            int nrFilme = actori.getInt("nrFilme");
            text+="\t\t\t\t\t nume: "+numeA+", varsta: "+varstaA+", nr filme: "+nrFilme+"\n";

            JSONObject regizori = angajati.getJSONObject("regizori");
            text+= "\t\t\t\t Regizori: \n";
            int nrFilmeReg = regizori.getInt("nrFilme");
            int nrSeriale = regizori.getInt("nrSeriale");
            int nrAsistenti = regizori.getInt("nrAsistenti");
            text+="\t\t\t\t\t nr filme: "+nrFilmeReg+", nr seriale: "+nrSeriale+", nr asistenti: "+nrAsistenti+"\n";
            JSONObject contabilitate = cinematograf.getJSONObject("contabilitate");
            text+= "\t\t Contabilitate: \n";
            JSONObject salariu = contabilitate.getJSONObject("salariu");
            text+= "\t\t\t\t Salariu: \n";
            int brut = salariu.getInt("brut");
            int net = salariu.getInt("net");
            int contributii = salariu.getInt("contributii");
            text+="\t\t\t\t\t brut: "+brut+", net: "+net+", contributii: "+contributii+"\n";

            JSONObject detalii = contabilitate.getJSONObject("detalii");
            text+= "\t\t\t\t Detalii: \n";
            String numeD = detalii.getString("nume");
            String prenumeD = detalii.getString("prenume");
            String cnp = detalii.getString("cnp");
            text+="\t\t\t\t\t nume: "+numeD+", prenume: "+prenumeD+", cnp: "+cnp+"\n";


            JSONObject spor = contabilitate.getJSONObject("sporSalariu");
            text+= "\t\t\t\t Spor salariu: \n";
            String vechime = spor.getString("vechime");
            String noapte = spor.getString("noapte");
            String limba = spor.getString("limba");
            text+="\t\t\t\t\t vechime: "+vechime+", noapte: "+noapte+", limba: "+limba+"\n";







        } catch (JSONException e) {
            e.printStackTrace();
        }
        return text;
    }

}
