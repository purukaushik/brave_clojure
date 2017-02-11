(ns clojure-noob.binary-search
  (:gen-class))

(defn binary-search
  "Searches a sorted sequence `xs` for an element x"
  [xs x]
  (letfn [(helper
    [lo hi]
    (if (< lo hi)
      (let [mid (quot (+ lo hi) 2)]
        (if (= (get xs mid) x)
          mid
          (if (> (get xs mid) x)
            (helper lo (- mid 1))
            (helper (+ mid 1) hi))))
      (if (and (< lo (count xs)) (= (get xs lo) x))
        lo
        (if (and (>= hi 0) (= (get xs hi) x))
          hi
          -1))))]
    (helper 0 (- (count xs) 1))))

(def list-for-me [0 1 2 3 4 5 6 7 8])
(binary-search list-for-me 8)
