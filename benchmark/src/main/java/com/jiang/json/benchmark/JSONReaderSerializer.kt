package com.jiang.json.benchmark

import android.util.JsonReader
import java.io.InputStream

class JSONReaderSerializer {
    fun parse(inputStream: InputStream): KRResponseDataClass {
        val reader = JsonReader(inputStream.bufferedReader())
        var users: List<KRUserDataClass> = emptyList()
        var status: String = ""
        var is_real_json: Boolean = false
        reader.beginObject()
        while (reader.hasNext()) {
            val name = reader.nextName()
            if (name.equals("status")) {
                status = reader.nextString()
            } else if (name.equals("is_real_json")) {
                is_real_json = reader.nextBoolean()
            } else if (name.equals("users")) {
                users = readUsers(reader)
            } else {
                reader.skipValue()
            }
        }
        reader.endObject()
        return KRResponseDataClass(users = users, status = status, is_real_json = is_real_json)
    }

    private fun readUsers(reader: JsonReader): List<KRUserDataClass> {
        val users = mutableListOf<KRUserDataClass>()
        reader.beginArray()
        while (reader.hasNext()) {
            users.add(readUser(reader))
        }
        reader.endArray()
        return users
    }

    private fun readUser(reader: JsonReader): KRUserDataClass {
        var _id = ""
        var index = 0
        var guid = ""
        var is_active = false
        var balance = ""
        var picture = ""
        var age = 0
        var name = KRNameDataClass(first = "", last = "")
        var company = ""
        var email = ""
        var address = ""
        var about = ""
        var registered = ""
        var latitude = 0.0
        var longitude = 0.0
        var tags = emptyList<String>()
        var range = emptyList<Int>()
        var friends = emptyList<KRFriendDataClass>()
        var images = emptyList<KRImageDataClass>()
        var greeting = ""
        var favorite_fruit = ""
        var eye_color = ""
        var phone = ""
        reader.beginObject()
        while (reader.hasNext()) {
            val keyName = reader.nextName()
            when (keyName) {
                "_id" -> _id = reader.nextString()
                "index" -> index = reader.nextInt()
                "guid" -> guid = reader.nextString()
                "is_active" -> is_active = reader.nextBoolean()
                "balance" -> balance = reader.nextString()
                "picture" -> picture = reader.nextString()
                "age" -> age = reader.nextInt()
                "name" -> name = readName(reader)
                "company" -> company = reader.nextString()
                "email" -> email = reader.nextString()
                "address" -> address = reader.nextString()
                "about" -> about = reader.nextString()
                "registered" -> registered = reader.nextString()
                "latitude" -> latitude = reader.nextDouble()
                "longitude" -> longitude = reader.nextDouble()
                "tags" -> tags = readTags(reader)
                "range" -> range = readRange(reader)
                "friends" -> friends = readFriends(reader)
                "images" -> images = readImages(reader)
                "greeting" -> greeting = reader.nextString()
                "favorite_fruit" -> favorite_fruit = reader.nextString()
                "eye_color" -> eye_color = reader.nextString()
                "phone" -> phone = reader.nextString()
                else -> reader.skipValue()
            }
        }
        reader.endObject()
        return KRUserDataClass(
            _id = _id,
            index = index,
            guid = guid,
            is_active = is_active,
            balance = balance,
            picture = picture,
            age = age,
            name = name,
            company = company,
            email = email,
            address = address,
            about = about,
            registered = registered,
            latitude = latitude,
            longitude = longitude,
            tags = tags,
            range = range,
            friends = friends,
            images = images,
            greeting = greeting,
            favorite_fruit = favorite_fruit,
            eye_color = eye_color,
            phone = phone
        )
    }

    private fun readTags(reader: JsonReader): List<String> {
        val tags = mutableListOf<String>()
        reader.beginArray()
        while (reader.hasNext()) {
            tags.add(reader.nextString())
        }
        reader.endArray()
        return tags
    }

    private fun readRange(reader: JsonReader): List<Int> {
        val range = mutableListOf<Int>()
        reader.beginArray()
        while (reader.hasNext()) {
            range.add(reader.nextInt())
        }
        reader.endArray()
        return range
    }

    private fun readName(reader: JsonReader): KRNameDataClass {
        var first = ""
        var last = ""
        reader.beginObject()
        while (reader.hasNext()) {
            val keyName = reader.nextName()
            when (keyName) {
                "first" -> first = reader.nextString()
                "last" -> last = reader.nextString()
                else -> reader.skipValue()
            }
        }
        reader.endObject()
        return KRNameDataClass(first = first, last = last)
    }

    private fun readFriends(reader: JsonReader): List<KRFriendDataClass> {
        val friends = mutableListOf<KRFriendDataClass>()
        reader.beginArray()
        while (reader.hasNext()) {
            friends.add(readFriend(reader))
        }
        reader.endArray()
        return friends
    }

    private fun readFriend(reader: JsonReader): KRFriendDataClass {
        var id = 0
        var name = ""
        reader.beginObject()
        while (reader.hasNext()) {
            val keyName = reader.nextName()
            when (keyName) {
                "id" -> id = reader.nextInt()
                "name" -> name = reader.nextString()
                else -> reader.skipValue()
            }
        }
        reader.endObject()
        return KRFriendDataClass(id = id, name = name)
    }

    private fun readImages(reader: JsonReader): List<KRImageDataClass> {
        val images = mutableListOf<KRImageDataClass>()
        reader.beginArray()
        while (reader.hasNext()) {
            images.add(readImage(reader))
        }
        reader.endArray()
        return images
    }

    private fun readImage(reader: JsonReader): KRImageDataClass {
        var id = ""
        var format = ""
        var url = ""
        var description = ""
        reader.beginObject()
        while (reader.hasNext()) {
            val keyName = reader.nextName()
            when (keyName) {
                "id" -> id = reader.nextString()
                "format" -> format = reader.nextString()
                "url" -> url = reader.nextString()
                "description" -> description = reader.nextString()
                else -> reader.skipValue()
            }
        }
        reader.endObject()
        return KRImageDataClass(id = id, format = format, url = url, description = description)
    }
}