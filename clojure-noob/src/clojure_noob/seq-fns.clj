(ns clojure-noob.functions-in-depth
  (:require [clojure.string :as string])
  (:gen-class))

;; Clojure sequences are an abstraction over lists, sets, maps and vectors
;; Clojure Sequences are required to extend first, rest, and cons
;; Imagine this java abstraction of a clojure sequence
;; interface Seq<E> {
;;   Seq<E> rest();
;;   E first();
;;   Seq<E> cons(E head, Seq<E> tail);
;;   ... other functions like map, filter, etc
;; }
;;
;;


;; straightforward mapper method  any Seq-like collection
;; function definition --> (map fn collections*)
(map inc [1 2 3])

;; mulitple collection inputs -> combines the two collections and then applies map
(map str ["a" "b" "c"] ["A" "B" "C"])

;; vvv----is equivalently---- vvv
(list (str "a" "A") (str "b" "B") (str "c" "C"))

;; fn that takes just two parameters
(defn two-adder [a b] (+ a b))

;; mapper must be able to take in same number of collections as parameters
(map two-adder [1 2 3] [ 3 4 5])

;; this probably throws an arity exception
(map two-adder [1 2 3] [3 4] [0]) ;; !!! ArityException !!!


;; using map to retrieve all values mapped to a keyword in a #map
(def batsmen
  [{:alias "MasterBlaster" :real "Tendulkar"}
   {:alias "Wall" :real "Dravid"}
   {:alias "Dada" :real "Ganguly"}])

(map :real batsmen)

;; reducer - basic summer
(reduce + '(1 3 4 5 6))

;; increment values of a #map
(defn val-inc [map-in]
  (reduce (fn [new-map [key val]]
             (assoc new-map key (inc val)))
          {} map-in))

(val-inc {1 2 2 1 3 2 4 5})

;; assoc function --> update a key with new value or just a new key,value
(def hmap {:node1 5 :node2 6})
(assoc hmap :node1 (inc (hmap :node1)))

;; reduce can actually be used to implement the map function like:
(defn map-it [f map-in]
  (reduce (fn [new-map [key val]]
            (assoc new-map key (f val)))
          {}
          map-in))

(map-it #(* % %) {:node1 5 :node2 6 :node3 120})





