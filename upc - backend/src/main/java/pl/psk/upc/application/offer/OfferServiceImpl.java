package pl.psk.upc.application.offer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.psk.upc.infrastructure.dto.SaveProductWithOfferRequestDto;
import pl.psk.upc.infrastructure.entity.OfferEntity;
import pl.psk.upc.infrastructure.entity.OfferType;
import pl.psk.upc.infrastructure.entity.ProductEntity;
import pl.psk.upc.infrastructure.repository.OfferRepository;
import pl.psk.upc.infrastructure.repository.ProductRepository;
import pl.psk.upc.web.offer.OfferDto;
import pl.psk.upc.web.offer.OfferDtoWrapper;
import pl.psk.upc.web.offer.SaveOfferRequestDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ProductRepository productRepository;

    public OfferServiceImpl(OfferRepository offerRepository, ProductRepository productRepository) {
        this.offerRepository = offerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OfferDtoWrapper getOffersByType(OfferType offerType) {
        List<OfferEntity> offers = offerRepository.findByOfferType(offerType);
        return OfferConverter.convertFrom(offers);
    }

    @Override
    public OfferDtoWrapper getAllOffers() {
        List<OfferEntity> all = offerRepository.findAll();
        return OfferConverter.convertFrom(all);
    }

    @Override
    public OfferDto getOfferByUuid(UUID uuid) {
        OfferEntity offer = offerRepository.findByUuid(uuid)
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));
        return OfferConverter.convertFrom(offer);
    }

    @Override
    public OfferDto saveOffer(SaveOfferRequestDto saveOfferRequestDto) {
        SaveProductWithOfferRequestDto saveProductWithOfferRequestDto = saveOfferRequestDto.getSaveProductWithOfferRequestDto();

        if (saveProductWithOfferRequestDto != null && saveProductWithOfferRequestDto.getUuid() == null) {
            ProductEntity newProduct = ProductEntity.builder()
                    .uuid(UUID.randomUUID())
                    .name(saveProductWithOfferRequestDto.getName())
                    .price(saveProductWithOfferRequestDto.getPrice())
                    .description(saveProductWithOfferRequestDto.getDescription())
                    .productType(saveProductWithOfferRequestDto.getProductType())
                    .build();
            ProductEntity savedProduct = productRepository.save(newProduct);

            OfferEntity newOffer = OfferEntity.builder()
                    .uuid(UUID.randomUUID())
                    .name(saveOfferRequestDto.getName())
                    .description(saveOfferRequestDto.getDescription())
                    .price(saveOfferRequestDto.getPrice())
                    .productEntity(savedProduct)
                    .withDevice(true)
                    .offerType(saveOfferRequestDto.getOfferType())
                    .build();
            return OfferConverter.convertFrom(offerRepository.save(newOffer));
        }

        if (saveProductWithOfferRequestDto != null && saveProductWithOfferRequestDto.getUuid() != null) {
            ProductEntity product = productRepository.findByUuid(saveProductWithOfferRequestDto.getUuid())
                    .orElseThrow(() -> new UsernameNotFoundException("Not found"));

            OfferEntity newOffer = OfferEntity.builder()
                    .uuid(UUID.randomUUID())
                    .name(saveOfferRequestDto.getName())
                    .description(saveOfferRequestDto.getDescription())
                    .price(saveOfferRequestDto.getPrice())
                    .productEntity(product)
                    .withDevice(true)
                    .offerType(saveOfferRequestDto.getOfferType())
                    .build();
            return OfferConverter.convertFrom(offerRepository.save(newOffer));

        }

        OfferEntity newOffer = OfferEntity.builder()
                .uuid(UUID.randomUUID())
                .name(saveOfferRequestDto.getName())
                .description(saveOfferRequestDto.getDescription())
                .price(saveOfferRequestDto.getPrice())
                .productEntity(null)
                .withDevice(false)
                .offerType(saveOfferRequestDto.getOfferType())
                .build();
        return OfferConverter.convertFrom(offerRepository.save(newOffer));
    }
}
