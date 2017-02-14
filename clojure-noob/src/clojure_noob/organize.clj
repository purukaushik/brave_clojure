(ns clojure_noob.organize
  (:gen-class))

;; this exists throughout the lifecycle of the namespace under which it is defined
(def var 5)

;; storing an envaluated list
(def un-eval '(map inc [1 2]))

;; evaluate it 
(eval un-eval)

(type (ns-interns *ns*)) ;; PersistentArrayMap

;; whatteh terrible way to deref
(eval (deref (get (ns-interns *ns*) 'un-eval)))

(create-ns 'clojure.noob.organize.nns)
(in-ns 'clojure.noob.organize.nns)

