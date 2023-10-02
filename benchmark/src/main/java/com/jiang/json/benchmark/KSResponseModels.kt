package com.jiang.json.benchmark

import kotlinx.serialization.Serializable


@Serializable
data class KSResponse(
    val users: List<KSUser>,
    val status: String,
    val is_real_json: Boolean
)

@Serializable
data class KSUser(
    val _id: String,
    val index: Int,
    val guid: String,
    val is_active: Boolean,
    val balance: String,
    val picture: String,
    val age: Int,
    val name: KSName,
    val company: String,
    val email: String,
    val address: String,
    val about: String,
    val registered: String,
    val latitude: Double,
    val longitude: Double,
    val tags: List<String>,
    val range: List<Int>,
    val friends: List<KSFriend>,
    val images: List<KSImage>,
    val greeting: String,
    val favorite_fruit: String,
    val eye_color: String,
    val phone: String
)

@Serializable
class KSName(
    val first: String,
    val last: String
)

@Serializable
data class KSFriend(
    val id: Int,
    val name: String
)

@Serializable
class KSImage(
    val id: String,
    val format: String,
    val url: String,
    val description: String
)