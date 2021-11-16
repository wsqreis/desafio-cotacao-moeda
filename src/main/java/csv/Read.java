package csv;

import com.neovisionaries.i18n.CurrencyCode;
import model.Moeda;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Scanner;

public class Read {

    public List<Moeda> readCsv(String pathName) {
        List<Moeda> list = new ArrayList<>();
        File csv = new File(pathName);
        try (Scanner sc = new Scanner(csv)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(";");

                String codMoeda = data[1];
                String tipo = data[2];
                String moeda = data[3];
                BigDecimal taxaCompra = new BigDecimal(data[4].replaceAll(",", "."));
                BigDecimal taxaVenda = new BigDecimal(data[5].replaceAll(",", "."));
                BigDecimal paridadeCompra = new BigDecimal(data[6].replaceAll(",", "."));
                BigDecimal paridadeVenda = new BigDecimal(data[7].replaceAll(",", "."));
                Moeda currency = new Moeda(codMoeda, tipo, moeda, taxaCompra, taxaVenda, paridadeCompra, paridadeVenda);

                list.add(currency);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public BigDecimal encontraCotacaoDolar(List<Moeda> list){
        BigDecimal cotacaoDolar = null;
        if (list != null) {
            for (Moeda moeda : list) {
                if (moeda.getMoeda().contains("USD")){
                    cotacaoDolar = moeda.getTaxaCompra();
                }
            }
        }
        return cotacaoDolar;
    }

    public void calculaParidade(List<Moeda> list){
        BigDecimal cotacaoDolar = encontraCotacaoDolar(list);
        if (list != null) {
            for (Moeda moeda : list) {
                if (moeda.getTipo().equals("A")) {
                    moeda.setParidadeUsd(moeda.getTaxaCompra().divide(cotacaoDolar, RoundingMode.HALF_EVEN));
                } else if (moeda.getTipo().equals("B")) {
                    moeda.setParidadeUsd(cotacaoDolar.divide(moeda.getTaxaCompra(), RoundingMode.HALF_EVEN));
                }
            }
        }
    }

    public void menorCotacao(List<Moeda> list){
        calculaParidade(list);
        BigDecimal menorCotacao = list.get(0).getParidadeUsd();
        String moedaMenorCotacao = list.get(0).getMoeda();
        CurrencyCode currency = CurrencyCode.getByCode(moedaMenorCotacao);
        String paisMenorCotacao = currency.getCountryList().get(0).getName();
        Currency simbolo = Currency.getInstance(moedaMenorCotacao);
        for (Moeda moeda : list) {
           if (menorCotacao.compareTo(moeda.getParidadeUsd()) > 0){
                menorCotacao = moeda.getParidadeUsd();
                moedaMenorCotacao = moeda.getMoeda();
                currency = CurrencyCode.getByCode(moedaMenorCotacao);
                paisMenorCotacao = currency.getCountryList().get(0).getName();
                simbolo = Currency.getInstance(moedaMenorCotacao);
           }
        }

        String pais = paisMenorCotacao.substring(0,paisMenorCotacao.indexOf(","));
        String menorCotacaoUsd = menorCotacao.toPlainString();

        System.out.println(simbolo.getSymbol() + ", " + pais + ", "
                + "1 " + moedaMenorCotacao + " = " + menorCotacaoUsd + " USD");
    }

}
