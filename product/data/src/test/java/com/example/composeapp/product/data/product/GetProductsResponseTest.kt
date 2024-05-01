package com.example.composeapp.product.data.product

import com.example.composeapp.product.data.getMoshi
import junit.framework.TestCase.assertEquals
import org.intellij.lang.annotations.Language
import org.junit.Test

class GetProductsResponseTest {
    @Language("JSON")
    private val json =
        """
        {
          "hits": [{
            "id": 1234,
            "sku": "AB123",
            "inStock": true,
            "sizeInStock": ["xs", "s"],
            "availableSizes": [
              {
                "id": 123401,
                "inStock": true,
                "inventoryQuantity": 1,
                "price": 1000,
                "size": "xs",
                "sku": "AB12301"
              },
              {
                "id": 123402,
                "inStock": false,
                "inventoryQuantity": 0,
                "price": 1000,
                "size": "s",
                "sku": "AB12302"
              }
            ],
            "handle": "gymshark-handle",
            "title": "Speed Leggings",
            "description": "This will be html text",
            "type": "Womens Leggings",
            "gender": ["f"],
            "fit": "long",
            "labels": ["new", "popular"],
            "colour": "Navy",
            "price": 1000,
            "compareAtPrice": null,
            "discountPercentage": null,
            "featuredMedia": {
              "admin_graphql_api_id": "gid://shopify/ProductImage/29035954831459",
              "alt": null,
              "created_at": "2022-04-06T15:19:54+01:00",
              "height": 2018,
              "id": 29035954831459,
              "position": 1,
              "product_id": 6732609257571,
              "src": "https://cdn.shopify.com/s/files/1/1326/4923/products/SpeedLEGGINGNavy-B3A3E-UBCY.A-Edit_BK.jpg?v=1649254794",
              "updated_at": "2022-04-06T15:19:54+01:00",
              "variant_ids": [],
              "width": 1692
            },
            "media": [
              {
                "admin_graphql_api_id": "gid://shopify/ProductImage/29035954831459",
                "alt": null,
                "created_at": "2022-04-06T15:19:54+01:00",
                "height": 2018,
                "id": 29035954831459,
                "position": 1,
                "product_id": 6732609257571,
                "src": "https://cdn.shopify.com/s/files/1/1326/4923/products/SpeedLEGGINGNavy-B3A3E-UBCY.A-Edit_BK.jpg?v=1649254794",
                "updated_at": "2022-04-06T15:19:54+01:00",
                "variant_ids": [],
                "width": 1692
              }
            ],
            "objectID": "6732609257571"
          }]
        }
        """.trimIndent()

