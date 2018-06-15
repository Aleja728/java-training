package co.com.s4n.training.java;

import io.vavr.control.Try;
import org.omg.PortableInterceptor.SUCCESSFUL;

public class Factura {

    // elemento1,valor;elemento2,valor;elemento3,valor
    public static Try<Double> calcularSubTotal(String elementoValor){


        String[] elementos = elementoValor.split("\\;");
        Double subTotal=0.0;

        for(int i=0; i<elementos.length;i++){
            String[] valores1 = elementos[i].split("\\,");

            subTotal += Double.parseDouble(valores1[1]);
        }


        System.out.println("Subtotal: "+subTotal);
        Double finalSubTotal = subTotal;
        return Try.of(() -> finalSubTotal);
    }

    public static Try<Double> calcularDescuentos(Double subtotal, int descuento){
        Double descuentoD = descuento/100.0;
        Double valorConDescuentos = subtotal-(subtotal*descuentoD) ;
        System.out.println("Valor con Descuentos: "+valorConDescuentos);

        return (descuento<60)?Try.of(()->valorConDescuentos):Try.failure(new Exception("Descuento no puede ser mayor a 60%"));
    }

    public static Try<Double> calcularRetencion(Double subtotal, int retencion) {
        Double retencionD = retencion / 100.0;
        Double valorTotal = subtotal + (subtotal * retencionD);
        System.out.println("Valor Total: " + valorTotal);
        return Try.of(() -> valorTotal);

    }

}
