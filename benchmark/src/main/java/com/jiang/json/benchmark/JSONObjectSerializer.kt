package com.jiang.json.benchmark

import org.json.JSONArray
import org.json.JSONObject

class JSONObjectSerializer {

    fun parse(text: String): KRResponseDataClass {
        val jsonObject = JSONObject(text)
        val result = KRResponseDataClass(users = jsonObject.optJSONArray("users")?.map { userArray, userIndex ->
            val user = userArray.optJSONObject(userIndex)
            KRUserDataClass(
                _id = user.optString("_id"),
                index = user.optInt("index"),
                guid = user.optString("guid"),
                is_active = user.optBoolean("is_active"),
                balance = user.optString("balance"),
                picture = user.optString("picture"),
                age = user.optInt("age"),
                name = user.optJSONObject("name")?.let { nameJsonObject ->
                    KRNameDataClass(
                        first = nameJsonObject.optString("first"),
                        last = nameJsonObject.optString("last")
                    )
                } ?: KRNameDataClass(first = "", last = ""),
                company = user.optString("company"),
                email = user.optString("email"),
                address = user.optString("address"),
                about = user.optString("about"),
                registered = user.optString("registered"),
                latitude = user.optDouble("latitude"),
                longitude = user.optDouble("longitude"),
                tags = user.optJSONArray("tags")?.map { tagArray, tagIndex ->
                    tagArray.optString(tagIndex)
                } ?: emptyList(),
                range = user.optJSONArray("range")?.map { rangeArray, rangeIndex ->
                    rangeArray.optInt(rangeIndex)
                } ?: emptyList(),
                friends = user.optJSONArray("friends")?.map { friendArray, friendIndex ->
                    val friend = friendArray.optJSONObject(friendIndex)
                    KRFriendDataClass(
                        id = friend.optInt("id"),
                        name = friend.optString("name")
                    )
                } ?: emptyList(),
                images = user.optJSONArray("images")?.map { imageArray, imageIndex ->
                    val image = imageArray.optJSONObject(imageIndex)
                    KRImageDataClass(
                        id = image.optString("id"),
                        format = image.optString("format"),
                        url = image.optString("url"),
                        description = image.optString("description")
                    )
                } ?: emptyList(),
                greeting = user.optString("greeting"),
                favorite_fruit = user.optString("favorite_fruit"),
                eye_color = user.optString("eye_color"),
                phone = user.optString("phone")
            )
        } ?: emptyList(),
            status = jsonObject.optString("status"),
            is_real_json = jsonObject.optBoolean("is_real_json")
        )
        return result
    }

    private inline fun <T> JSONArray.map(block: (JSONArray, Int) -> T): List<T> {
        val list = ArrayList<T>(length())
        for (index in 0 until length()) {
            list.add(block(this, index))
        }
        return list
    }
}