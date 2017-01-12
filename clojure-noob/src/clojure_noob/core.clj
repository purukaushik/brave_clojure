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
