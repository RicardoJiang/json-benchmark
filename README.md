English | **[简体中文](README_zh.md)**

# json-benchmark
- Use the Jetpack Microbenchmark library for benchmark testing to avoid the impact of CPU frequency reduction and JIT optimization on test results.
- The test case input includes three json files of size 12kb, 78kb, and 238kb to test the impact of json size on deserialization speed.
- The test results are divided into multiple runs with sufficient preheating and one run without preheating to test the difference in deserialization speed under cold start.

## Test results
### Multi-run test results
|                      | small json     | medium json    | large json     |
|----------------------|----------------|----------------|----------------|
| Kotlin Serialization | 165,936   ns   | 997,228   ns   | 2,933,098   ns |
| JSONReader           | 190,902   ns   | 1,164,605   ns | 3,412,914   ns |
| FastJson             | 196,860   ns   | 1,417,077   ns | 4,218,987   ns |
| JSONObject           | 258,789   ns   | 1,690,190   ns | 4,788,937   ns |
| Moshi                | 303,056   ns   | 1,411,364   ns | 3,955,789   ns |
| Gson                 | 412,421   ns   | 1,356,564   ns | 3,557,943   ns |
| Jackson              | 1,073,504   ns | 1,798,989   ns | 3,543,983   ns |


![](https://raw.gitmirror.com/RicardoJiang/resource/main/2023/october/DeserializationSpeedMultiTimes.png)

### One-run test results
|                      | small json      | medium json     | large json      |
|----------------------|-----------------|-----------------|-----------------|
| Kotlin Serialization | 4,114,323   ns  | 15,739,688   ns | 17,428,906   ns |
| JSONReader           | 630,469   ns    | 2,052,501   ns  | 5,630,261   ns  |
| FastJson             | 61,629,844   ns | 6,756,823   ns  | 10,529,791   ns |
| JSONObject           | 580,469   ns    | 2,227,290   ns  | 6,311,667   ns  |
| Moshi                | 4,460,886   ns  | 13,854,792   ns | 18,951,198   ns |
| Gson                 | 3,319,688   ns  | 5,568,906   ns  | 10,264,635   ns |
| Jackson              | 15,070,469   ns | 13,625,521   ns | 17,914,687   ns |


![](https://raw.gitmirror.com/RicardoJiang/resource/main/2023/october/DeserializationSpeedRunOnce.png)

## Kudos test results
[Kudos](https://github.com/kanyun-inc/Kudos) is short for Kotlin utilities for deserializing objects. It is designed to make it safer and easier to deserializing Kotlin classes with Gson and Jackson.

Based on the working mechanism of Kudos, it is not difficult to think that the running time of Kudos will be slightly longer than the corresponding JSON serialization framework. The following is a comparison of the running time of Kudos and the corresponding JSON serialization framework:

### Multi-run test results
|                  | small json     | medium json    | large json     |
|------------------|----------------|----------------|----------------|
| Gson             | 412,375   ns   | 1,374,838   ns | 3,641,904   ns |
| Kudos-Gson       | 517,123   ns   | 1,686,568   ns | 4,311,910   ns |
| Jackson          | 1,035,010   ns | 1,750,709   ns | 3,450,974   ns |
| Kudos-Jackson    | 1,261,026   ns | 2,030,874   ns | 3,939,600   ns |
| JsonReader       | 190,302   ns   | 1,176,479   ns | 3,464,174   ns |
| Kudos-JsonReader | 215,974   ns   | 1,359,587   ns | 4,019,024   ns |

### One-run test results
|                  | small json      | medium json     | large json      |
|------------------|-----------------|-----------------|-----------------|
| Gson             | 3,974,219   ns  | 4,666,927   ns  | 8,271,355   ns  |
| Kudos-Gson       | 4,531,718   ns  | 6,244,479   ns  | 11,160,782   ns |
| Jackson          | 12,821,094   ns | 13,930,625   ns | 15,989,791   ns |
| Kudos-Jackson    | 13,233,750   ns | 15,674,010   ns | 18,641,302   ns |
| JsonReader       | 662,032   ns    | 2,056,666   ns  | 4,624,687   ns  |
| Kudos-JsonReader | 734,907   ns    | 2,362,010   ns  | 6,212,917   ns  |

## More
[Performance comparison of commonly used JSON libraries](https://android-performance-optimization.github.io/practical/speed/json-serialization-speed/)


