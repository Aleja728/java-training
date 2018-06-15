package co.com.s4n.training.java;

import io.vavr.collection.List;
import io.vavr.control.Option;

public class Factura {

    // elemento1,valor;elemento2,valor;elemento3,valor
    public static Option<Double> calcularSubTotal(String elementoValor){
        String[] elementos = elementoValor.split("\\;");
        Double subTotal=0.0;

        for(int i=0; i<elementos.length;i++){
            String[] valores1 = elementos[i].split("\\,");

            subTotal += Double.parseDouble(valores1[1]);
        }

        System.out.println("Subtotal: "+subTotal);
        return Option.of(subTotal);
    }

    public static Option<Double> calcularDescuentos(Double subtotal, int descuento){
        Double descuentoD = descuento/100.0;
        Double valorConDescuentos = subtotal-(subtotal*descuentoD) ;
        System.out.println("Valor con Descuentos: "+valorConDescuentos);
        return Option.of(valorConDescuentos);
    }

    public static Option<Double> calcularRetencion(Double subtotal, int retencion){
        Double retencionD = retencion/100.0;
        Double valorTotal = subtotal-(subtotal*retencionD) ;
        System.out.println("Valor Total: "+valorTotal);
        return Option.of(valorTotal);
    }
}
