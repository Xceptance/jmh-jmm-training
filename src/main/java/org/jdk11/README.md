# Hotspot Compiler Difference
This test highlights a compiler difference between JDK 8 and JDK 11 that exhibits slower performance with older code.

## Results

Because support for complex tables is limited in Markdown, here are just the basic results. Find a link to a Google Sheet below which shows perf output as well.

All test have been executed on Ubuntu 18.04, kernel 4.15, 64 bit on a Lenovo Thinkpad T450s, Intel(R) Core(TM) i7-5600U CPU @ 2.60GHz, 20 GB RAM.

|Method                           |OpenJDK 1.8.0.242|OpenJDK 11.0.6|OpenJDK 15 EA 12|
|-------------------------------  |----------------:|-------------:|---------------:|
|**cpu_02a_StringTokenizer**      |1561 ns/ops      |1640 ns/ops   |1401 ns/ops     |
|**cpu_02b_StringTokenizerNoLoop**|1628 ns/ops      |1612 ns/ops   |1436 ns/ops     |
|**cpu_03a_LightTokenizer**       |1577 ns/ops      |**1954 ns/ops** |**1810 ns/ops**   |
|**cpu_03b_LightTokenizerNoLoop** |1741 ns/ops      |1804 ns/ops   |1418 ns/ops     |



