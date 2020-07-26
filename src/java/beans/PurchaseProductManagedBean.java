package beans;

import beans.model.ShoppingCartItem;
import business.facade.PurchaseProductFacadeLocal;
import business.model.Purchase;
import business.model.PurchaseProduct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@Named(value = "purchaseProductManagedBean")
@RequestScoped
public class PurchaseProductManagedBean {

    @Inject
    PurchaseManagedBean purchaseManagedBean;

    @EJB
    private PurchaseProductFacadeLocal purchaseProductFacadeLocal;

    public PurchaseProductManagedBean() {
    }

    public PurchaseManagedBean getPurchaseManagedBean() {
        return purchaseManagedBean;
    }

    public void setPurchaseManagedBean(PurchaseManagedBean purchaseManagedBean) {
        this.purchaseManagedBean = purchaseManagedBean;
    }

    public void savePurchases(String username) {
        
        Purchase purchase = getPurchaseManagedBean().savePurchase(username);
        for (ShoppingCartItem item : getPurchaseManagedBean().getProductManagedBean().getShoppingCartItems()) {
            PurchaseProduct pp = new PurchaseProduct();
            pp.setProductId(item.getProduct());
            pp.setPurchaseId(purchase);
            pp.setQuantity(item.getQuantity());
            purchaseProductFacadeLocal.create(pp);
        }
        getPurchaseManagedBean().cleanShoppingCartItems();
    }

}
