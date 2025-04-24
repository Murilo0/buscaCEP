package buscaCEP;
import buscaCEP.methods.ConsultaCep;
import buscaCEP.modulo.Adress;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String CEP = "";
        ConsultaCep consultaCep = new ConsultaCep();
        ArrayList<Adress> adressesList = new ArrayList<Adress>();

        while (!Objects.equals(CEP, "sair")){
            System.out.println("Digite o seu CEP para que seja feita a busca do endereco: ");
            CEP = scanner.nextLine();
            if (Objects.equals(CEP, "sair")){
                FileWriter escrita = new FileWriter("enderecos.json");
                escrita.write(gson.toJson(adressesList));
                escrita.close();
                scanner.close();
                break;
            }else{
                Adress newAdress = consultaCep.consultaCep(CEP);
                adressesList.add(newAdress);
            }
        }
        System.out.println(adressesList);
    }
}
