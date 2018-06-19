package co.com.s4n.training.java.vavr;

import io.vavr.Lazy;
import io.vavr.concurrent.Future;

import org.junit.platform.runner.IncludeEngines;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

@RunWith(JUnitPlatform.class)
@IncludeEngines("junit-jupiter")
public class LazySuite {

        private void sleep(int milliseconds){
            try{
                Thread.sleep(milliseconds);
            }catch(Exception e){
                System.out.println("Problemas durmiendo hilo");
            }
        }

        @Test
        public void EjercicioLazy(){
            Lazy<Future<Integer>> f1 = Lazy.of(()-> Future.of(() ->{
                Thread.sleep(500);
                return 1;
            }));
            Lazy<Future<Integer>> f2 = Lazy.of(()-> Future.of(() ->{
                Thread.sleep(800);
                return 2;
            }));
            Lazy<Future<Integer>> f3 = Lazy.of(()-> Future.of(() ->{
                Thread.sleep(300);
                return 3;
            }));

            long inicio = System.nanoTime();

            Future<Integer> result =
                    f1.get().flatMap(s -> f2.get()
                            .flatMap(s1 -> f3.get()
                                    .flatMap(s2 -> Future.of(() -> s+s1+s2))));


            result.await().get();

            long fin = System.nanoTime();
            Double elapsed = (fin-inicio)*Math.pow(10 ,- 6);
            System.out.println(elapsed);

            long inicio2 = System.nanoTime();

            Future<Integer> result2 =
                    f1.get().flatMap(s -> f2.get()
                            .flatMap(s1 -> f3.get()
                                    .flatMap(s2 -> Future.of(() -> s+s1+s2))));
            result2.await().get();

            long fin2 = System.nanoTime();
            elapsed = (fin2-inicio2)*Math.pow(10 ,- 6);
            System.out.println(elapsed);

        }

        @Test
        public void SuppliervsLazy(){
            Supplier<String> supl = () ->{
                sleep(500);
                return "Alejandra";
            };

            Lazy<Future<String>> f1 = Lazy.of(()-> Future.of(() ->{
                sleep(500);
                return "Cadavid";
            }));


            long inicio = System.nanoTime();
            supl.get();
            long fin = System.nanoTime();

            long inicio2 = System.nanoTime();
            f1.get().await();
            long fin2 = System.nanoTime();


            //Memoizing
            long inicioM = System.nanoTime();
            supl.get();
            long finM = System.nanoTime();

            long inicio2M = System.nanoTime();
            f1.get().await();
            long fin2M = System.nanoTime();


            Double elapsed = (fin-inicio)*Math.pow(10 ,- 6);
            System.out.println("Supplier 1: "+elapsed);

            elapsed = (finM-inicioM)*Math.pow(10 ,- 6);
            System.out.println("Supplier 2: "+elapsed);

            elapsed = (fin2-inicio2)*Math.pow(10 ,- 6);
            System.out.println("Lazy 3: "+elapsed);

            elapsed = (fin2M-inicio2M)*Math.pow(10 ,- 6);
            System.out.println("lazy 4: "+elapsed);

        }

}
