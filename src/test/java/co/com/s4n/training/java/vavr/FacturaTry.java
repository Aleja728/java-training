package co.com.s4n.training.java.vavr;

import co.com.s4n.training.java.Factura;
import io.vavr.control.Try;
import org.junit.Test;

import static io.vavr.API.Success;
import static junit.framework.TestCase.assertEquals;

public class FacturaTry {
/*

    @Test
    public void FacturaRecoverSuccess(){
        Try<Double> res = Factura.calcularSubTotal("cuaderno,10000;lapiz,2000")
                .flatMap(r0->Factura.calcularDescuentos(r0,50)
                        .flatMap(r1 -> Factura.calcularRetencion(r1,28).recover(Exception.class,1.0)
                        ));

        assertEquals(Success(7680.0),res);
    }

    @Test
    public void FacturaRecoverFailure(){
        Try<Double> res = Factura.calcularSubTotal("cuaderno,10000;lapiz,2000")
                .flatMap(r0->Factura.calcularDescuentos(r0,70)
                        .flatMap(r1 -> Factura.calcularRetencion(r1,28).recover(Exception.class,1.0)
                        ));

        assertEquals(Try.failure(new Exception("Descuento no puede ser mayor a 60%")).toString(),res.toString());
    }

    @Test
    public void FacturaRecoverWithSuccess(){
        Try<Double> res = Factura.calcularSubTotal("cuaderno,10000;lapiz,2000")
                .flatMap(r0->Factura.calcularDescuentos(r0,10).recoverWith(Exception.class,Factura.calcularDescuentos(r0,10)
                        .flatMap(r1 -> Factura.calcularRetencion(r1,19))
                ));
        assertEquals(Success(10800.0),res);
    }
    @Test
    public void FacturaRecoverWithFailure(){
        Try<Double> res = Factura.calcularSubTotal("cuaderno,10000;lapiz,2000")
                .flatMap(r0->Factura.calcularDescuentos(r0,70).recoverWith(Exception.class,Factura.calcularDescuentos(r0,10)
                        .flatMap(r1 -> Factura.calcularRetencion(r1,19))
                        ));
        assertEquals(Success(12852.0),res);
    }*/
}
