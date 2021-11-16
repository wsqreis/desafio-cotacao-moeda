package model;

import java.math.BigDecimal;

public class Moeda {

    String codMoeda;
    String tipo;
    String moeda;
    BigDecimal taxaCompra;
    BigDecimal taxaVenda;
    BigDecimal paridadeCompra;
    BigDecimal paridadeVenda;
    BigDecimal paridadeUsd;

    public Moeda(String codMoeda, String tipo, String moeda, BigDecimal taxaCompra, BigDecimal taxaVenda, BigDecimal paridadeCompra, BigDecimal paridadeVenda) {
        this.codMoeda = codMoeda;
        this.tipo = tipo;
        this.moeda = moeda;
        this.taxaCompra = taxaCompra;
        this.taxaVenda = taxaVenda;
        this.paridadeCompra = paridadeCompra;
        this.paridadeVenda = paridadeVenda;
    }

    @Override
    public String toString() {
        return "\nMoeda{"
                + "codMoeda= " + codMoeda
                + ", tipo= " + tipo
                + ", moeda= " + moeda
                + ", taxaCompra= " + taxaCompra
                + ", taxaVenda= " + taxaVenda
                + ", paridadeCompra= " + paridadeCompra
                + ", paridadeVenda= " + paridadeVenda
                + ", paridadeUSD= " + paridadeUsd
                + "}";
    }

    public String getCodMoeda() {
        return codMoeda;
    }

    public void setCodMoeda(String codMoeda) {
        this.codMoeda = codMoeda;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public BigDecimal getTaxaCompra() {
        return taxaCompra;
    }

    public void setTaxaCompra(BigDecimal taxaCompra) {
        this.taxaCompra = taxaCompra;
    }

    public BigDecimal getTaxaVenda() {
        return taxaVenda;
    }

    public void setTaxaVenda(BigDecimal taxaVenda) {
        this.taxaVenda = taxaVenda;
    }

    public BigDecimal getParidadeCompra() {
        return paridadeCompra;
    }

    public void setParidadeCompra(BigDecimal paridadeCompra) {
        this.paridadeCompra = paridadeCompra;
    }

    public BigDecimal getParidadeVenda() {
        return paridadeVenda;
    }

    public void setParidadeVenda(BigDecimal paridadeVenda) {
        this.paridadeVenda = paridadeVenda;
    }

    public BigDecimal getParidadeUsd() {
        return paridadeUsd;
    }

    public void setParidadeUsd(BigDecimal paridadeUsd) {
        this.paridadeUsd = paridadeUsd;
    }
}