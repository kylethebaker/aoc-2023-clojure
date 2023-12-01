(ns aoc2023.day-01
  (:require [clojure.string :as str]))

(def digit-map {"one" 1
                "two" 2
                "three" 3
                "four" 4
                "five" 5
                "six" 6
                "seven" 7
                "eight" 8
                "nine" 9})

(def digit-regex
  (as-> digit-map _
    (keys _)
    (str/join "|" _)
    (str "(?=(\\d|" _ "))")
    (re-pattern _)))

(defn get-input []
  (as-> "./resources/day_01.input" _
    (slurp _)
    (str/split _ #"\n")))

(defn to-digit [digit-or-str]
  (if (contains? digit-map digit-or-str)
    (get digit-map digit-or-str)
    (Integer/parseInt digit-or-str)))

(defn normalize-digits [line]
  (->> line
    (re-seq digit-regex)
    (map last)
    (map to-digit)))

(defn get-two-digit-num [nums]
  (let [front (first nums) back (last nums)]
    (Integer/parseInt (str front back))))

; Answer is 55029
(defn part-1 []
  (->> (get-input)
    (map #(re-seq #"\d" %))
    (map get-two-digit-num)
    (reduce +)))

; Answer is 55686
(defn part-2 []
  (->> (get-input)
    (map normalize-digits)
    (map get-two-digit-num)
    (reduce +)))
