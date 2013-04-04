package at.indra_prasnikar.m2lt3;

import at.indra_prasnikar.m2lt3.bo.Buch;
import at.indra_prasnikar.m2lt3.control.BuchJpaController;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BuchJpaController bjc = new BuchJpaController();
        Buch b = new Buch();
//        b.setId("A123");
        bjc.create(b);
    }
}
