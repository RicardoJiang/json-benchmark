package com.jiang.json.benchmark

import com.kanyun.kudos.annotations.Kudos
import com.kanyun.kudos.gson.KUDOS_GSON
import com.kanyun.kudos.jackson.KUDOS_JACKSON

@Kudos(KUDOS_JACKSON)
class KudosJacksonResponse {
    var users: List<KudosJacksonUser>? = null
    val status: String? = null
    val is_real_json: Boolean? = null
}

@Kudos(KUDOS_JACKSON)
class KudosJacksonUser {
    var _id: String? = null
    var index: Int? = null
    var guid: String? = null
    var is_active: Boolean? = null
    var balance: String? = null
    var picture: String? = null
    var age: Int? = null
    var name: KudosJacksonName? = null
    var company: String? = null
    var email: String? = null
    var address: String? = null
    var about: String? = null
    var registered: String? = null
    var latitude: Double? = null
    var longitude: Double? = null
    var tags: List<String>? = null
    var range: List<Int>? = null
    var friends: List<KudosJacksonFriend>? = null
    var images: List<KudosJacksonImage>? = null
    var greeting: String? = null
    var favorite_fruit: String? = null
    var eye_color: String? = null
    var phone: String? = null
}

@Kudos(KUDOS_JACKSON)
class KudosJacksonName {
    var first: String? = null
    var last: String? = null
}

@Kudos(KUDOS_JACKSON)
class KudosJacksonFriend {
    var id: Int? = null
    var name: String? = null
}

@Kudos(KUDOS_JACKSON)
class KudosJacksonImage {
    var id: String? = null
    var format: String? = null
    var url: String? = null
    var description: String? = null
}