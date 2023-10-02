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
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.junit.runners.Parameterized

/**
 * Benchmark, which will execute on an Android device.
 *
 * The body of [BenchmarkRule.measureRepeated] is measured in a loop, and Studio will
 * output the result. Modify your code to see how it affects performance.
 */
@LargeTest
@RunWith(Parameterized::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class JsonStringBenchmark(val resourceName: String) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "size={0}")
        fun data(): List<Array<*>> {
            return listOf(
                arrayOf("small.json"),
                arrayOf("medium.json"),
                arrayOf("large.json")
            )
        }
    }

    @get:Rule
    val benchmarkRule = BenchmarkRule()
    private val resource = Resources.getResource(resourceName)

    @Test
    fun testGson() {
        benchmarkRule.measureRepeated {
            val source = runWithTimingDisabled {
                resource.readText()
            }
            val gson = Gson()
            val result = gson.fromJson(source, KRResponse::class.java)
            println("gson $resourceName size: ${result?.users?.size}")
        }
    }

    @Test
    fun testMoshi() {
        benchmarkRule.measureRepeated {
            val source = runWithTimingDisabled {
                resource.readText()
            }
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val jsonAdapter = moshi.adapter(MoshiKRResponse::class.java)
            val result = jsonAdapter.fromJson(source)
            println("moshi $resourceName size: ${result?.users?.size}")
        }
    }

    @Test
    fun testJackson() {
        benchmarkRule.measureRepeated {
            val source = runWithTimingDisabled {
                resource.readText()
            }
            val mapper = ObjectMapper()
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            mapper.disable(DeserializationFeature.WRAP_EXCEPTIONS)
            val result = mapper.readValue(source, KRResponse::class.java)
            println("jackson $resourceName size: ${result?.users?.size}")
        }
    }

    @Test
    fun testFastJson() {
        benchmarkRule.measureRepeated {
            val source = runWithTimingDisabled {
                resource.readText()
            }
            val result = JSON.parseObject(source, KRResponse::class.java)
            println("fastJson $resourceName size: ${result?.users?.size}")
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Test
    fun testKotlinSerialization() {
        benchmarkRule.measureRepeated {
            val source = runWithTimingDisabled {
                resource.readText()
            }
            val json = Json { ignoreUnknownKeys = true }
            val result = json.decodeFromString<KSResponse>(source)
            println("KotlinSerialization $resourceName size: ${result.users.size}")
        }
    }

    @Test
    fun testJsonObject() {
        benchmarkRule.measureRepeated {
            val source = runWithTimingDisabled {
                resource.readText()
            }
            val result = JSONObjectSerializer().parse(source)
            println("JSONObject $resourceName size: ${result.users.size}")
        }
    }

    @Test
    fun testJSONReader() {
        benchmarkRule.measureRepeated {
            val source = runWithTimingDisabled {
                resource.readText()
            }
            val result = JSONReaderSerializer().parse(source.byteInputStream())
            println("JSONReader $resourceName size: ${result.users.size}")
        }
    }
}