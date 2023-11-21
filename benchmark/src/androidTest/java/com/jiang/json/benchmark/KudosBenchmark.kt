package com.jiang.json.benchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.filters.LargeTest
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.io.Resources
import com.google.gson.Gson
import com.kanyun.kudos.gson.kudosGson
import com.kanyun.kudos.jackson.kudosObjectMapper
import com.kanyun.kudos.json.reader.KudosAndroidJsonReader
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
class KudosBenchmark(val resourceName: String) {

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
                resource.openStream()
            }
            val gson = Gson()
            val reader = gson.newJsonReader(InputStreamReader(source))
            val result = gson.fromJson<KRResponse>(reader, KRResponse::class.java)
            println("gson $resourceName size: ${result?.users?.size}")
        }
    }

    @Test
    fun testKudosGson() {
        benchmarkRule.measureRepeated {
            val source = runWithTimingDisabled {
                resource.openStream()
            }
            val gson = kudosGson()
            val reader = gson.newJsonReader(InputStreamReader(source))
            val result = gson.fromJson<KudosGsonResponse>(reader, KudosGsonResponse::class.java)
            println("kudosGson $resourceName size: ${result?.users?.size}")
        }
    }

    @Test
    fun testJackson() {
        benchmarkRule.measureRepeated {
            val source = runWithTimingDisabled {
                resource.openStream()
            }
            val mapper = ObjectMapper()
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            mapper.disable(DeserializationFeature.WRAP_EXCEPTIONS)
            val result = mapper.readValue(source, KRResponse::class.java)
            println("jackson $resourceName size: ${result?.users?.size}")
        }
    }

    @Test
    fun testKudosJackson() {
        benchmarkRule.measureRepeated {
            val source = runWithTimingDisabled {
                resource.openStream()
            }
            val mapper = kudosObjectMapper()
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            mapper.disable(DeserializationFeature.WRAP_EXCEPTIONS)
            val result = mapper.readValue(source, KudosJacksonResponse::class.java)
            println("kudosJackson $resourceName size: ${result?.users?.size}")
        }
    }

    @Test
    fun testJsonReader() {
        benchmarkRule.measureRepeated {
            val source = runWithTimingDisabled {
                resource.openStream()
            }
            val result = JSONReaderSerializer().parse(source)
            println("JSONReader $resourceName size: ${result.users.size}")
        }
    }

    @Test
    fun testKudosJsonReader(){
        benchmarkRule.measureRepeated {
            val source = runWithTimingDisabled {
                resource.openStream()
            }
            val result = KudosAndroidJsonReader.fromJson<KudosJsonReaderResponse>(source)
            println("kudosAndroidJsonReader $resourceName size: ${result.users?.size}")
        }
    }
}