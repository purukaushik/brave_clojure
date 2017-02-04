(ns clojure-noob.hobbit
  (:require [clojure.string :as string])
  (:gen-class))

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  "Function that returns the matching part JSON for an incoming part JSON"
  [part]
  {:name (string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "@Expects - seq of maps that have :name, :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    ;;loop body
    (if (empty? remaining-asym-parts)
      final-body-parts
      ;;else
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining-asym-parts
               (into final-body-parts
                     (set [part (matching-part part)])))))))

(symmetrize-body-parts asym-hobbit-body-parts)
