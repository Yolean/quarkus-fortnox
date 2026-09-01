# Hand-edits to openapi.json

`openapi.json` is downloaded from <https://api.fortnox.se/apidocs>, then edited by hand. The
document as Fortnox publishes it does not generate compilable Java, and it is missing enum
values the live API actually returns.

**After every refresh of the spec, re-apply these.** A plain re-download silently reverts them
— that is what happened in 1840c6c, which wiped the edits from 481c4f8 and broke the build.

## Won't compile without these

Fortnox emits property names that differ only in case. Both spellings camelize to one Java
field, so the generated model has a duplicate variable and does not compile. Delete the
variant listed; it is the one every other schema in the document disagrees with.

| Schema | Delete property |
| --- | --- |
| `fortnox_ArticleBundleSubItemRowSingleItem` | `EuAccount` (keep `EUAccount`) |
| `fortnox_ArticleBundleSubItemRowSinglePayloadItem` | `EuAccount` (keep `EUAccount`) |
| `fortnox_Lon_EmployeeCategoryListItem` | `value` (keep `Value`) |

To find new occurrences after a refresh, look for two properties on one schema whose names
match case-insensitively and whose camelized forms collide.

## Enum values the API returns but the spec omits

Observed against the Fortnox test environment; guarded by `FortnoxSchemaHacksTest`.

| Schema | Property | Add value |
| --- | --- | --- |
| `fortnox_Kf_InvoiceSingleItem` | `PaymentWay` | `""` |
| `fortnox_Kf_InvoiceV2SingleItem` | `PaymentWay` | `""` |
| `fortnox_DefaultDeliveryTypesSingleItem` | `Invoice` | `ELECTRONICINVOICE` |
| `fortnox_DefaultDeliveryTypesSinglePayloadItem` | `Invoice` | `ELECTRONICINVOICE` |

## Known-bad, deliberately left alone

`Recurring-API_CreateRecurringRow` and `Recurring-API_UpdateRecurringRow` have
`discriminator.mapping` entries pointing at schema names missing their `Recurring-API_`
prefix. On openapi-generator 2.10.0 this only logs six `Failed to lookup the schema` errors;
the code still compiles. Prefixing the six mapping values fixes the log and produces correct
`@JsonSubTypes`. It becomes a hard compile error on 2.18.0 and later.

Do not bump `quarkus-openapi-generator` past 2.10.0 without checking this: 2.18.0+ also
mis-generates `allOf` + sibling `default` (three spots in the recurring API) as a bare
identifier instead of a quoted string, e.g. `private String taxReductionCategory = NONE;`.
No generator config works around it.
