package com.jiang.json.benchmark

import com.kanyun.kudos.annotations.Kudos

@Kudos
class KudosResponse {
    var users: List<KudosUser>? = null
    val status: String? = null
    val is_real_json: Boolean? = null
}

@Kudos
class KudosUser {
    var _id: String? = null
    var index: Int? = null
    var guid: String? = null
    var is_active: Boolean? = null
    var balance: String? = null
    var picture: String? = null
    var age: Int? = null
    var name: KudosName? = null
    var company: String? = null
    var email: String? = null
    var address: String? = null
    var about: String? = null
    var registered: String? = null
    var latitude: Double? = null
    var longitude: Double? = null
    var tags: List<String>? = null
    var range: List<Int>? = null
    var friends: List<KudosFriend>? = null
    var images: List<KudosImage>? = null
    var greeting: String? = null
    var favorite_fruit: String? = null
    var eye_color: String? = null
    var phone: String? = null
}

@Kudos
class KudosName {
    var first: String? = null
    var last: String? = null
}

@Kudos
class KudosFriend {
    var id: Int? = null
    var name: String? = null
}

@Kudos
class KudosImage {
    var id: String? = null
    var format: String? = null
    var url: String? = null
    var description: String? = null
}