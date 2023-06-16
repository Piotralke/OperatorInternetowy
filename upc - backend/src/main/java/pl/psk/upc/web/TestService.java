package pl.psk.upc.web;

import com.github.javafaker.Faker;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.psk.upc.infrastructure.db.init.Offers;
import pl.psk.upc.infrastructure.db.init.Products;
import pl.psk.upc.infrastructure.entity.*;
import pl.psk.upc.infrastructure.enums.ContractForm;
import pl.psk.upc.infrastructure.enums.ProductType;
import pl.psk.upc.infrastructure.repository.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TestService {

    private final Faker faker;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    private final OfferRepository offerRepository;
    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;

    public TestService(ProductRepository productRepository, WarehouseRepository warehouseRepository, OfferRepository offerRepository, EmployeeRepository employeeRepository, ClientRepository clientRepository) {
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
        this.offerRepository = offerRepository;
        this.employeeRepository = employeeRepository;
        this.clientRepository = clientRepository;
        this.faker = Faker.instance();
    }

//   @EventListener(ApplicationReadyEvent.class)
//   public void initDb() {
//       init();
//   }

    public void init() {
        initProducts();
        initWarehouse();
        initOffers();
        initAdminAccount();
        initWorkerAccount();
        initClientAccounts();
    }

    private void initProducts() {
        for (Products p : Products.values()) {
            productRepository.save(ProductEntity.builder()
                    .uuid(UUID.randomUUID())
                    .name(p.getProductName())
                    .price(p.getPrice())
                    .productType(p.getProductType())
                    .description(p.getDescription())
                    .build());
        }
    }

    private void initWarehouse() {
        List<ProductEntity> allProducts = productRepository.findAll();
        for (ProductEntity p : allProducts) {
            warehouseRepository.save(WarehouseEntity.builder()
                    .uuid(p.getUuid())
                    .productName(p.getName())
                    .quantity(faker.number().numberBetween(0, 29))
                    .productEntity(p)
                    .build());
        }
    }

    private void initOffers() {
        for (Offers o : Offers.values()) {
            offerRepository.save(OfferEntity.builder()
                    .uuid(UUID.randomUUID())
                    .name(o.getOfferName())
                    .description(o.getDescription())
                    .offerType(o.getOfferType())
                    .price(o.getPrice())
                    .withDevice(o.isWithDevice())
                    .productEntity(getProductEntity(o.isWithDevice(), o.getProductType()))
                    .isArchival(o.isArchival())
                    .build());
        }
    }

    private ProductEntity getProductEntity(boolean isWithDevice, ProductType productType) {
        if (!isWithDevice) {
            return null;
        }
        List<ProductEntity> all = productRepository.findAll();
        List<ProductEntity> filteredByProductType = all.stream()
                .filter(productEntity -> productEntity.getProductType().equals(productType))
                .collect(Collectors.toList());

        int index = faker.number().numberBetween(0, filteredByProductType.size() - 1);
        return filteredByProductType.get(index);
    }

    private void initAdminAccount() {
        employeeRepository.save(EmployeeEntity.builder()
                .uuid(UUID.randomUUID())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password("admin")
                .roles("ADMIN")
                .phoneNumber(String.valueOf(661412255))
                .workplace("Kielce")
                .salary(12000.0)
                .contractForm(ContractForm.B2B)
                .build());
    }

    private void initWorkerAccount() {
        employeeRepository.save(EmployeeEntity.builder()
                .uuid(UUID.randomUUID())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password("worker")
                .roles("WORKER")
                .phoneNumber(String.valueOf(661412254))
                .workplace("Kielce")
                .salary(7000.0)
                .contractForm(ContractForm.B2B)
                .build());
    }

    private void initClientAccounts() {
        for (int i = 0; i < 10; i++) {
            clientRepository.save(ClientAccountEntity.builder()
                    .uuid(UUID.randomUUID())
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .email(faker.internet().emailAddress())
                    .password("client" + i)
                    .address(faker.address().fullAddress())
                    .balance(0)
                    .roles("USER")
                    .phoneNumber(faker.phoneNumber().phoneNumber())
                    .isBusinessClient(false)
                    .build());
        }
    }
}
