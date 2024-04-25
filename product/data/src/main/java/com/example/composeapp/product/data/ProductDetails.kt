package com.example.composeapp.product.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ProductDetails(
    @Json(name = "id") val id: Int,
    @Json(name = "sku") val sku: String,
    @Json(name = "inStock") val inStock: Boolean,
    @Json(name = "sizeInStock") val sizeInStock: List<String>,
    @Json(name = "availableSizes") val availableSizes: List<AvailableSize>,
    @Json(name = "handle") val handle: String,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,
    @Json(name = "type") val type: String,
    @Json(name = "gender") val gender: List<String>,
    @Json(name = "fit") val fit: String?,
    @Json(name = "labels") val labels: List<String>?,
    @Json(name = "colour") val colour: String,
    @Json(name = "price") val price: Int,
    @Json(name = "compareAtPrice") val compareAtPrice: Boolean?,
    @Json(name = "discountPercentage") val discountPercentage: Int?,
    @Json(name = "featuredMedia") val featuredMedia: Media,
    @Json(name = "media") val media: List<Media>,
    @Json(name = "objectID") val objectID: String,
)

@JsonClass(generateAdapter = true)
class AvailableSize(
    @Json(name = "id") val id: Int,
    @Json(name = "inStock") val inStock: Boolean,
    @Json(name = "inventoryQuantity") val inventoryQuantity: Int,
    @Json(name = "price") val price: Int,
    @Json(name = "size") val size: String,
    @Json(name = "sku") val sku: String,
)

@JsonClass(generateAdapter = true)
class Media(
    @Json(name = "admin_graphql_api_id") val adminGraphQlApiId: String,
    @Json(name = "alt") val alt: String?,
    @Json(name = "created_at") val createdAt: String?,
    @Json(name = "height") val height: Int,
    @Json(name = "id") val id: Int,
    @Json(name = "position") val position: Int,
    @Json(name = "product_id") val productId: Int,
    @Json(name = "src") val src: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "variant_ids") val variantIds: List<Int>,
    @Json(name = "width") val width: Int,
)
