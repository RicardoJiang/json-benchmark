package com.jiang.json.benchmark

import com.kanyun.kudos.annotations.Kudos
import com.kanyun.kudos.gson.KUDOS_GSON
import com.kanyun.kudos.json.reader.KUDOS_ANDROID_JSON_READER

@Kudos(KUDOS_ANDROID_JSON_READER)
class KudosJsonReaderResponse {
    var users: List<KudosJsonReaderUser>? = null
    val status: String? = null
    val is_real_json: Boolean? = null
}

@Kudos(KUDOS_ANDROID_JSON_READER)
class KudosJsonReaderUser {
    var _id: String? = null
    var index: Int? = null
    var guid: String? = null
    var is_active: Boolean? = null
    var balance: String? = null
    var picture: String? = null
    var age: Int? = null
    var name: KudosJsonReaderName? = null
    var company: String? = null
    var email: String? = null
    var address: String? = null
    var about: String? = null
    var registered: String? = null
    var latitude: Double? = null
    var longitude: Double? = null
    var tags: List<String>? = null
    var range: List<Int>? = null
    var friends: List<KudosJsonReaderFriend>? = null
    var images: List<KudosJsonReaderImage>? = null
    var greeting: String? = null
    var favorite_fruit: String? = null
    var eye_color: String? = null
    var phone: String? = null
}

@Kudos(KUDOS_ANDROID_JSON_READER)
class KudosJsonReaderName {
    var first: String? = null
    var last: String? = null
}

@Kudos(KUDOS_ANDROID_JSON_READER)
class KudosJsonReaderFriend {
    var id: Int? = null
    var name: String? = null
}

@Kudos(KUDOS_ANDROID_JSON_READER)
class KudosJsonReaderImage {
    var id: String? = null
    var format: String? = null
    var url: String? = null
    var description: String? = null
}