package com.tolikavr.telegram.test

class HappyMap<K, V>(val map: MutableMap<K, V> = mutableMapOf()) : MutableMap<K, V> by map {
  override fun put(key: K, value: V): V? {
    return map.put(key, value).apply {
      if (this == null) {
        println("Yay! $key")
      }
    }
  }
}

fun main() {
  val map = HappyMap<String, String>()
  map["A"] = "B"
  map["B"] = "C"
  map["A"] = "C"
  map.remove("A")
  map["A"] = "C"
}
