package com.jiang.json.benchmark

import com.kanyun.kudos.annotations.Kudos
import com.kanyun.kudos.gson.KUDOS_GSON

@Kudos(KUDOS_GSON)
class KudosGsonResponse {
    var users: List<KudosGsonUser>? = null
    val status: String? = null
    val is_real_json: Boolean? = null
}

@Kudos(KUDOS_GSON)
class KudosGsonUser {
    var _id: String? = null
    var index: Int? = null
    var guid: String? = null
    var is_active: Boolean? = null
    var balance: String? = null
    var picture: String? = null
    var age: Int? = null
    var name: KudosGsonName? = null
    var company: String? = null
    var email: String? = null
    var address: String? = null
    var about: String? = null
    var registered: String? = null
    var latitude: Double? = null
    var longitude: Double? = null
    var tags: List<String>? = null
    var range: List<Int>? = null
    var friends: List<KudosGsonFriend>? = null
    var images: List<KudosGsonImage>? = null
    var greeting: String? = null
    var favorite_fruit: String? = null
    var eye_color: String? = null
    var phone: String? = null
}

@Kudos(KUDOS_GSON)
class KudosGsonName {
    var first: String? = null
    var last: String? = null
}

@Kudos(KUDOS_GSON)
class KudosGsonFriend {
    var id: Int? = null
    var name: String? = null
}

@Kudos(KUDOS_GSON)
class KudosGsonImage {
    var id: String? = null
    var format: String? = null
    var url: String? = null
    var description: String? = null
}