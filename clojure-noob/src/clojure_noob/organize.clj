(ns clojure.noob.organize
  (:gen-class))

;; this exists throughout the lifecycle of the namespace under which it is defined
(def var 5)

;; storing an envaluated list
(def un-eval '(map inc [1 2]))

;; evaluate it 
(eval un-eval)

(type (ns-interns *ns*)) ;; PersistentArrayMap

