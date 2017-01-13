;; Run all examples here with C-x C-e after last S-exp on emacs with clojure mode and cider

(ns clojure-noob.core
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
