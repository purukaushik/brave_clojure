(ns clojure-noob.fn-prog
  (:require [clojure.string :as string])
  (:gen-class))

;; basic summer with recursion

(defn sum
  ;;(sum xs) will call (sum xs 0), ie acc -> 0
  ([xs] (sum xs 0))
  ([xs acc]
   (if (empty? xs) acc (sum (rest xs) (+ (first xs) acc)))))

;; way of the...
(sum '(1 2 3 -1 -2 ))
(sum [1 2 3 4])
(sum [1 2 3 4] -10)

;; with recur
(defn sum
  ([xs]
   (sum xs 0))
  ([xs acc]
   (if (empty? xs)
     acc
     (recur (rest xs) (+ (first xs) acc)))))

(sum [1 2 3 4])

;; super cool function composition with `comp`
((comp inc *) 2 4) ;; (2*4) + 1 => 9

;; what about (a mod b) !=0 function
((comp not zero? mod) 11 5)


;; x%3 != 0 ?
(defn mod3nz? [x]
  ((comp not zero? mod) x 3))

;; composing map, filter.

(defn abs-2-by-3
  "Takes a seq of numbers, filters those not divisible by 3 and muls them by 2"
  [xs]
  ((comp (partial map #(* % 2))
        (partial filter #(mod3nz? %)))
   xs))

(abs-2-by-3 [16 15 30 43])

;;now back to the course
