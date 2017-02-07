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

;; now back to the course

;; memoization of function results through `memoize`
;; for programs that take longer to run
(defn sleepy-identify
  [x]
  (Thread/sleep 3000)
  x)

(def memo-sleepy-identify (memoize sleepy-identify))

(time (memo-sleepy-identify "Free Snowden!")) ;; 3 seconds

(time (memo-sleepy-identify "Free Snowden!")) ;; immediately

(time (memo-sleepy-identify "Free Cenk!")) ;; again 3 seconds

((comp inc) 6)

;; exercises
;; 1. implement comp
;; (comp) (comp f) (comp f g) (comp f g & fs)
(defn composure
  ([f] f)
  ([f g] #(f (g %)))
  ([f g & fs]
   (reduce composure (list* f g fs))))
   
;; two function composure
((composure #(mod % 10) inc) 19)

;; regular comp for comparision
((comp #(mod % 10) inc) 19)

;; multi-function compsure
((comp #(mod % 5) #(mod % 7) inc) 15)
((composure #(mod % 5) #(mod % 7) inc) 15)

;; two function multi-args
(def map-to-pair (map identity {:1 "Sun" :2 "Mercury" :3 "Venus"}))
(def planets {:1 "Sun" :2 "Mercury" :3 "Venus" :4 "Earth"
              :5 "Mars" :6 "Jupiter" :7 "Saturn" :8 "Uranus" :9 "Neptune"
              })

;; let's go back to our earlier comp example with map filter mixins
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

(defn abs-2-by-3-composured
  [xs]
  ((composure (partial map #(* % 2))
              (partial filter #(mod3nz? %)))
   xs))
(abs-2-by-3-composured [16 15 30 43]) ;; works! yahoo

;; exercise 2. implement `assoc-in` function
;; syntax from clojure-docs ~> 
