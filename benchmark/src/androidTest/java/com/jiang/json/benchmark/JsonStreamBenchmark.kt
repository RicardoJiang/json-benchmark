package com.jiang.json.benchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.filters.LargeTest
import com.alibaba.fastjson2.JSON
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.io.Resources
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import okio.buffer
import okio.source
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.junit.runners.Parameterized
import java.io.InputStreamReader

/**
 * Benchmark, which will execute on an Android device.
 *
 * The body of [BenchmarkRule.measureRepeated] is measured in a loop, and Studio will
 * output the result. Modify your code to see how it affects performance.
 */
@LargeTest
@RunWith(Parameterized::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class JsonStreamBenchmark(val jsonName: String, val pbName: String) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "size={0}")
        fun data(): List<Array<*>> {
            return listOf(
                arrayOf("small.json", "small.bin"),
                arrayOf("medium.json", "medium.bin"),
                arrayOf("large.json", "large.bin")
            )
        }
    }

    @get:Rule
    val benchmarkRule = BenchmarkRule()
    private val jsonResource = Resources.getResource(jsonName)
    private val pbResource = Resources.getResource(pbName)

    @Test
    fun testGson() {
        benchmarkRule.measureRepeated {
            val source = runWithTimingDisabled {
                jsonResource.openStream()
            }
            val gson = Gson()
            val reader = gson.newJsonReader(InputStreamReader(source))
            val result = gson.fromJson<KRResponse>(reader, KRResponse::class.java)
            println("gson $jsonName size: ${result?.users?.size}")
        }
    }

    @Test
    fun testMoshi() {
        benchmarkRule.measureRepeated {
            val source = runWithTimingDisabled {
                jsonResource.openStream()
            }
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val jsonAdapter = moshi.adapter(MoshiKRResponse::class.java)
            val result = jsonAdapter.fromJson(source.source().buffer())
            println("moshi $jsonName size: ${result?.users?.size}")
        }
    }

    @Test
    fun testJackson() {
        benchmarkRule.measureRepeated {
            val source = runWithTimingDisabled {
                jsonResource.openStream()
            }
            val mapper = ObjectMapper()
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            mapper.disable(DeserializationFeature.WRAP_EXCEPTIONS)
            val result = mapper.readValue(source, KRResponse::class.java)
            println("jackson $jsonName size: ${result?.users?.size}")
        }
    }

    @Test
    fun testFastJson() {
        benchmarkRule.measureRepeated {
            val source = runWithTimingDisabled {
                jsonResource.openStream()
            }
            val result = JSON.parseObject<KRResponse>(source, KRResponse::class.java)
            println("fastJson $jsonName size: ${result?.users?.size}")
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Test
    fun testKotlinSerialization() {
        benchmarkRule.measureRepeated {
            val source = runWithTimingDisabled {
                jsonResource.openStream()
            }
            val json = Json { ignoreUnknownKeys = true }
            val result = json.decodeFromStream<KSResponse>(source)
            println("KotlinSerialization $jsonName size: ${result.users.size}")
        }
    }

    @Test
    fun testJsonObject() {
        benchmarkRule.measureRepeated {
            val source = runWithTimingDisabled {
                jsonResource.openStream()
            }
            val result = JSONObjectSerializer().parse(source.bufferedReader().readText())
            println("JSONObject $jsonName size: ${result.users.size}")
        }
    }

    @Test
    fun testJSONReader() {
        benchmarkRule.measureRepeated {
            val source = runWithTimingDisabled {
                jsonResource.openStream()
            }
            val result = JSONReaderSerializer().parse(source)
            println("JSONReader $jsonName size: ${result.users.size}")
        }
    }

    @Test
    fun testProtobuf(){
        benchmarkRule.measureRepeated {
            val source = runWithTimingDisabled {
                pbResource.openStream()
            }
            val result = SomeMessageOuterClass.SomeMessage.parseFrom(source)
            println("pb $jsonName size: ${result?.usersList?.size}")
        }
    }
}