package buscaCEP;
import buscaCEP.methods.ConsultaCep;
import buscaCEP.modulo.Adress;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String CEP = "";
        ConsultaCep consultaCep = new ConsultaCep();
        ArrayList adressesList = new ArrayList<Adress>();

        while (!Objects.equals(CEP, "sair")){
            System.out.println("Digite o seu CEP para que seja feita a busca do endereco: ");
            CEP = scanner.nextLine();
            if (Objects.equals(CEP, "sair")){
                break;
            }else{
                Adress newAdress = consultaCep.consultaCep(CEP);
                adressesList.add(newAdress);
            }
        }
    }
}