    @Test
    fun `GetProductsResponse - should deserialise fields correctly`() {
        val result =
            getMoshi()
                .adapter(GetProductsResponse::class.java)
                .fromJson(json)

        assertEquals(1, result?.products?.size)
        assertEquals(1234L, result?.products?.first()?.id)
        assertEquals("AB123", result?.products?.first()?.sku)
        assertEquals(true, result?.products?.first()?.inStock)

        assertEquals(2, result?.products?.first()?.sizeInStock?.size)
        assertEquals("xs", result?.products?.first()?.sizeInStock?.first())
        assertEquals("s", result?.products?.first()?.sizeInStock?.get(1))

        assertEquals(2, result?.products?.first()?.availableSizes?.size)
        assertEquals(123401L, result?.products?.first()?.availableSizes?.first()?.id)
        assertEquals(true, result?.products?.first()?.availableSizes?.first()?.inStock)
        assertEquals(1, result?.products?.first()?.availableSizes?.first()?.inventoryQuantity)
        assertEquals(1000.toDouble(), result?.products?.first()?.availableSizes?.first()?.price)
        assertEquals("xs", result?.products?.first()?.availableSizes?.first()?.size)
        assertEquals("AB12301", result?.products?.first()?.availableSizes?.first()?.sku)

        assertEquals(123402L, result?.products?.first()?.availableSizes?.get(1)?.id)
        assertEquals(false, result?.products?.first()?.availableSizes?.get(1)?.inStock)
        assertEquals(0, result?.products?.first()?.availableSizes?.get(1)?.inventoryQuantity)
        assertEquals(1000.toDouble(), result?.products?.first()?.availableSizes?.get(1)?.price)
        assertEquals("s", result?.products?.first()?.availableSizes?.get(1)?.size)
        assertEquals("AB12302", result?.products?.first()?.availableSizes?.get(1)?.sku)

        assertEquals("gymshark-handle", result?.products?.first()?.handle)
        assertEquals("Speed Leggings", result?.products?.first()?.title)
        assertEquals("This will be html text", result?.products?.first()?.description)
        assertEquals("Womens Leggings", result?.products?.first()?.type)

        assertEquals(1, result?.products?.first()?.gender?.size)
        assertEquals("f", result?.products?.first()?.gender?.first())

        assertEquals("long", result?.products?.first()?.fit)

        assertEquals(2, result?.products?.first()?.labels?.size)
        assertEquals("new", result?.products?.first()?.labels?.first())
        assertEquals("popular", result?.products?.first()?.labels?.get(1))

        assertEquals("Navy", result?.products?.first()?.colour)
        assertEquals(1000.toDouble(), result?.products?.first()?.price)
        assertEquals(null, result?.products?.first()?.compareAtPrice)
        assertEquals(null, result?.products?.first()?.discountPercentage)

        assertEquals("gid://shopify/ProductImage/29035954831459", result?.products?.first()?.featuredMedia?.adminGraphQlApiId)
        assertEquals(null, result?.products?.first()?.featuredMedia?.alt)
        assertEquals("2022-04-06T15:19:54+01:00", result?.products?.first()?.featuredMedia?.createdAt)
        assertEquals(2018, result?.products?.first()?.featuredMedia?.height)
        assertEquals(29035954831459, result?.products?.first()?.featuredMedia?.id)
        assertEquals(1, result?.products?.first()?.featuredMedia?.position)
        assertEquals(6732609257571, result?.products?.first()?.featuredMedia?.productId)
        assertEquals(
            "https://cdn.shopify.com/s/files/1/1326/4923/products/SpeedLEGGINGNavy-B3A3E-UBCY.A-Edit_BK.jpg?v=1649254794",
            result?.products?.first()?.featuredMedia?.src,
        )
        assertEquals("2022-04-06T15:19:54+01:00", result?.products?.first()?.featuredMedia?.updatedAt)
        assertEquals(0, result?.products?.first()?.featuredMedia?.variantIds?.size)
        assertEquals(1692, result?.products?.first()?.featuredMedia?.width)

        assertEquals(1, result?.products?.first()?.media?.size)
        assertEquals("gid://shopify/ProductImage/29035954831459", result?.products?.first()?.media?.first()?.adminGraphQlApiId)
        assertEquals(null, result?.products?.first()?.media?.first()?.alt)
        assertEquals("2022-04-06T15:19:54+01:00", result?.products?.first()?.media?.first()?.createdAt)
        assertEquals(2018, result?.products?.first()?.media?.first()?.height)
        assertEquals(29035954831459, result?.products?.first()?.media?.first()?.id)
        assertEquals(1, result?.products?.first()?.media?.first()?.position)
        assertEquals(6732609257571, result?.products?.first()?.media?.first()?.productId)
        assertEquals(
            "https://cdn.shopify.com/s/files/1/1326/4923/products/SpeedLEGGINGNavy-B3A3E-UBCY.A-Edit_BK.jpg?v=1649254794",
            result?.products?.first()?.media?.first()?.src,
        )
        assertEquals("2022-04-06T15:19:54+01:00", result?.products?.first()?.media?.first()?.updatedAt)
        assertEquals(0, result?.products?.first()?.media?.first()?.variantIds?.size)
        assertEquals(1692, result?.products?.first()?.media?.first()?.width)

        assertEquals("6732609257571", result?.products?.first()?.objectID)
    }
}
