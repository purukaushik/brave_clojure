;; Run all examples here with C-x C-e after last S-exp on emacs with clojure mode and cider

(ns clojure-noob.core
  (:require [clojure.string :as string])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Dei, mokka. Idhaan clojure!"))
(println "Manidha! oh Manidha! oh Manidha! Ooh Manidha!")nil
(defn train
  []
  (println "Choo!Choo! Chee!Chee! Veidi phone-ah! Veidi!"))
(+ 1 (* 2 3 4))

(if true "By Zeus's Armor" "This wont happen!")

(if (< 0 1) (if (< -1 0) "True") "False")

(if true (do (println "String Output") "Print") "Wont be printed")

(when true (println (str "Appending " "Strings")))

(nil?  1)

;;(1? 1) won't work though

;;Truthy stuff are non-nil like LISP, nil is falsey
(if nil "This" "That")
(if "That" "non-nil" "nil")

;; equality
(= 1 1)

;;logical ops
(or false nil :large_I_mean_venti
    :why_Cant_I_Just_say_large)

(and (or true nil) false)

;; value binding
(def failed-protagonist-names
  ["Larry Clotter", "Doreen the explorer"])
;; print value
failed-protagonist-names

;; what does this do?
(if (= :mild "mild") "Colons are equally equal" "Wrong has been done")
;; Colons arent equal

;; def is for values, defn is for functions

;; Clojure maps -> sorted and hash maps

;; empty map
{}
;; Keyword keys
{:f 1 :g 2}
;; String keys
{"1" 1}
;; vv Throws error!! vv
;; {1 2 3 5}

;; hash maps
(def my-map (hash-map :a 1 :b 2))

;; checking maps for equality
(def their-map {:a 1 :b 2})
(= their-map my-map) ;;returns false -> maybe object model for equality like java???

;; hash map get()
my-map
(get my-map :a) ;; => 1

;; default values when key is not in map
(get my-map :c) ;; => nil
(get my-map :c "Nothing dude")

;; nested maps
(def nested (hash-map :a (hash-map :b 1)))
;; get inside nested map
(get-in my-map [:b]) ;; => 2

;; map get is actually you trying to query a map like it is a function
({:name "The human teapot"} :name)
;; this works too for some odd reason
(:name {:name "The human teapot"})

;; :name is a keyword in clojure

;; Vectors
;; obviously hybrid
[3 45 -1 "hello"]
;; getting stuff by index
((get ["a" {:name "Pugsley Bottomwinter"} "c" ] 1) :name)
;; vector function creates vectors obviously
(vector "creepy" "full" "moon")
;; let's see if we can concatenate a list of strings
(str ["creepy" "full" "moon"])
;; Consing vectors
(conj [1 2 3] 4)

;; Lists are not vectors !!
'(1 2 3 4) ;; the lisp way of course
;; get by index
(nth '(:a :b :c) 0)
(conj '(0 2 1) -1) ;; adds to head of list

;; Sets -> again hashSets, sortedSets
;; again hybrid

;; Hash set ab initio
#{"kurt vonnegut" :p :e :i9 00} ;; duplicates cause problems

;; functionalized
(hash-set 1 2 2 3 1 2) ;; duplicates dont bother us this way
;; sets would need contains
(contains? #{:a :b} :a)
(contains? #{:a :b} :c)

;; Data structures recap -> lists, vectors, sets, hashmaps, keywords

;; Functions

;; vv cooky vv
((or + -) 1 2 3)
;; what happens ^^ here is currying. + is a ref to the (+) function
;; or returns the first truthy value. here + is truthy.

;; in a similar vein
((and (= 1 1) +) 1 2 3)
((and (= 0 1) +) 1 2 3)

(defn this-guitar
  "Kills fascists"
  [name]
  (str "This guitar " name " -> kills fascists"))

(this-guitar "does what?")

;; multiple parameters epdi raaa!!
(defn gcd
  "Euclidean gcd algorithm. Take two return one int."
  [a b]
  (if (= b 0) a (gcd b (mod a b))))

(defn lcm
  "UnEuclidean lcm algorithm. Take two return one int."
  [a b]
  (/ (* a b) (gcd a b)))

;; multiple- arity; gives us some optionsssss
(defn x-chop
  "Some bloody nosed chop that"
  ([name chop-type]
   (str "Kai! " chop-type " Kuthadi kuthadi " name ". Kuninju kuthadi " name "."))
  ([name]
   (x-chop name "karate")))
(x-chop "sailaka")
(x-chop "sailaka" "Seeman says: ")

;; destructuring
(defn my-first
  [[first-thing]]
  first-thing)

;; first and rest of var args
(defn my-favorite-things
  [first & rest]
  (str "Hi " first ", -> " (string/join rest))
  )
(my-favorite-things "Dorie" "Dora" "buji")

;; map destructurer
(defn map-location
  [{lat :lat lng :lng}]
  (string/join lat lng))
(def co-ords {:lat -33.1325 :long 23.2326})
(map-location co-ords)

;; clojure fn body
(defn illustrated-fn
  []
  (def st "does nothing")
  (def i 5)
  (def k (* i i))
  (str "this function " st " but return " k)
  )
(illustrated-fn)

;; a recursive looper function
(defn looper
  [i]
  (println i)
  (if (= i 0) 0 (looper (- i 1))))

(looper 5) ;; hmmm..that didn't work as expected. we need to yield like python

;; loop sum
(defn loop-sum
  [a,x]
  (if (= x 0) a (loop-sum (+ a x) (- x 1))))

(loop-sum 0 10)

;; first let's see lambdas -> (fn [x...] )
(map (fn [x] (* x x))
     [0 1 2 3 4 5])
;; more compact lambda
(#(* % %) 77)

;; multi-grain big turkeys
(#(+ %1 %2) 500 3)


;; classic exponentiation
(defn exp [x n]
  (if (zero? n) 1
      (* x (exp x (dec n)))))
(exp 2 4)

;;returning functions
(defn -maker
  [a]
  #(* (exp %1 %2) a))

;; cannot partially apply -> ((-maker 3) 5)
((-maker 3) 2 5)

;; say f(x) = 3*x^n = 3 * -maker(x,n)
(defn fofx [x n]
 ((-maker 3) x n))

;; computing with x=2, n=5
(fofx 2 5)

;; curried sum of squares
(defn sum-of-range-sq [f b y]
  (defn loop [a x]
    (if (= x 0) a (loop (+ (f x) a) (- x 1))))
    (loop b y))
(sum-of-range-sq (fn [x] (* x x)) 0 5)
