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

