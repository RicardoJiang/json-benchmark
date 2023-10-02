package com.jiang.json.benchmark

data class KRResponseDataClass(
    val users: List<KRUserDataClass>,
    val status: String,
    val is_real_json: Boolean
)

data class KRUserDataClass(
    val _id: String,
    val index: Int,
    val guid: String,
    val is_active: Boolean,
    val balance: String,
    val picture: String,
    val age: Int,
    val name: KRNameDataClass,
    val company: String,
    val email: String,
    val address: String,
    val about: String,
    val registered: String,
    val latitude: Double,
    val longitude: Double,
    val tags: List<String>,
    val range: List<Int>,
    val friends: List<KRFriendDataClass>,
    val images: List<KRImageDataClass>,
    val greeting: String,
    val favorite_fruit: String,
    val eye_color: String,
    val phone: String
)

class KRNameDataClass(
    val first: String,
    val last: String
)

data class KRFriendDataClass(
    val id: Int,
    val name: String
)

class KRImageDataClass(
    val id: String,
    val format: String,
    val url: String,
    val description: String
)