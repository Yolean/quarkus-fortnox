package org.openapi.quarkus.openapi.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import se.yolean.fortnox.client.model.FortnoxCustomer;
import se.yolean.fortnox.client.model.FortnoxCustomerDefaultDeliveryTypes;
import se.yolean.fortnox.client.model.FortnoxInvoice; // Ensure this import matches the generated class

public class FortnoxSchemaHacksTest {

  @Test
  void testPaymentWayCanBeSetToEmptyString() {
    // Create an instance of the generated FortnoxInvoice class
    FortnoxInvoice invoice = new FortnoxInvoice();

    // Set the PaymentWay property to an empty string
    invoice.setPaymentWay(FortnoxInvoice.PaymentWayEnum.EMPTY);

    // Assert that getPaymentWay() returns the empty string
    assertEquals(FortnoxInvoice.PaymentWayEnum.EMPTY, invoice.getPaymentWay(),
        "PaymentWay should be an empty string after being set to empty.");
  }

  @Test
  void testCustomerDefaultDeliveryTypesElectronicInvoice() {
    // Create an instance of the generated FortnoxCustomer class
    FortnoxCustomer customer = new FortnoxCustomer();
    var types = new FortnoxCustomerDefaultDeliveryTypes();
    customer.setDefaultDeliveryTypes(types);

    // Set the DefaultDeliveryTypes property to an empty string
    types.setInvoice(FortnoxCustomerDefaultDeliveryTypes.InvoiceEnum.ELECTRONICINVOICE);

    // Assert that getDefaultDeliveryTypes() returns the empty string
    assertEquals(FortnoxCustomerDefaultDeliveryTypes.InvoiceEnum.ELECTRONICINVOICE, customer.getDefaultDeliveryTypes().getInvoice(),
        "DefaultDeliveryTypes.Invoice should be ELECTRONICINVOICE after being set to ELECTRONICINVOICE.");
  }
}