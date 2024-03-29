package structural.facade;

import structural.facade.enums.Products;
import structural.facade.microservices.DeliveryService;
import structural.facade.microservices.OrderPickingService;
import structural.facade.microservices.PackingService;

import java.util.List;

public class OnlineShopFacade {
    private OrderPickingService orderPickingService;
    private PackingService packingService;
    private DeliveryService deliveryService;

    public OnlineShopFacade(){
        orderPickingService = new OrderPickingService();
        packingService = new PackingService();
        deliveryService = new DeliveryService();
    }
    public ProductPackage buyAndDeliver(String address, Products... products){
        List<Products> basket = orderPickingService.pickOrder(products);
        ProductPackage pack = packingService.pack(basket);
        deliveryService.sendPackage(address,pack);
        return pack;
    }
}
