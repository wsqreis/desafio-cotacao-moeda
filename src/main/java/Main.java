import csv.Read;
import model.Moeda;
import url.Download;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Informe uma data no formato YYYYMMDD: ");
        String data = sc.nextLine();

        String link = "https://www4.bcb.gov.br/Download/fechamento/" + data + ".csv";

        String diretorio = System.getProperty("user.dir");

        String caminho = diretorio + "\\src\\main\\resources\\" + data + ".csv";
        File cotacao = new File(caminho);

        Download download = new Download(link, cotacao);
        download.run();

        if (cotacao.exists()){
            Read read = new Read();
            List<Moeda> list = read.readCsv(caminho);
            read.menorCotacao(list);

        }else if (!cotacao.exists()) {
            System.out.println("x");
        }

        sc.close();
    }
}
