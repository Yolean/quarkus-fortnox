package org.openapi.quarkus.openapi.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import se.yolean.fortnox.client.model.FortnoxDefaultDeliveryTypesSingleItem;
import se.yolean.fortnox.client.model.FortnoxKfCustomerSingleItem;
import se.yolean.fortnox.client.model.FortnoxKfInvoiceSingleItem; // Ensure this import matches the generated class

public class FortnoxSchemaHacksTest {

  @Test
  void testPaymentWayCanBeSetToEmptyString() {
    // Create an instance of the generated FortnoxKfInvoiceSingleItem class
    FortnoxKfInvoiceSingleItem invoice = new FortnoxKfInvoiceSingleItem();

    // Set the PaymentWay property to an empty string
    invoice.setPaymentWay(FortnoxKfInvoiceSingleItem.PaymentWayEnum.EMPTY);

    // Assert that getPaymentWay() returns the empty string
    assertEquals(FortnoxKfInvoiceSingleItem.PaymentWayEnum.EMPTY, invoice.getPaymentWay(),
        "PaymentWay should be an empty string after being set to empty.");
  }

  @Test
  void testCustomerDefaultDeliveryTypesElectronicInvoice() {
    // Create an instance of the generated FortnoxKfCustomerSingleItem class
    FortnoxKfCustomerSingleItem customer = new FortnoxKfCustomerSingleItem();
    var types = new FortnoxDefaultDeliveryTypesSingleItem();
    customer.setDefaultDeliveryTypes(types);

    // Set the DefaultDeliveryTypes property to an empty string
    types.setInvoice(FortnoxDefaultDeliveryTypesSingleItem.InvoiceEnum.ELECTRONICINVOICE);

    // Assert that getDefaultDeliveryTypes() returns the empty string
    assertEquals(FortnoxDefaultDeliveryTypesSingleItem.InvoiceEnum.ELECTRONICINVOICE, customer.getDefaultDeliveryTypes().getInvoice(),
        "DefaultDeliveryTypes.Invoice should be ELECTRONICINVOICE after being set to ELECTRONICINVOICE.");
  }
}