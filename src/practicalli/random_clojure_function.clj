(ns practicalli.random-clojure-function
  (:gen-class))

;; data ---------------------------------------------------------
(def standard-library-functions
  "Fully qualified function names form clojure.core."
  (vals (ns-publics 'clojure.core)))

(def available-namespaces
  "Fully qualified function names availbale ns-publics"
  (mapcat #(vals (ns-publics %)) (all-ns)))

;; helpers ------------------------------------------------------
(defn function-list
  [namespace]
  (vals (ns-publics namespace)))

;; logic --------------------------------------------------------
(defn random-function
  "Returns the details of a function from clojure.core."
  [function-list]
  (let [function-details (meta (rand-nth function-list))]
    (str (function-details :ns) "/" (function-details :name)
         "\n " (function-details :arglists)
         "\n " (function-details :doc))))

(defn -main
  "Returns the details of a random function in the Clojure standard library."
  [& args]
  (if (seq args)
    (println (random-function (mapcat #(function-list (symbol %)) args)))
    (println (random-function available-namespaces))))

;; REPL experiments ----------------------------------------------
(comment
  ;;
  (-main)
  (-main 'clojure.stacktrace)

  ;;
  (all-ns)
  (function-list 'clojure.core)
  (mapcat #(vals (ns-publics %)) (all-ns))
  (ns-publics 'clojure.core)

  ;;
  )
