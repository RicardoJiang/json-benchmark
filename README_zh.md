**[English](README.md)** | 简体中文

# json-benchmark
- 通过 Jetpack Microbenchmark 库进行基准测试，以避免 CPU 降频，JIT 优化对测试结果的影响
- 测试用例输入包括 12kb, 78kb, 238kb 大小的三个 json 文件，以测试 json 大小对反序列化速度的影响
- 测试结果分为多次运行充分预热与一次运行无预热两种情况，以测试在冷启动情况下反序列化速度的差异

## 测试结果
### 多次运行测试结果
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

### 一次运行测试结果
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

## Kudos 测试结果
[Kudos](https://github.com/kanyun-inc/Kudos) 是 Kotlin utilities for deserializing objects 的缩写。它可以解决使用 Gson、Jackson 等框架反序列化 JSON 到 Kotlin 类时所存在的空安全问题和构造器默认值失效的问题。

基于 Kudos 的工作机制不难想到，Kudos 的运行耗时会略微多于对应的 JSON 序列化框架。下面是 Kudos 与对应的 JSON 序列化框架的运行耗时对比：

|               | small json     | medium json    | large json     |
|---------------|----------------|----------------|----------------|
| Gson          | 412,375   ns   | 1,374,838   ns | 3,641,904   ns |
| Kudos-Gson    | 517,123   ns   | 1,686,568   ns | 4,311,910   ns |
| Jackson       | 1,035,010   ns | 1,750,709   ns | 3,450,974   ns |
| Kudos-Jackson | 1,261,026   ns | 2,030,874   ns | 3,939,600   ns |

## 更多
[常用 JSON 库性能对比](https://android-performance-optimization.github.io/practical/speed/json-serialization-speed/)