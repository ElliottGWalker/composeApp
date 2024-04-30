package com.example.composeapp.product.data.product

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
class ProductDetails(
    @Json(name = "id") val id: Long,
    @Json(name = "sku") val sku: String,
    @Json(name = "inStock") val inStock: Boolean,
    @Json(name = "sizeInStock") val sizeInStock: List<String>?,
    @Json(name = "availableSizes") val availableSizes: List<AvailableSize>,
    @Json(name = "handle") val handle: String,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,
    @Json(name = "type") val type: String,
    @Json(name = "gender") val gender: List<String>,
    @Json(name = "fit") val fit: String?,
    @Json(name = "labels") val labels: List<String>?,
    @Json(name = "colour") val colour: String,
    @Json(name = "price") val price: Double,
    @Json(name = "compareAtPrice") val compareAtPrice: Boolean?,
    @Json(name = "discountPercentage") val discountPercentage: Int?,
    @Json(name = "featuredMedia") val featuredMedia: Media,
    @Json(name = "media") val media: List<Media>,
    @Json(name = "objectID") val objectID: String,
) : Parcelable {
    val imageUrls by lazy {
        media.map { it.src }
    }
    companion object {
        fun createMock() =
            ProductDetails(
                id = 123,
                sku = "AB123",
                inStock = true,
                sizeInStock = listOf("s", "m", "l"),
                availableSizes =
                    listOf(
                        AvailableSize.createMock(true, "s"),
                        AvailableSize.createMock(true, "m"),
                        AvailableSize.createMock(true, "l"),
                    ),
                handle = "Handle",
                title = "title",
                description = "<meta charset=\"utf-8\">\n<p data-mce-fragment=\"1\"><strong data-mce-fragment=\"1\">RUN WITH IT</strong></p>\n<p data-mce-fragment=\"1\"><br data-mce-fragment=\"1\">Your run requires enduring comfort and support, so step out and hit the road in Speed. Made with zero-distractions and lightweight, ventilating fabrics that move with you, you can trust in Speed no matter how far you go.</p>\n<p data-mce-fragment=\"1\"> <br data-mce-fragment=\"1\"></p>\n<p data-mce-fragment=\"1\"><br data-mce-fragment=\"1\">- Full length legging<br data-mce-fragment=\"1\">- High-waisted<br data-mce-fragment=\"1\">- Compressive fit<br data-mce-fragment=\"1\">- Internal adjustable elastic/drawcord at front waistband<br data-mce-fragment=\"1\">- Pocket to back of waistband<br data-mce-fragment=\"1\">- Reflective Gymshark sharkhead logo to ankle<br data-mce-fragment=\"1\">- Main: 88% Polyester 12% Elastane. Internal Mesh: 76% Nylon 24% Elastane<br data-mce-fragment=\"1\">- We've cut down our use of swing tags, so this product comes without one<br data-mce-fragment=\"1\">- Model is <meta charset=\"utf-8\"><span data-mce-fragment=\"1\" lang=\"EN-GB\" class=\"TextRun SCXP103297068 BCX0\" data-contrast=\"none\" data-usefontface=\"true\" xml:lang=\"EN-GB\"><span data-mce-fragment=\"1\" class=\"NormalTextRun SCXP103297068 BCX0\">5'3\" and wears a size M</span></span><br data-mce-fragment=\"1\">- SKU: B3A3E-UBCY</p>",
                type = "type",
                gender = listOf("m", "f"),
                fit = "fit",
                labels = listOf("NEW", "GOING_FAST"),
                colour = "Grey",
                price = 10.00,
                compareAtPrice = false,
                discountPercentage = 0,
                featuredMedia = Media.createMock(1),
                media =
                    listOf(
                        Media.createMock(1),
                        Media.createMock(2),
                        Media.createMock(3),
                    ),
                objectID = "objectID",
            )
    }
}

@Parcelize
@JsonClass(generateAdapter = true)
class AvailableSize(
    @Json(name = "id") val id: Long,
    @Json(name = "inStock") val inStock: Boolean,
    @Json(name = "inventoryQuantity") val inventoryQuantity: Int,
    @Json(name = "price") val price: Double,
    @Json(name = "size") val size: String,
    @Json(name = "sku") val sku: String,
): Parcelable {
    companion object {
        fun createMock(
            inStock: Boolean,
            size: String,
        ) = AvailableSize(
            id = 321,
            inStock = inStock,
            inventoryQuantity = 1,
            price = 10.00,
            size = size,
            sku = "AB12301",
        )
    }
}

@Parcelize
@JsonClass(generateAdapter = true)
class Media(
    @Json(name = "admin_graphql_api_id") val adminGraphQlApiId: String,
    @Json(name = "alt") val alt: String?,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "height") val height: Int,
    @Json(name = "id") val id: Long,
    @Json(name = "position") val position: Int,
    @Json(name = "product_id") val productId: Long,
    @Json(name = "src") val src: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "variant_ids") val variantIds: List<Int>,
    @Json(name = "width") val width: Int,
): Parcelable {
    companion object {
        fun createMock(position: Int) =
            Media(
                adminGraphQlApiId = "adminGraphQlApiId",
                alt = "alt",
                createdAt = "2022-04-06T15:19:54+01:00",
                height = 2018,
                id = 789,
                position = position,
                productId = 1234,
                src = "https://cdn.shopify.com/s/files/1/1326/4923/products/SpeedLEGGINGNavy-B3A3E-UBCY.A-Edit_BK.jpg?v=1649254794",
                updatedAt = "2022-04-06T15:19:54+01:00",
                variantIds = listOf(),
                width = 1692,
            )
    }
}
