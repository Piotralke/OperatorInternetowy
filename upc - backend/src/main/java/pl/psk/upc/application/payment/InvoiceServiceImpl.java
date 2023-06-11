package pl.psk.upc.application.payment;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.stereotype.Service;
import pl.psk.upc.exception.GenericNotFoundException;
import pl.psk.upc.tech.MethodArgumentValidator;
import pl.psk.upc.web.payment.InvoiceDto;
import pl.psk.upc.web.product.ProductDto;

import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.util.List;
import java.util.UUID;

@Service
class InvoiceServiceImpl implements InvoiceService {
    private final static String NOT_FOUND_MESSAGE = "Invoice not found";

    private final PaymentService paymentService;

    public InvoiceServiceImpl(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public ResourceRegion generateInvoice(UUID clientUuid, UUID paymentUuid) {
        MethodArgumentValidator.requiredNotNull(clientUuid, "clientUuid");
        MethodArgumentValidator.requiredNotNull(paymentUuid, "paymentUuid");
        InvoiceDto invoiceDto = getInvoiceDto(clientUuid, paymentUuid);
        String path = "testowy.pdf";
        if (new File(path).exists()) {
            new File(path).delete();
        }
        Document document = new Document(PageSize.A4);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            document.add(prepareHeader());
            document.add(new Paragraph("\n"));
            document.add(preparePayerInfo(invoiceDto));
            document.add(new Paragraph("\n"));
            document.add(prepareProductsInfo(invoiceDto.getProductDtos(), invoiceDto.getName(), invoiceDto.getAmount()));
            document.add(prepareTotalInfo(invoiceDto.getAmount()));
            document.close();

        } catch (DocumentException e) {
            document.close();
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            FileUrlResource fileUrlResource = new FileUrlResource(path);
            return new ResourceRegion(fileUrlResource, 0, fileUrlResource.contentLength());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Table prepareHeader() {
        try {
            Table table = new Table(2, 1);
            table.setWidths(new float[]{200f, 360f});
            table.addCell("INVOICE");
            table.addCell("UPC");
            table.setBackgroundColor(new Color(192,192,192));
            table.setBorder(0);
            return table;
        } catch (BadElementException e) {
            throw new RuntimeException(e);
        }
    }

    private Table preparePayerInfo(InvoiceDto invoiceDto) {
        try {
            Table table = new Table(1, 1);
            table.setWidths(new float[]{560f});
            table.addCell(new Cell("Client Information"));
            table.addCell("First name: " + invoiceDto.getClientFirstName() + " " + invoiceDto.getClientLastName());
            table.addCell("Address: " + invoiceDto.getClientAddress());
            table.addCell("Pesel: " + invoiceDto.getClientPesel());
            table.addCell("Creation date: " + invoiceDto.getCreationDate());
            table.addCell("Payment date: " + invoiceDto.getPaymentDate());
            table.addCell("Payment status: " + invoiceDto.getPaymentStatus());
            table.setBorder(0);
            return table;
        } catch (BadElementException e) {
            throw new RuntimeException(e);
        }
    }

    private Table prepareProductsInfo(List<ProductDto> products, String serviceName, double totalAmount) {
        List<Double> productsPriceList = products.stream()
                .map(ProductDto::getPrice)
                .toList();
        Double totalProductsPrice = 0d;
        for (Double price : productsPriceList) {
            totalProductsPrice += price;
        }
        double servicePrice = totalAmount - totalProductsPrice;
        try {
            Table table = new Table(2, 2);
            table.setWidths(new float[]{360f, 200f});
            table.addCell("Product name");
            table.addCell("Product price");
            table.addCell(serviceName);
            table.addCell(String.valueOf(servicePrice));
            for (ProductDto p : products) {
                table.addCell(p.getName());
                table.addCell(String.valueOf(p.getPrice()));
            }
            return table;
        } catch (BadElementException e) {
            throw new RuntimeException(e);
        }
    }

    private Table prepareTotalInfo(double totalAmount) {
        try {
            Table table = new Table(1, 1);
            table.setWidths(new float[]{560f});
            table.addCell("Total amount: " + totalAmount);
            table.setBorder(0);
            return table;
        } catch (BadElementException e) {
            throw new RuntimeException(e);
        }
    }

    private InvoiceDto getInvoiceDto(UUID clientUuid, UUID paymentUuid) {
        List<InvoiceDto> invoices = paymentService.findAllByUser(clientUuid)
                .getContent();
        return invoices.stream()
                .filter(invoiceDto -> invoiceDto.getUuid().equals(paymentUuid))
                .findFirst()
                .orElseThrow(() -> new GenericNotFoundException(NOT_FOUND_MESSAGE));
    }


}
