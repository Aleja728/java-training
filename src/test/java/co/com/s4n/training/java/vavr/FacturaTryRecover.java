package co.com.s4n.training.java.vavr;

import co.com.s4n.training.java.FacturaTry;
import io.vavr.control.Try;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.IncludeEngines;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static io.vavr.API.Success;
import static junit.framework.TestCase.assertEquals;

@RunWith(JUnitPlatform.class)
@IncludeEngines("junit-jupiter")
public class FacturaTryRecover {


    @Test
    public void FacturaRecoverSuccess(){
        Try<Double> res = FacturaTry.calcularSubTotal("cuaderno,10000;lapiz,2000")
                .flatMap(r0->FacturaTry.calcularDescuentos(r0,50)
                        .flatMap(r1 -> FacturaTry.calcularRetencion(r1,28).recover(Exception.class,1.0)
                        ));

        assertEquals(Success(7680.0),res);
    }

    @Test
    public void FacturaRecoverFailure(){
        Try<Double> res = FacturaTry.calcularSubTotal("cuaderno,10000;lapiz,2000")
                .flatMap(r0->FacturaTry.calcularDescuentos(r0,70)
                        .flatMap(r1 -> FacturaTry.calcularRetencion(r1,28).recover(Exception.class,1.0)
                        ));

        assertEquals(Try.failure(new Exception("Descuento no puede ser mayor a 60%")).toString(),res.toString());
    }

    @Test
    public void FacturaRecoverWithSuccess(){
        Try<Double> res = FacturaTry.calcularSubTotal("cuaderno,10000;lapiz,2000")
                .flatMap(r0->FacturaTry.calcularDescuentos(r0,10).recoverWith(Exception.class,FacturaTry.calcularDescuentos(r0,10)
                        .flatMap(r1 -> FacturaTry.calcularRetencion(r1,19))
                ));
        assertEquals(Success(10800.0),res);
    }
    @Test
    public void FacturaRecoverWithFailure(){
        Try<Double> res = FacturaTry.calcularSubTotal("cuaderno,10000;lapiz,2000")
                .flatMap(r0->FacturaTry.calcularDescuentos(r0,70).recoverWith(Exception.class,FacturaTry.calcularDescuentos(r0,10)
                        .flatMap(r1 -> FacturaTry.calcularRetencion(r1,19))
                        ));
        assertEquals(Success(12852.0),res);
    }
}
