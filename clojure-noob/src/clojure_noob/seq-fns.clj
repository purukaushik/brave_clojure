(ns clojure-noob.seq-fns
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




(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

;; we already know how take, take-while, drop, drop-while works -> work on them in clojure-koans
;; same for filter

;; now for some some
;; some -> does the collection contain at least one element satisfying a predicate p
;; (some p collection)
(some #(>( :critter %) 5) food-journal)
(some #(>( :critter %) 3) food-journal)

;; get `first` stuff that satisfy a predicate p
(some #(and (> (:critter %) 3) %) food-journal)


;; some vampire database shit to demonstrate lazy sequences
(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true  :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true  :name "McMackson"}
   2 {:makes-blood-puns? true,  :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true,  :has-pulse? true  :name "Mickey Mouse"}})

(defn vampire-related-details
  [social-security-number]
  (Thread/sleep 1000)
  (get vampire-database social-security-number))

(defn vampire?
  [record]
  (and (:makes-blood-puns? record)
       (not (:has-pulse? record))
       record))

(defn identify-vampire
  [social-security-numbers]
  (first (filter vampire?
                 (map vampire-related-details social-security-numbers))))

(time (vampire-related-details 0))
(time (def mapped-details (map vampire-related-details (range 0 1000000))))
(time (first mapped-details))
(time (identify-vampire (range 0 1e6)))


;; a stream generator of natural numbers
(defn N-stream []
  (defn from
    [n]
    (cons n (lazy-seq (from (inc n)))))
  (from 1))
(take 1000 (N-stream))

;;PRIME GENERATOR HELPERS
;; not eq zero test
(defn nz? [x]
  (not (zero? x)))
;; a%b!=0?
(defn modnz? [a b]
  (nz? (mod a b)))

;; prime generator that doesn't work!!!!
(defn prime-stream
  ([] (prime-stream (N-stream)))
  ([xs]
   (cons (first xs) (prime-stream (filter #(modnz? % (first xs)) (rest xs) ) ))))

(take 10 (prime-stream))
;; !!!!!!!!!! ^^^^^^^^ DOESNT WORK ^^^^^^ !!!!!!!!!!

;; ok just do even-number streams
(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))

(take 10 (even-numbers))

;; into method -> push seq of key value pairs into a hashmap
;; sort of a tuple2HashMap()
(into {} [[:1 "Sun"] [:2 "Mercury"]])

;; opposite of this is the map method with an identity method as mapper
;; hashMapToPair
(map (fn [x] x) {:1 "Sun" :2 "Mercury"})

;; another way to do hashMapToPair
(seq {:1 "Sun" :2 "Mercury"})

;; into works on other data structures too
(into [] (map identity [:a :b :c :d :e])) ;; totally useless peice of code this

;; types of maps, sets
(type {}) ;; => PersistentArrayMap
(type (set [])) ;; => Also PersistentHashSet
(type #{}) ;; => PersistentHashSet
(type (hash-map :a 1)) ;; => PersistentHashMap

;; into vs conj
(conj [0] [1]) ;; => [0 [1]]
(into [0] [1]) ;; => [0 1]
;; so to add to end V
(conj [0 1 2] 3  4 5)
;; compare with cons
(cons 1 [2 3 4 5 6 7])

;; cons syntax is (cons x seq)
;; conj syntax on the other hand is (conj coll x & xs) -> more leeway at the end


;; conj map with pair
(conj {:time "Midnight"} [:place "GMT"])
;;but vector must be a pair
(conj {:time "Midnight"} [:place "GMT" :another "PST"])

;; conj in terms of into
(defn into-conj
  [dest & xs]
  (into dest xs))
(into-conj  [1 2 3] 1 2)

;; apply and partial -> Functional stuff now
(max 1 2 3) ;; => 3

;; what about applying max to each elem of a vector
(max [1 2 3]) ;; => won't work 
(apply max [1 2 3])

;; max with reduce
;; reduce syntax -> (reduce fn acc-default collection)
(defn maxer
  [xs]
  (reduce (fn [a x] (if (> x a) x a)) xs))
(maxer [1 2 3 4 -3])

;; partial functions
(def add12 (partial + 10 2))
(add12 5)

;; partially applied max function using reduce from before
(def max (partial reduce (fn [a x] (if (> x a ) x a))) )
(clojure-noob.seq-fns/max [1 2 3 4] )
