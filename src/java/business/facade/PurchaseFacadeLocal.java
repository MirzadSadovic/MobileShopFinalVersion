package business.facade;

import business.model.Purchase;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;


@Local
public interface PurchaseFacadeLocal {

    void create(Purchase purchase);

    void edit(Purchase purchase);

    void remove(Purchase purchase);

    Purchase find(Object id);

    List<Purchase> findAll();

    List<Purchase> findRange(int[] range);

    int count();
    
    public Purchase createPurchase(String username,String cardNumber, BigDecimal totalPrice);
}
